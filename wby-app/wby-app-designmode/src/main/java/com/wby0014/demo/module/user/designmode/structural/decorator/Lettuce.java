package com.wby0014.demo.module.user.designmode.structural.decorator;

/**
 * 生菜，装饰者的第一层
 *
 * @author wubinyu
 * @date 2019/10/30 15:30.
 */
public class Lettuce extends Condiment {
    Humburger humburger;

    public Lettuce(Humburger humburger) {
        this.humburger = humburger;
    }

    @Override public String getName() {
        return humburger.getName() + "加生菜";
    }

    @Override protected double getPrice() {
        return humburger.getPrice() + 1.5;
    }
}
