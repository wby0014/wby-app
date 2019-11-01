package com.wby0014.demo.module.user.designmode.prototype;

import java.util.ArrayList;

/**
 * 深拷贝与浅拷贝。Object类的clone方法只会拷贝对象中的基本的数据类型 （8种基本数据类型byte,char,short,int,long,float,double，boolean），
 * 对于数组、容器对象、引用对象等都不会拷贝，这就是浅拷贝。如果要实现深拷贝， 必须将原型模式中的数组、容器对象、引用对象等另行拷贝。 ————————————————
 *
 * @author wubinyu
 * @date 2019/10/30 14:28.
 */
public abstract class Prototype implements Cloneable {
    /**
     * 需要额外拷贝
     */
    private ArrayList<String> list = new ArrayList<>();

    @Override
    public Prototype clone() {
        Prototype prototype = null;
        try {
            prototype = (Prototype) super.clone();
            prototype.list = (ArrayList<String>) this.list.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return prototype;
    }
}
