package com.yuan.java.test.desigin.observe;

import java.util.List;

/**
 * Desc :
 * Author : chavezyuan
 * Date : 2016-06-30
 */
public abstract class Station {

    List<Display> observers;

    public List<Display> getObservers() {
        return observers;
    }

    public void setObservers(List<Display> observers) {
        this.observers = observers;
    }

    public void registerObserver(Display display) {
        observers.add(display);
    }
}
