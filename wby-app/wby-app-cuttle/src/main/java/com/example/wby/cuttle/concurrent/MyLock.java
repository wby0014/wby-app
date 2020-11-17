package com.example.wby.cuttle.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 演示demo过于消耗cpu，可直接使用ReentrantLock
 */
public class MyLock {
    private AtomicInteger status = new AtomicInteger(0);
    public void lock() {
        while (!status.compareAndSet(0, 1)) {
            Thread.yield();
        }
    }
    public void unlock() {
        status.compareAndSet(1, 0);
    }
}
