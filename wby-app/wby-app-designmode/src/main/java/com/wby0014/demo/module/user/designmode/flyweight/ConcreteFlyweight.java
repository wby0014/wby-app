package com.wby0014.demo.module.user.designmode.flyweight;

/**
 * @author wubinyu
 * @date 2019/10/30 16:35.
 */
public class ConcreteFlyweight implements Flyweight {
    private String string;

    public ConcreteFlyweight(String string) {
        this.string = string;
    }

    @Override public void operation() {
        System.out.println("concrete ---Flyweight :" + string);
    }
}
