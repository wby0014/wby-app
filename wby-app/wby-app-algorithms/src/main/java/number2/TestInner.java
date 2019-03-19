package number2;

import java.io.Serializable;

/**
 * 1、内部类方法可以访问该类定义所在的作用域的数据，包括私有的数据
 * 2、内部类可以对同一个包中的其他类隐藏起来，一般的非内部类，是不允许有private与protected权限的，但内部类可以
 * 3、可以实现多重继承
 * 4、当想要定义一个回调函数且不想编写大量代码时，使用匿名内部类比较便捷
 *
 * @author wubinyu
 * @date 2019/2/25 16:00.
 */
public class TestInner extends SituMerge implements Serializable {

    private class Node extends Heap {

    }

    protected class Sync extends IndexPriorityQueue implements Serializable {

        public Sync(int maxN) {
            super(maxN);
        }
    }

}
