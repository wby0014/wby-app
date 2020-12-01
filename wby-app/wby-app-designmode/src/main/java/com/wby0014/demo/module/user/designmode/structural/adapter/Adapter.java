package com.wby0014.demo.module.user.designmode.structural.adapter;

/**
 * 适配器类，对象的组合方式
 *
 * @author wubinyu
 * @date 2019/10/30 14:55.
 */
public class Adapter implements Target {
    private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        this.adaptee.specificRequest();
    }
}
