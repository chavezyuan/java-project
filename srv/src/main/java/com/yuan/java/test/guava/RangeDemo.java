package com.yuan.java.test.guava;

import com.google.common.collect.BoundType;
import com.google.common.collect.Range;

/**
 * Desc :
 * Author : chavezyuan
 * Date : 2016-08-17
 */
public class RangeDemo {

    public static void main(String[] args) {
//        rangeTest();
        rangeTimeTest();
    }


    public static void rangeTest() {

        Range<Integer> range = Range.openClosed(5, 5);

        Range<Integer> range1 = Range.closed(2, 5);

        Range<Integer> range2 = range.intersection(range1);



        BoundType type = range2.lowerBoundType();
        System.out.println(range2);

    }


    public static void rangeTimeTest() {

        Range<Time> range1 = Range.closed(new Time(10, 0, 0), new Time(20, 0, 0));
        Range<Time> range2 = Range.closed(new Time(8, 0, 0), new Time(22, 0, 0));

        range1 = range1.intersection(range2);

        System.out.println(range1);

    }

}
