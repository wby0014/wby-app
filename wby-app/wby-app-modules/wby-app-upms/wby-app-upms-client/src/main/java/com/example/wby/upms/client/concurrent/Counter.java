package com.example.wby.upms.client.concurrent;

public class Counter {

    static int count;

    public synchronized void incr() {
        count++;
    }

    public synchronized int getCount() {
        return count;
    }

}
