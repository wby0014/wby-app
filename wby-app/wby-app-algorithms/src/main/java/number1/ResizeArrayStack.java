package number1;

import java.util.Iterator;

/**
 * p88 下压栈（能动态调节数组的大小） 实现iterable接口则该对象支持foreach
 *
 * @author wubinyu
 * @date 2019/1/17 14:30.
 */
public class ResizeArrayStack<Item> implements Iterable<Item> {

    private Item[] a = (Item[]) new Object[1];
    private int N;  //记录元素个数

    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    public void push(Item item) {
        if (N == a.length) {
            resize(a.length * 2);
        }
        a[N++] = item;
    }

    public Item pop() {
        Item item = a[--N];
        a[N] = null; //避免对象游离
        if (N > 0 && N == a.length / 4) {
            resize(a.length / 2);
        }
        return item;
    }


    @Override
    public Iterator<Item> iterator() {
        return new RecverseArrayIterator();
    }

    private class RecverseArrayIterator implements Iterator<Item> {

        private int i = N;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            return a[--i];
        }

    }

    public static void main(String[] args) {
        ResizeArrayStack<Integer> s = new ResizeArrayStack<>();
        for (int i = 0; i < 10; i++) {
            s.push(i);
        }
        for (Integer integer : s) {
            System.out.println("foreach:" + integer);
        }

        Iterator<Integer> iterator = s.iterator();
        while (iterator.hasNext()) {
            System.out.println("iterator:" + iterator.next());
        }
    }

}
