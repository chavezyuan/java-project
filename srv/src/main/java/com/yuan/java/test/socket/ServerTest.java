package com.yuan.java.test.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket socket = new ServerSocket();
		socket.bind(new InetSocketAddress("10.128.11.129", 8887));
//		ServerSocketChannel channel = socket.getChannel();
		socket.setReuseAddress(true);
		Socket nSocket = socket.accept();
		OutputStream os = nSocket.getOutputStream();
		InputStream is = nSocket.getInputStream();
		
		byte[] b = new byte[1024];
		is.read(b);
		System.out.println(new String(b));
		
		nSocket.close();
		socket.close();
	}

}
