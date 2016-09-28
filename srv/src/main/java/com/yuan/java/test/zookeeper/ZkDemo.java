package com.yuan.java.test.zookeeper;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

import com.javafx.tools.doclets.formats.html.SourceToHTMLConverter;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ZkDemo {

	static String address = "localhost:2181";
	
	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {

		ZooKeeper zk = new ZooKeeper(address, 3000, new Watcher() {

			@Override
			public void process(WatchedEvent event) {
				System.out.println("watcher1: " + event.getPath());
			}
			
		});

        String path = "/demo";

//		zk.create("/demo", new byte[0], Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        Stat stat = new Stat();
        byte[] data = new byte[1024];
        System.out.println("---------------before get");
        data = zk.getData(path, false, stat);
        System.out.println("---------------after get");
//        data = zk.getData(path, new Watcher() {
//            @Override
//            public void process(WatchedEvent event) {
//                System.out.println("watcher2: " + event.getPath());
//            }
//        }, stat);
        System.out.println(new String(data, 0, data.length));
        System.out.println(stat.getCtime());

//        zk.setData(path, "update_data".getBytes(), stat.getVersion());
//
//        data = zk.getData(path, false, stat);
//
//        System.out.println(new String(data, 0, data.length));
    }

	
	
	
	static class SyncPrimitive implements Watcher {
		static ZooKeeper zk = null;
		static final Object mutex = new Object();

		String root;

		SyncPrimitive(String address) throws KeeperException, IOException {
			if (zk == null) {
				System.out.println("Starting ZK:");
				zk = new ZooKeeper(address, 3000, this);
				System.out.println("Finished starting ZK: " + zk);
			}
		}

		@Override
		public void process(WatchedEvent event) {
			synchronized (mutex) {
				mutex.notify();
			}
		}
	}

	static class Queue extends SyncPrimitive {
		Queue(String address, String name) throws KeeperException, InterruptedException, IOException {
			super(address);
			this.root = name;
			// Create ZK node name
			if (zk != null) {
				Stat s = zk.exists(root, false);
				if (s == null) {
					zk.create(root, new byte[0], Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, null, 0);
				}
			}
		}
		
		boolean produce(int i) throws KeeperException, InterruptedException{
            ByteBuffer b = ByteBuffer.allocate(4);
            byte[] value;
 
            // Add child with value i
            b.putInt(i);
            value = b.array();
            zk.create(root + "/element", value, Ids.OPEN_ACL_UNSAFE,
                        CreateMode.EPHEMERAL_SEQUENTIAL);
 
            return true;
        }
		
		int consume() throws KeeperException, InterruptedException{
            int retvalue = -1;
            Stat stat = null;
 
            // Get the first element available
            while (true) {
                synchronized (mutex) {
                    List<String> list = zk.getChildren(root, true);
                    if (list.isEmpty()) {
                        System.out.println("Going to wait");
                        mutex.wait();
                    } else {
                        Integer min = new Integer(list.get(0).substring(7));
                        for(String s : list){
                            Integer tempValue = new Integer(s.substring(7));
                            if(tempValue < min) min = tempValue;
                        }
                        System.out.println("Temporary value: " + root + "/element" + min);
                        byte[] b = zk.getData(root + "/element" + min, false, stat);
                        zk.delete(root + "/element" + min, 0);
                        ByteBuffer buffer = ByteBuffer.wrap(b);
                        retvalue = buffer.getInt();
 
                        return retvalue;
                    }
                }
            }
        }
	}
}
