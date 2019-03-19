package number1;

import java.util.Iterator;

/**
 * 使用链表实现下压栈 在头结点实现push() 和 pop()操作
 *
 * @author wubinyu
 * @date 2019/1/17 18:55.
 */
public class Stack<Item> implements Iterable<Item> {

    private Node first; //头结点
    private int N;      // 栈中元素个数

    private class Node {
        Item item;
        Node next;
    }

    public void push(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;
    }

    public Item pop() {
        Item item = first.item;
        first = first.next;
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
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current !=null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current =current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
    }
}
