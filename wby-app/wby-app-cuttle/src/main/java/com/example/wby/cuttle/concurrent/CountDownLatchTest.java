package com.example.wby.cuttle.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch不能重新初始化或者修改内部计数器的值
 * 一个线程调用countDown方法happen-before 另外一个线程调用await方法
 */
public class CountDownLatchTest {
    static CountDownLatch c = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(1);
                c.countDown();
                System.out.println(2);
                c.countDown();
            }
        }).start();
        c.await();
        System.out.println(3);
    }
}
