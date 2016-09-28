package com.yuan.java.test.nio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioServerTest {

	public static void main(String[] args) {
		server();
	}

	public static void server() {
		ServerSocketChannel channel = null;
		try {
			channel = ServerSocketChannel.open();
			channel.configureBlocking(false);
			channel.bind(new InetSocketAddress(8887));
			channel.socket().setReuseAddress(true);
			Selector selector = Selector.open();
			channel.register(selector, SelectionKey.OP_ACCEPT, new Integer(1));

			while (true) {
				if (selector.select() > 0) {
					Set<SelectionKey> sets = selector.selectedKeys();
					Iterator<SelectionKey> keys = sets.iterator();
					while (keys.hasNext()) {
						SelectionKey key = keys.next();
						keys.remove();
						if (key.isAcceptable()) {
							key.attach(new Integer(1));
							SocketChannel schannel = ((ServerSocketChannel) key.channel()).accept();
							schannel.configureBlocking(false);
							schannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
						}
						if (key.isReadable()) {
							SocketChannel schannel = (SocketChannel) key.channel();
							ByteBuffer buf = ByteBuffer.allocate(1024);
							ByteArrayOutputStream output = new ByteArrayOutputStream();
							while ((schannel.read(buf)) != 0) {
								buf.flip();
								byte by[] = new byte[buf.remaining()];
								buf.get(by);
								output.write(by);
								buf.clear();
							}
							String str = output.toString();
							System.out.println("server read: " + str);
							key.attach(str);
						}

						if (key.isWritable()) {
							Object object = key.attachment();
							String attach = object != null ? "server replay: " + object.toString() : "server replay: ";
							SocketChannel schannel = (SocketChannel) key.channel();
							schannel.write(ByteBuffer.wrap(attach.getBytes()));
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				channel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
