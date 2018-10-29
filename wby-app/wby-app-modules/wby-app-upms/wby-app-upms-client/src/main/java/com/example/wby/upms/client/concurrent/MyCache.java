package com.example.wby.upms.client.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁  内部使用同一个整数变量表示锁的状态，16位给读锁用，16位给写锁用。使用一个变量便于进行CAS
 * @写锁的获取 需确保所有线程没有持有锁，
 * @写锁释放后 唤醒等待队列中的第一个线程，唤醒的可能是等待读锁或等待写锁
 * @读锁的获取 只要保证没有线程持有写锁. 获取到读锁后，他会检查等待队列，逐个唤醒最前面的等待读锁的线程
 * 直到第一个等待写锁的线程。
 * @读锁释放后 检查读锁和写锁数是否都变为了0，如果是，唤醒等待队列中的下一个线程。
 *
 *
 */
public class MyCache {
    private Map<String, Object> map = new HashMap<>();
    private ReentrantReadWriteLock readWriteLock =  new ReentrantReadWriteLock();
    private Lock readLock = readWriteLock.readLock();
    private Lock writeLock = readWriteLock.writeLock();

    public Object get(String key) {
        readLock.lock();
        try{
            return map.get(key);
        }finally {
            readLock.unlock();
        }
    }

    public Object put(String key, Object value) {
        writeLock.lock();
        try{
            return map.put(key, value);
        }finally {
            writeLock.unlock();
        }
    }

    public void clear(){
        writeLock.lock();
        try{
            map.clear();
        }finally {
            writeLock.unlock();
        }
    }
}
