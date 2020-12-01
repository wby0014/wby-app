package com.wby0014.demo.module.user.designmode.structural.proxy;

/**
 * @author wubinyu
 * @date 2019/10/30 16:13.
 */
public class Proxy implements Subject {
    private RealSubject realSubject;

    @Override
    public void request() {
        System.out.println("代理类");
        realSubject.request();
    }
}
