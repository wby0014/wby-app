package com.wby0014.demo.module.user.designmode.builder;

/**
 * 建造接口
 *
 * @author wubinyu
 * @date 2019/10/30 11:33.
 */
public interface Builder {

    void buildHead();
    void buildBody();
    void buildFoot();
    Person buildPerson();
}
