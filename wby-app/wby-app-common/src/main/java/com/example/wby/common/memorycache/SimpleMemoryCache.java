package com.example.wby.common.memorycache;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class SimpleMemoryCache<K,V> {

    private final Lock lock = new ReentrantLock();
    private final int maxCapacity;
    private final Map<K,V> eden;
    private final Map<K,V> longterm;

    public SimpleMemoryCache(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.eden = new ConcurrentHashMap<>(maxCapacity);
        this.longterm = new WeakHashMap<>(maxCapacity);
    }

    public Map<K, V> getEdenMap() {
        return this.eden;
    }

    public Map<K, V> getLongTermMap() {
        this.lock.lock();
        Map kvMap;
        try {
            kvMap = this.longterm;
        } finally {
            this.lock.unlock();
        }
        return kvMap;
    }

    public void remove(List<String> keyList) {
        if (keyList != null && keyList.size() > 0) {
            Iterator its = keyList.iterator();
            while(its.hasNext()) {
                String key = (String)its.next();
                this.remove(key);
            }
        }
    }

    public V get(K k) {
        V v = this.eden.get(k);
        if (v == null) {
            this.lock.lock();
            try {
                v = this.longterm.get(k);
            } finally {
                this.lock.unlock();
            }
            if (v != null) {
                this.eden.put(k, v);
            }
        }
        return v;
    }

    public void put(K k, V v) {
        if (this.eden.size() >= this.maxCapacity) {
            this.lock.lock();
            try {
                this.longterm.putAll(this.eden);
            } finally {
                this.lock.unlock();
            }
            this.eden.clear();
        }
        this.eden.put(k, v);
    }

    public void remove(String key) {
        this.lock.lock();
        try {
            this.longterm.remove(key);
            this.eden.remove(key);
        } finally {
            this.lock.unlock();
        }
    }

    public void clear() {
        this.lock.lock();
        try {
            this.longterm.clear();
            this.eden.clear();
        } finally {
            this.lock.unlock();
        }
    }

}
