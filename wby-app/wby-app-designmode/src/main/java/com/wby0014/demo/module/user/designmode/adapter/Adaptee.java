package com.wby0014.demo.module.user.designmode.adapter;

/**
 * 已存在的，具有特殊功能、但不符合我们既有的标准接口的类
 *
 * @author wubinyu
 * @date 2019/10/30 14:49.
 */
public class Adaptee {

    public void specificRequest() {
        System.out.println("被适配类具有特殊功能。。。");
    }
}
