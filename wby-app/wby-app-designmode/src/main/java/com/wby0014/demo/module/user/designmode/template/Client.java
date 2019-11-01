package com.wby0014.demo.module.user.designmode.template;

/**
 * @author wubinyu
 * @date 2019/10/30 16:52.
 */
public class Client {

    public static void main(String[] args) {
        new AbstractClass() {
            @Override
            protected void templateMethod() {
                System.out.println("交由子类去扩展新的行为");
            }
        }.handle();
    }
}
