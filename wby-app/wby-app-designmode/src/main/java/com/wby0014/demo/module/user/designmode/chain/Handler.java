package com.wby0014.demo.module.user.designmode.chain;

/**
 * @author wubinyu
 * @date 2019/10/30 19:23.
 */
public abstract class Handler {

    protected Handler successor;

    public abstract void handleRequest();

    public Handler getSuccessor() {
        return successor;
    }

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }
}
