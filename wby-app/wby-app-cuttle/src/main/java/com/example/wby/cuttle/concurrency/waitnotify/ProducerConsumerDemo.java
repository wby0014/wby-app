package com.example.wby.cuttle.concurrency.waitnotify;

import java.util.LinkedList;

/**
 * @author wubinyu
 */
public class ProducerConsumerDemo {

    private LinkedList<String> buffer;
    private int maxSize;
    Object obj = new Object();

    ProducerConsumerDemo(int maxSize) {
        this.maxSize = maxSize;
        buffer = new LinkedList<>();
    }

    public void set(String string) {
        synchronized (obj) {
            try {
                while (maxSize == buffer.size()) {
                    obj.wait();
                }
                buffer.add(string);
                System.out.println(Thread.currentThread().getName() + " producer:" + string);
                obj.notify();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String get() {
        String poll = null;
        synchronized (obj) {
            try {
                while (buffer.size() == 0) {
                    obj.wait();
                }
                poll = buffer.poll();
                System.out.println(Thread.currentThread().getName() + " consumer:" + poll);
                obj.notify();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return poll;
    }

    public static void main(String[] args) {
        ProducerConsumerDemo demo = new ProducerConsumerDemo(10);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> demo.get()).start();
        }
        for (int j = 0; j < 10; j++) {
            int finalJ = j;
            new Thread(() -> demo.set(finalJ + "")).start();
        }
    }

}



