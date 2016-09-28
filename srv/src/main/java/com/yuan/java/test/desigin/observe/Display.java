package com.yuan.java.test.desigin.observe;

/**
 * Desc :
 * Author : chavezyuan
 * Date : 2016-06-30
 */
public abstract class Display {


    public void register(Station station) {
        station.getObservers().add(this);
    }


}
