package com.wby0014.demo.module.user.designmode.builder;

/**
 * 女人建造者
 *
 * @author wubinyu
 * @date 2019/10/30 11:40.
 */
public class WomanBuilder implements Builder {

    Person person;

    public WomanBuilder() {
        this.person = new Woman();
    }

    @Override public void buildHead() {
        person.setHead("女人头部");
    }

    @Override public void buildBody() {
        person.setBody("女人身体");
    }

    @Override public void buildFoot() {
        person.setFoot("女人脚");
    }

    @Override public Person buildPerson() {
        return person;
    }
}
