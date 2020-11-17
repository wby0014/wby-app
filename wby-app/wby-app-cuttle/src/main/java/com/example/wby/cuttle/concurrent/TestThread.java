package com.example.wby.cuttle.concurrent;

import java.util.ArrayList;

/**
 * 多线程调试
 * 1.在ArrayList的add方法打断点
 * 设置断点condition：((!(Thread.currentThread().getName().equals("main"))) && size == 9)
 */
public class TestThread {
    static ArrayList<String> a1 = new ArrayList<>();

    static class AddTask implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 10000000; i++) {
                a1.add(new String());
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new AddTask(), "t1");
        Thread t2 = new Thread(new AddTask(), "t2");
        t1.start();
        t2.start();
        Thread t3 = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t3");
        t3.start();
    }
}
