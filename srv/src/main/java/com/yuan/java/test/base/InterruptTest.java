package com.yuan.java.test.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class InterruptTest implements Runnable{
	
	private double d = 0.0;
	
	public static void main(String[] args) throws InterruptedException {
		testThreadPoolStop();
	}

	public static void testThreadStop() throws InterruptedException {
		Thread t = new Thread(new InterruptTest());
		t.start();
		Thread.sleep(100l);
//		System.out.println("****************************");  
//        System.out.println("Interrupted Thread!");  
//        System.out.println("****************************");  
	}
	
	public static void testThreadPoolStop() throws InterruptedException {
		ExecutorService es = Executors.newSingleThreadExecutor();
		Future future = es.submit(new InterruptTest());
		Thread.sleep(100l);
		if(!future.isDone()) {
			boolean isCanneled = future.cancel(true);
			System.out.println("****************************");  
	        System.out.println("task canncel!"  + isCanneled);  
	        System.out.println("****************************");  
		}
		System.out.println("******shutdown**********"); 
		es.shutdownNow();
		System.out.println("******isTerminated**********"); 
		System.out.println(es.isTerminated());
		System.out.println(es.isShutdown());
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			System.out.println("hello world!!");
			
			for (int i = 0; i < 900000; i++) {  
                d =  d + (Math.PI + Math.E) / d;  
            }  
			Thread.yield();
		}
	}
}
