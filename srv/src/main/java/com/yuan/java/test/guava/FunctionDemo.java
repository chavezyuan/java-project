package com.yuan.java.test.guava;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

import java.util.Iterator;
import java.util.List;

/**
 * Desc :
 * Author : chavezyuan
 * Date : 2016-08-16
 */
public class FunctionDemo {

    public static void main(String[] args) {
        functionTest();
    }

    public static void functionTest() {

        List<String> list = Lists.newArrayList("c", "a", "b", "a", "a");

//        Iterable<String> iterable = Iterables.filter(list, new Predicate<String>() {
//            @Override
//            public boolean apply(String input) {
//                return input.equals("a");
//            }
//        });
//
//        Iterator<String> iterator = iterable.iterator();
//        while(iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }


        /**
         * FluentIterable 用于函数式操作Iterable
         */
        List<String> upperCaseList = FluentIterable.from(list).filter(new Predicate<String>() {
            @Override
            public boolean apply(String input) {
                return input.equals("a");
            }
        }).transform(new Function<String, String>() {

            @Override
            public String apply(String input) {
                return input.toUpperCase();
            }
        }).toList();

        System.out.println(upperCaseList);

    }
}
