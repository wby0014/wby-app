package com.wby0014.demo.module.user.designmode.behavior.observer;

/**
 * @author wubinyu
 * @date 2019/10/30 17:19.
 */
public class ConcreteObserverA implements Observer {
    @Override public void update() {
        System.out.println("A update");
    }
}
