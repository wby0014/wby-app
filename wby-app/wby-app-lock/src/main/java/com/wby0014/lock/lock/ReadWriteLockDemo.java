package com.wby0014.lock.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description 读写锁使用demo
 * @Date 2020/11/17 16:14
 * @Author wuby31052
 */
public class ReadWriteLockDemo {

    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Map<String, String> map = new HashMap<>();
    private Lock readLock = readWriteLock.readLock();
    private Lock writeLock = readWriteLock.writeLock();

    public void set(String key, String val) {
        writeLock.lock();
        try {
            map.put(key, val);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    public String get(String key) {
        readLock.lock();
        try {
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public void clear() {
        writeLock.lock();
        try {
            map.clear();
        } finally {
            writeLock.unlock();
        }
    }

}
