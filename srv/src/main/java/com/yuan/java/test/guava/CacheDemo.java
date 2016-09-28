package com.yuan.java.test.guava;

import com.google.common.cache.*;
import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Desc :
 * Author : chavezyuan
 * Date : 2016-08-16
 */
public class CacheDemo {

    public static void main(String[] args) {
        cacheTest();
    }

    static Map<Integer, String> database;

    static {
        database = Maps.newHashMap();
        database.put(1, "a");
        database.put(2, "b");
        database.put(3, "c");
    }

    static Executor executor = Executors.newSingleThreadExecutor();

    public static void cacheTest() {

        LoadingCache<Integer, String> cache =
                CacheBuilder.newBuilder().maximumSize(3)
                        .removalListener(RemovalListeners.asynchronous(new RemovalListener<Integer, String>() {
                            @Override
                            public void onRemoval(RemovalNotification<Integer, String> notification) {
                                Integer key = notification.getKey();
                                String value = notification.getValue();
                                System.out.println("removing..." + key + ":" + value);
                            }
                        }, executor))
                .build(
                        new CacheLoader<Integer, String>() {
                            @Override
                            public String load(Integer key) throws Exception {
                                System.out.println("loading..." + key);
                                return loadData(key);
                            }
                        }
                );


        try {
            String result = cache.get(4, new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return "z";
                }
            });
            System.out.println(cache.asMap());

            cache.get(1);

            cache.invalidateAll();

        } catch (ExecutionException e) {
            System.out.println("exception !!");
            e.printStackTrace();
        }

    }


    public static String loadData(Integer key) {
        return database.get(key);
    }
}
