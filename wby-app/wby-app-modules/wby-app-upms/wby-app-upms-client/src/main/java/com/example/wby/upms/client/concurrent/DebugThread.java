package com.example.wby.upms.client.concurrent;

/**
 * @author wubinyu
 * @date 2019/3/8 15:17.
 */
public class DebugThread {

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            System.out.println("1");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("2");
        }).start();

        new Thread(() -> {
            System.out.println("3");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("4");
        }).start();

        System.out.println("5");
        Thread.sleep(2000);
        System.out.println("6");
    }
}
