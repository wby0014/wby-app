package com.wby0014.demo.module.user.designmode.strategy;

/**
 * @author wubinyu
 * @date 2019/10/29 21:55.
 */
public class ConcreteStrategyA implements Strategy {
    @Override public void operate() {
        System.out.println("具体实现策略A");
    }
}
