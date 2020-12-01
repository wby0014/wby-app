package com.example.wby.cuttle.concurrency.semaphore;

import java.util.concurrent.Semaphore;

/**
 * Semaphore 通常用于限制可以访问某些资源（物理或逻辑的）的线程数目
 * 一个计数信号量。从概念上讲，信号量维护了一个许可集。
 * 如有必要，在许可可用前会阻塞每一个 acquire，然后再获取该许可。
 * 每个 release 添加一个许可，从而可能释放一个正在阻塞的获取者。
 * 但是，不使用实际的许可对象，Semaphore 只对可用许可的号码进行计数，并采取相应的行动。
 *
 * @author wubinyu
 */
public class SemaphoreDemo {

    static class Parking {

        //信号量
        private Semaphore semaphore;

        Parking(int count) {
            semaphore = new Semaphore(count);
        }

        public void park() {
            try {
                //获取信号量
                semaphore.acquire();
                long time = (long) (Math.random() * 10);
                System.out.println(Thread.currentThread().getName() + "进入停车场，停车" + time + "秒...");
                Thread.sleep(time);
                System.out.println(Thread.currentThread().getName() + "开出停车场...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
    }


    static class Car extends Thread {
        Parking parking;

        Car(Parking parking) {
            this.parking = parking;
        }

        @Override
        public void run() {
            parking.park();     //进入停车场
        }
    }

    public static void main(String[] args) {
        Parking parking = new Parking(3);

        for (int i = 0; i < 5; i++) {
            new Car(parking).start();
        }
    }
}
