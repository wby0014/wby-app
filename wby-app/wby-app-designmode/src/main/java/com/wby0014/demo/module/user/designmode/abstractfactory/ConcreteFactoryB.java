package com.wby0014.demo.module.user.designmode.abstractfactory;

/**
 * @author wubinyu
 * @date 2019/10/30 14:18.
 */
public class ConcreteFactoryB implements AbstractFactory {
    @Override public Engine createProductA() {
        return new EngineB();
    }

    @Override public Aircondition createProductB() {
        return new AirconditionB();
    }
}
