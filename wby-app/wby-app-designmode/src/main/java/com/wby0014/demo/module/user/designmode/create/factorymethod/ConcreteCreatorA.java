package com.wby0014.demo.module.user.designmode.create.factorymethod;

/**
 * @author wubinyu
 * @date 2019/10/30 13:58.
 */
public class ConcreteCreatorA implements Creator {

    @Override public Product createProduct() {
        return new ConcreteProductA();
    }
}
