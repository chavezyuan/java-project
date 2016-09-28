package com.yuan.java.test.guava;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import java.util.List;

/**
 * Desc :
 * Author : chavezyuan
 * Date : 2016-08-17
 */
public class StringDemo {

    public static void main(String[] args) {
        stringTest();
    }

    public static void stringTest() {

        String result = Joiner.on(";").join("abc", "def");

        System.out.println(result);

        List<String> list = Splitter.on(";").trimResults().splitToList(result);

        System.out.println(list);


        System.out.println(CharMatcher.JAVA_LOWER_CASE.collapseFrom("aBbBcRRR", 'A'));
    }
}
