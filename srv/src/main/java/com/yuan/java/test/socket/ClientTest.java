package com.yuan.java.test.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientTest {
	public static void main(String[] args) throws IOException {
		Socket socket = new Socket();
		socket.setReuseAddress(true);
		socket.connect(new InetSocketAddress("10.128.11.129", 8887));
		OutputStream is = socket.getOutputStream();
		
		is.write("hello world".getBytes());
		
		socket.close();
	}
}
