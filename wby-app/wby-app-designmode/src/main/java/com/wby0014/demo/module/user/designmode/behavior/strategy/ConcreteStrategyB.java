package com.wby0014.demo.module.user.designmode.behavior.strategy;

/**
 * @author wubinyu
 * @date 2019/10/29 21:56.
 */
public class ConcreteStrategyB implements Strategy {
    @Override public void operate() {
        System.out.println("具体实现策略B");
    }
}
