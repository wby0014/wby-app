package com.example.wby.cuttle.concurrency.condition;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wubinyu
 */
public class ConditionDemo {

    private LinkedList<String> buffer;
    private int maxSize;
    private Lock lock;
    private Condition notEmptyCondition;
    private Condition notFullCondition;

    ConditionDemo(int maxSize) {
        this.maxSize = maxSize;
        buffer = new LinkedList<>();
        lock = new ReentrantLock();
        notEmptyCondition = lock.newCondition();
        notFullCondition = lock.newCondition();
    }

    public void set(String string) {
        lock.lock();
        try {
            while (maxSize == buffer.size()) {
                notFullCondition.await();
            }
            buffer.add(string);
            System.out.println(Thread.currentThread().getName() + " producer:" + string);
            notEmptyCondition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public String get() {
        String poll = null;
        lock.lock();
        try {
            while (buffer.size() == 0) {
                notEmptyCondition.await();
            }
            poll = buffer.poll();
            System.out.println(Thread.currentThread().getName() + " consumer:" + poll);
            notFullCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return poll;
    }


    public static void main(String[] args) {
        ConditionDemo demo = new ConditionDemo(10);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> demo.get()).start();
        }
        for (int j = 0; j < 10; j++) {
            int finalJ = j;
            new Thread(() -> demo.set(finalJ + "")).start();
        }
    }

}
