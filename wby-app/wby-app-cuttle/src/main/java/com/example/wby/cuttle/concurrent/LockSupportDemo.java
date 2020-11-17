package com.example.wby.cuttle.concurrent;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(){
            public void run() {
                LockSupport.park();   // 放弃cpu
                System.out.println("exit");
            }
        };
        thread.start();  //启动子线程
        Thread.sleep(1000);  //睡眠1秒确保子线程先运行
        LockSupport.unpark(thread);
   }
}
