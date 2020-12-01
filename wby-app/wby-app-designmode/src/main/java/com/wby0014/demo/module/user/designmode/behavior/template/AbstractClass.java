package com.wby0014.demo.module.user.designmode.behavior.template;

/**
 * @author wubinyu
 * @date 2019/10/30 16:47.
 */
public abstract class AbstractClass {

    protected abstract void templateMethod();

    public void handle() {
        System.out.println("do something");
        templateMethod();
    }

}
