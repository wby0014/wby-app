package com.wby0014.demo.module.user.designmode.strategy;

/**
 * 客户端
 *
 * @author wubinyu
 * @date 2019/10/29 22:03.
 */
public class Client {

    public static void main(String[] args) {
        Context context;

        context = new Context(new ConcreteStrategyA());
        context.operate();

        context.setStrategy(new ConcreteStrategyB());
        context.operate();
    }
}
