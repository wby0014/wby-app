package com.wby0014.demo.module.user.designmode.flyweight;

/**
 * 核心总结，可以共享的对象，也就是说返回的同一类型的对象其实是同一实例，当客户端要求生成一个对象时， 工厂会检测是否存在此对象的实例，如果存在那么直接返回此对象实例，如果不存在就创建一个并保存起来，
 * 这点有些单例模式的意思。通常工厂类会有一个集合类型的成员变量来用以保存对象，如hashtable,vector等。 在java中，数据库连接池，线程池等即是用享元模式的应用 ————————————————
 *
 * @author wubinyu
 * @date 2019/10/30 16:38.
 */
public class Client {

    FlyweightFactory factory = new FlyweightFactory();
    Flyweight google1;
    Flyweight baidu;
    Flyweight google2;
    Flyweight google3;
    Flyweight google4;
    Flyweight google5;

    public Client() {
        google1 = factory.getFlyweight("Google");
        baidu = factory.getFlyweight("Baidu");
        google2 = factory.getFlyweight("Google");
        google3 = factory.getFlyweight("Google");
        google4 = factory.getFlyweight("Google");
        google5 = factory.getFlyweight("Google");
    }

    public void showFlyweight() {
        google1.operation();
        baidu.operation();
        google2.operation();
        google3.operation();
        google4.operation();
        google5.operation();
        int size = factory.getFlyweightSize();
        System.out.println(size);
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.showFlyweight();
    }
}
