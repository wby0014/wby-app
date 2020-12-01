package com.wby0014.demo.module.user.designmode.structural.decorator;

/**
 * @author wubinyu
 * @date 2019/10/30 15:33.
 */
public class Client {

    public static void main(String[] args) {
        Humburger humburger = new ChickenBurger();
        System.out.println(humburger.getName() + "" + humburger.getPrice());
        Lettuce lettuce = new Lettuce(humburger);
        System.out.println(lettuce.getName() + " " + lettuce.getPrice());
        Chilli chilli = new Chilli(humburger);
        System.out.println(chilli.getName() + " " + chilli.getPrice());
        Chilli chilli1 = new Chilli(lettuce);
        System.out.println(chilli1.getName() + " " + chilli1.getPrice());
    }
}
