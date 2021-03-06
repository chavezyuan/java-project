package com.yuan.java.test.nio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioClient2Test {
	public static void main(String[] args) {
		client();
	}

	public static void client() {
		SocketChannel channel = null;
		try {

			Selector selector = Selector.open();
			channel = SocketChannel.open();
			channel.configureBlocking(false);
			channel.connect(new InetSocketAddress(8887));
			channel.register(selector, SelectionKey.OP_CONNECT);

			while (true) {
				if (selector.select() > 0) {

					Iterator<SelectionKey> set = selector.selectedKeys().iterator();
					while (set.hasNext()) {
						SelectionKey key = set.next();
						set.remove();

						SocketChannel ch = (SocketChannel) key.channel();
						if (key.isConnectable()) {
							ch.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE, new Integer(1));
							ch.finishConnect();
						}

						if (key.isReadable()) {
							key.attach(new Integer(1));
							ByteArrayOutputStream output = new ByteArrayOutputStream();
							ByteBuffer buffer = ByteBuffer.allocate(1024);
							while ((ch.read(buffer)) != 0) {
								buffer.flip();
								byte by[] = new byte[buffer.remaining()];
								buffer.get(by);
								output.write(by);
								buffer.clear();
							}
							System.out.println(new String(output.toByteArray()));
							output.close();
						}

						if (key.isWritable()) {
							key.attach(new Integer(1));
							ch.write(ByteBuffer.wrap((("client say:hi----2")).getBytes()));
						}
					}
				}
			}
		} catch (Exception e) {
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
