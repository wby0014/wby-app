package number1;

import java.util.Iterator;

/**
 * 用链表实现队列，表尾添加节点，表头删除节点
 *
 * @author wubinyu
 * @date 2019/1/17 11:38.
 */
public class Queue<Item> implements Iterable<Item> {

    private Node first; //头部
    private Node last; //尾部
    private int N;   //记录元素个数

    private class Node {
        Item item;
        Node next;
    }

    public void enqueue(Item item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        N++;
    }

    public Item dequeue() {
        Item item = first.item;
        first = first.next;
        if (isEmpty()) {
            last = null;
        }
        N--;
        return item;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    @Override
    public Iterator<Item> iterator() {
        return new listIterator();
    }

    private class listIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }


    }

    public static void main(String[] args) {
        Queue<String> queue = new Queue<>();
//        String[] str = {"1", "3", "4", "5"};
        for (String string : args) {
            if (!string.equals("-")) {
                queue.enqueue(string);
            } else if (!queue.isEmpty()) {
                System.out.println(queue.dequeue() + "");
            }
        }
        System.out.println("(" + queue.size() + "left on stack" + ")");
    }

}
