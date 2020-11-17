package com.wby0014.lock.sync;

/**
 * @Description 重量级锁使用demo
 * @Date 2020/11/17 16:22
 * @Author wuby31052
 */
public class SynchronizedDemo {

    String obj = new String();

    int i = 1;

    /**
     * 修饰成员方法
     *
     * @return
     */
    public synchronized String get() {
        return "demo";
    }

    /**
     * 修饰静态方法
     */
    public synchronized static void test() {
        return;
    }

    /**
     * 修饰代码块
     *
     * @return
     */
    public int demo() {
        synchronized (obj) {
            i = i + 1;
        }
        synchronized (SynchronizedDemo.class) {
            i = i + 1;
        }
        synchronized (this) {
            i = i + 1;
        }
        return i;
    }


}
