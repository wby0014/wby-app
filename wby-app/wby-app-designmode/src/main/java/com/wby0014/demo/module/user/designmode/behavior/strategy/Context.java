package com.wby0014.demo.module.user.designmode.behavior.strategy;

/**
 * 环境类
 *
 * @author wubinyu
 * @date 2019/10/29 21:58.
 */
public class Context {
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void operate() {
        this.strategy.operate();
    }
}
