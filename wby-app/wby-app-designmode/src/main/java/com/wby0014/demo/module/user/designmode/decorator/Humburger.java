package com.wby0014.demo.module.user.designmode.decorator;

/**
 * 被装饰者，汉堡基类
 *
 * @author wubinyu
 * @date 2019/10/30 15:05.
 */
public abstract class Humburger {

    protected String name;

    public String getName() {
        return name;
    }

    protected abstract double getPrice();
}
