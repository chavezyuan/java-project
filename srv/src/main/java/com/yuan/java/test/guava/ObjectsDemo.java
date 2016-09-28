package com.yuan.java.test.guava;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

/**
 * Desc :
 * Author : chavezyuan
 * Date : 2016-08-15
 */
public class ObjectsDemo extends Object{
    public static void main(String[] args) {

        Person person = new Person();
        person.age = 10;
        person.name = "Yuan";

        System.out.println(person.toString());
    }

    static class Person implements Comparable<Person> {
        String name;
        Integer age;

//        @Override
//        public boolean equals(Object obj) {
//            return Objects.equal(this, obj);
//        }

        @Override
        public int hashCode() {
            return Objects.hashCode(this.name, this.age);
        }

        @Override
        public String toString() {

            MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(this);
            helper.add("name", this.name);
            helper.add("age", this.age);
            return helper.toString();
        }

        @Override
        public int compareTo(Person o) {
            return ComparisonChain.start()
                    .compare(this.name, o.name)
                    .compare(this.age, o.age).result();
        }
    }
}
