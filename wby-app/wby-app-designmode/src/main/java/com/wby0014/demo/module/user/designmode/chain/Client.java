package com.wby0014.demo.module.user.designmode.chain;

/**
 * @author wubinyu
 * @date 2019/10/30 19:26.
 */
public class Client {

    public static void main(String[] args) {
        Handler handler1 = new ConcreteHandler();
        Handler handler2 = new ConcreteHandler();
        handler1.setSuccessor(handler2);

        handler1.handleRequest();
    }
}
