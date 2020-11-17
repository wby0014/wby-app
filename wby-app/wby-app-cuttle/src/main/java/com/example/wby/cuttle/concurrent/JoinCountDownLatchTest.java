package com.example.wby.cuttle.concurrent;

/**
 * join用于让当前执行线程等待join线程执行结束
 * 其实原理是不停检查join线程是否存活，如果join线程存活则让当前线程永远等待，
 * 其中，wait(0)表示永远等待下去，
 * while(isAlive()){
 *     wait(0);
 * }
 * 直到join线程中止后，线程的this.notifyAll()方法会被调用,调用notifyAll()方法是在JVM里实现的。
 */
public class JoinCountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        Thread parser1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("11111");
            }
        });
        Thread parser2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("22222");
            }
        });
        parser1.start();
        parser2.start();
        parser1.join();
        parser2.join();
        System.out.println("all parser finish");
    }

}
