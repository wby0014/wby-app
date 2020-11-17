package com.example.wby.cuttle.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockCounter {
    private final Lock lock = new ReentrantLock();
    private volatile int count;
    public void incr() {
        try {
            lock.lock();
            count++;
        } finally {
            lock.unlock();
        }
    }
    public int getCount() {
        return count;
    }

}
