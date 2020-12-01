package com.wby0014.demo.module.user.designmode.behavior.observer;

/**
 * 被观察者
 *
 * @author wubinyu
 * @date 2019/10/30 17:16.
 */
public interface Subject {
    void Attach(Observer observer);

    void Detach(Observer observer);

    void Notify();
}
