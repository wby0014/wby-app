package com.example.wby.cuttle.concurrent;

public class Counter {

    static int count;

    public synchronized void incr() {
        count++;
    }

    public synchronized int getCount() {
        return count;
    }

}
