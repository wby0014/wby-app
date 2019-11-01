package com.wby0014.demo.module.user.designmode.proxy;

/**
 * @author wubinyu
 * @date 2019/10/30 16:13.
 */
public class RealSubject implements Subject {

    @Override
    public void request() {
        System.out.println("真正的主题");
    }
}
