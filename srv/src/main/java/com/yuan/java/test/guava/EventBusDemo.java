package com.yuan.java.test.guava;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Desc :
 * Author : chavezyuan
 * Date : 2016-08-17
 */
public class EventBusDemo {

    static ExecutorService executorService = Executors.newSingleThreadExecutor();


    public static void main(String[] args) throws InterruptedException {
        try {
//        EventBus eventBus = new EventBus();

            EventBus eventBus = new AsyncEventBus(executorService);

            eventBus.register(new EventBusChangeRecorder());

            System.out.println(Thread.currentThread().getName());
            eventBus.post("hello world");

            System.out.println("after event post");

        } finally {
            Thread.sleep(1000);
            executorService.shutdown();
            while(!executorService.awaitTermination(2, TimeUnit.SECONDS)){}
        }

    }


}


class EventBusChangeRecorder {

    @Subscribe
    public void recordChange(String s) {
        System.out.println(s);
        System.out.println(Thread.currentThread().getName());
    }
}