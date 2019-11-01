package com.wby0014.demo.module.user.designmode.decorator;

/**
 * 装饰者的第二层
 *
 * @author wubinyu
 * @date 2019/10/30 15:31.
 */
public class Chilli extends Condiment {

    Humburger humburger;

    public Chilli(Humburger humburger) {
        this.humburger = humburger;
    }

    @Override public String getName() {
        return humburger.getName() + "加辣椒";
    }

    @Override protected double getPrice() {
        return humburger.getPrice();
    }
}
