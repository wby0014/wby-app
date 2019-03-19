package number1;

/**
 * p84 一种表示泛型定容栈的抽象数据类型
 *
 * @author wubinyu
 * @date 2019/1/17 11:11.
 */
public class FixedCapacityStack<T> {
    private T[] a;   //stack entries
    private int N;   // size
    private int cap; //数组申请的长度

    public FixedCapacityStack(int cap) {
        this.cap = cap;
        a = (T[]) new Object[cap];
    }

    void push(T item) {
        a[N++] = item;
    }

    T pop() {
        return a[--N];
    }

    boolean isEmpty() {
        return N == 0;
    }

    int size() {
        return N;
    }

    boolean isFull() {
        return N == cap;
    }

    public static void main(String[] args) {
        String[] str = {"ss", "sdsdfasdf", "kgdsakg"};
        args = str;
        FixedCapacityStack<String> f = new FixedCapacityStack<>(10);
        for (String string : args) {
            if (!string.equals("-")) {
                f.push(string);
            } else if (!f.isEmpty()) {
                System.out.println(f.pop() + "");
            }
        }
        System.out.println("是否已满？" + f.isFull());
        System.out.println("(" + f.size() + "left on stack" + ")");
    }


}
