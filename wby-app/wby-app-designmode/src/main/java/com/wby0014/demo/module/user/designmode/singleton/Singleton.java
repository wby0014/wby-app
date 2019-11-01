package com.wby0014.demo.module.user.designmode.singleton;

/**
 * 单例模式
 *
 * @author wubinyu
 * @date 2019/10/30 10:47.
 */
public class Singleton {

    private Singleton() {
    }

    private static Singleton singleton = null;

    /**
     * 双重加锁写法
     *
     * @return
     */
    public static synchronized Singleton getSingleton() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

    private static class LazyHolder {
        private static final Singleton instance = new Singleton();
    }

    /**
     * 静态内部类写法
     *
     * @return
     */
    public static final Singleton getInstance() {
        return LazyHolder.instance;
    }
}
