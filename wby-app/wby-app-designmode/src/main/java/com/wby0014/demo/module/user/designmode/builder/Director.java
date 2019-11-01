package com.wby0014.demo.module.user.designmode.builder;

/**
 * 导演者
 *
 * @author wubinyu
 * @date 2019/10/30 11:42.
 */
public class Director {

    public Person constructPerson(Builder builder) {
        builder.buildHead();
        builder.buildBody();
        builder.buildFoot();
        return builder.buildPerson();
    }
}
