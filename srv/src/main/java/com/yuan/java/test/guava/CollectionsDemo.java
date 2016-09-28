package com.yuan.java.test.guava;

import com.google.common.collect.*;

import java.util.*;

/**
 * Desc :
 * Author : chavezyuan
 * Date : 2016-08-15
 */
public class CollectionsDemo {

    public static void main(String[] args) {
//        immutableTest();
        multiTest();
    }

    public static void immutableTest() {


//        List<String> list = Lists.newArrayList("abc", "def");
//        ImmutableList<String> immutableList = ImmutableList.copyOf(list);

//        ImmutableList<String> immutableList = ImmutableList.<String>builder().add("abc").add("def").build();

        ImmutableList<String> immutableList = ImmutableList.of("abc", "efg");

        ListIterator<String> iterator = immutableList.listIterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }



        System.out.println(immutableList.toString());
    }

    public static void multiTest() {
//
//        ImmutableMultiset multiset = ImmutableMultiset.builder().add("abc").add("abc").add().build();
//
//        System.out.println(multiset.count("abc"));
//
//        Multimap<String, String> multimap = HashMultimap.create();
//
//        multimap.put("fruits", "apple");
//        multimap.put("fruits", "pear");
//        multimap.put("vegetables", "huacai");
//
//        Collection<String> fruits = multimap.get("fruits");
//
//        System.out.println(fruits.toString());
//
//
//        BiMap<Integer, String> biMap = HashBiMap.create();
//
//        biMap.put(1, "a");
//        biMap.put(2, "b");
//        biMap.put(3, "c");
//
//        System.out.println(biMap.toString());
//
//        BiMap<String, Integer> biMap1 = biMap.inverse();
//
//        System.out.println(biMap.toString());
//        System.out.println(biMap1.toString());
//
//        biMap1.put("z", 26);
//
//        System.out.println(biMap.toString());
//        System.out.println(biMap1.toString());


        Table<Integer, Integer, String> table = HashBasedTable.create();

        table.put(1, 3, "a");
        table.put(2, 4, "b");

        Map<Integer, String> column = table.column(4);
        System.out.println(column);


        Map<Integer, String> row = table.row(1);
        System.out.println(row);

        Set<Table.Cell<Integer, Integer, String>> cells = table.cellSet();

        System.out.println(cells);


    }
}
