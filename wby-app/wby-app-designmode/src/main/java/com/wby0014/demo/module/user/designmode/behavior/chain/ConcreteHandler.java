package com.wby0014.demo.module.user.designmode.behavior.chain;

/**
 * @author wubinyu
 * @date 2019/10/30 19:25.
 */
public class ConcreteHandler extends Handler {

    @Override
    public void handleRequest() {
        if (getSuccessor() != null) {
            System.out.println("放过请求");
            getSuccessor().handleRequest();
        } else {
            System.out.println("处理请求");
        }
    }
}
