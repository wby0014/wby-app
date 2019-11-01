package com.wby0014.demo.module.user.designmode.abstractfactory;

/**
 * @author wubinyu
 * @date 2019/10/30 14:15.
 */
public class ConcreteFactoryA implements AbstractFactory {
    @Override public Engine createProductA() {
        return new EngineA();
    }

    @Override public Aircondition createProductB() {
        return new AirconditionA();
    }
}
