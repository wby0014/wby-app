package com.wby0014.demo.module.user.designmode.observer;

/**
 * @author wubinyu
 * @date 2019/10/30 17:22.
 */
public class Client {

    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();
        ConcreteObserverA a = new ConcreteObserverA();
        ConcreteObserverB b = new ConcreteObserverB();
        subject.Attach(a);
        subject.Attach(b);
        subject.Notify();
    }
}
