package com.wby0014.demo.module.user.designmode.structural.flyweight;

import java.util.Hashtable;

/**
 * @author wubinyu
 * @date 2019/10/30 16:33.
 */
public class FlyweightFactory {
    private Hashtable flyweights = new Hashtable();

    public FlyweightFactory() {
    }

    public Flyweight getFlyweight(Object obj) {
        Flyweight flyweight = (Flyweight) flyweights.get(obj);
        if (flyweight == null) {
            flyweight = new ConcreteFlyweight((String) obj);
            flyweights.put(obj, flyweight);
        }
        return flyweight;
    }

    public int getFlyweightSize() {
        return flyweights.size();
    }
}
