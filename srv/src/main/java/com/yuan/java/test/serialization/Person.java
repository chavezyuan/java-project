package com.yuan.java.test.serialization;

import java.io.Serializable;

/**
 * Desc :
 * Author : chavezyuan
 * Date : 2016-08-09
 */
class Person implements Serializable {
    private String name;

    private int age;

    private Gender gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public static class Builder {

        private final Person person = new Person();

        public static Builder builder() {
            return new Builder();
        }

        public Person build() {
            return this.person;
        }

        public Builder setName(String name) {
            this.person.setName(name);
            return this;
        }

        public Builder setAge(int age) {
            this.person.setAge(age);
            return this;
        }

        public Builder setGender(Gender gender) {
            this.person.setGender(gender);
            return this;
        }
    }


}
