package com.wby0014.demo.module.user.designmode.builder;

/**
 * 男人建造者
 *
 * @author wubinyu
 * @date 2019/10/30 11:36.
 */
public class ManBuilder implements Builder {
    Person person;

    public ManBuilder() {
        this.person = new Man();
    }

    @Override public void buildHead() {
        person.setHead("男人头部");
    }

    @Override public void buildBody() {
        person.setBody("男人身体");
    }

    @Override public void buildFoot() {
        person.setFoot("男人脚");
    }

    @Override public Person buildPerson() {
        return person;
    }
}
