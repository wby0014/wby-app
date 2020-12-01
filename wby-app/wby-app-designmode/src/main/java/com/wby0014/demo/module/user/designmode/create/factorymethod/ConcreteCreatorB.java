package com.wby0014.demo.module.user.designmode.create.factorymethod;

/**
 * @author wubinyu
 * @date 2019/10/30 14:01.
 */
public class ConcreteCreatorB implements Creator {
    @Override public Product createProduct() {
        return new ConcreteProductB();
    }
}
