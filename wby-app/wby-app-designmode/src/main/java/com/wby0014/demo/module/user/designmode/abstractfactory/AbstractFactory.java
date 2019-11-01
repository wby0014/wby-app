package com.wby0014.demo.module.user.designmode.abstractfactory;

/**
 * @author wubinyu
 * @date 2019/10/30 14:13.
 */
public interface AbstractFactory {

    Engine createProductA();

    Aircondition createProductB();
}
