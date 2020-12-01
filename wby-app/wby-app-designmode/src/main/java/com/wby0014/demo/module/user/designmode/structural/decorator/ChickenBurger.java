package com.wby0014.demo.module.user.designmode.structural.decorator;

/**
 * 鸡腿堡类，被装饰者初始状态
 *
 * @author wubinyu
 * @date 2019/10/30 15:06.
 */
public class ChickenBurger extends Humburger {

    public ChickenBurger() {
        name = "鸡腿堡";
    }

    @Override
    protected double getPrice() {
        return 10;
    }
}
