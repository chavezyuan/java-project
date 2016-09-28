package com.yuan.java.test.serialization;

import java.io.*;

/**
 * Desc :
 * Author : chavezyuan
 * Date : 2016-08-09
 */
public class SerializationDemo {

    public static void main(String[] args) {
        Person person = Person.Builder.builder()
                .setName("yuan").setAge(26).setGender(Gender.MALE)
                .build();

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("~/test/person.txt"))) {

            oos.writeObject(person);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}