package com.yuan.java.test.guava;

import com.google.common.base.Preconditions;

/**
 * Desc :
 * Author : chavezyuan
 * Date : 2016-08-15
 */
public class PreconditionsDemo {

    public static void main(String[] args) {
        test();
    }

    public static void test() {
//        Preconditions.checkArgument(b);

//        Preconditions.checkNotNull(null, "arg is null");


        Preconditions.checkState(true);

        System.out.println("pass");


    }

}
