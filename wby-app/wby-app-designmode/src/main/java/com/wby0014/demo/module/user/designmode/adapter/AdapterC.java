package com.wby0014.demo.module.user.designmode.adapter;

/**
 * 适配器类，类的适配模式
 *
 * @author wubinyu
 * @date 2019/10/30 14:48.
 */
public class AdapterC extends Adaptee implements Target {

    @Override
    public void request() {
        super.specificRequest();
    }
}
