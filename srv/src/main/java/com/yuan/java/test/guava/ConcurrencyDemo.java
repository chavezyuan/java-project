package com.yuan.java.test.guava;

import com.google.common.util.concurrent.*;

import java.util.concurrent.*;

/**
 * Desc :
 * Author : chavezyuan
 * Date : 2016-08-16
 */
public class ConcurrencyDemo {

    static ExecutorService executor = Executors.newSingleThreadExecutor();

    static ListeningExecutorService listeningExecutor = MoreExecutors.listeningDecorator(executor);

    public static void main(String[] args) throws InterruptedException {
//        try {
//            traditionalTest();
//        } finally {
//            executor.shutdown();
//            while (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
//                System.out.println("waiting...");
//            }
//        }


        try{
            concurrencyTest();
        } finally {
            listeningExecutor.shutdown();
            while (!listeningExecutor.awaitTermination(5, TimeUnit.SECONDS)) {

            }
        }
    }


    public static void traditionalTest() {
        Future<String> future = executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "success";
            }
        });

        try {
            String result = future.get();

            System.out.println(result);


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    public static void concurrencyTest() {

        final ListenableFuture<String> future = listeningExecutor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {

                return "success";
            }
        });

        future.addListener(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(future.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }, listeningExecutor);


        Futures.addCallback(future, new FutureCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println(result);
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("f");
            }
        } , listeningExecutor);

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }





    public static void serviceTest() {

//        Service
    }
}
