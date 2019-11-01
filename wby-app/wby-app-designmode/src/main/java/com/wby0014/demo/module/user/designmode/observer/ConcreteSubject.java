package com.wby0014.demo.module.user.designmode.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wubinyu
 * @date 2019/10/30 17:18.
 */
public class ConcreteSubject implements Subject {

    List<Observer> list = new ArrayList<>();

    @Override
    public void Attach(Observer observer) {
        list.add(observer);
    }

    @Override public void Detach(Observer observer) {
        list.remove(observer);
    }

    @Override public void Notify() {
        for (Observer observer : list) {
            observer.update();
        }
    }
}
