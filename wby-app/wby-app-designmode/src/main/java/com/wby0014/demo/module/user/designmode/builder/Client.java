package com.wby0014.demo.module.user.designmode.builder;

/**
 * 客户端
 *
 * @author wubinyu
 * @date 2019/10/30 11:44.
 */
public class Client {

    public static void main(String[] args) {
        Director director = new Director();
        Person man = director.constructPerson(new ManBuilder());
        Person woman = director.constructPerson(new WomanBuilder());
    }
}
