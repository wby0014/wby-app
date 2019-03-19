package number1;

/**
 * P104 T31 双向链表 相当于简化版的LinkedList
 * 主要实现了
 * 从表头，表尾插入一个结点，
 * 在指定结点之前或之后插入一个结点，
 * 从表头，表尾删除一个结点，
 * 删除指定结点
 * 指定索引返回结点
 *
 * @author wubinyu
 * @date 2019/1/16 11:48.
 */
public class DoubleLinkedList {

    static class DoubleNode<T> {
        private int N;//记录元素个数
        private Node first;//头节点
        private Node last; //尾节点

        private class Node {
            T item;
            Node perv;//前一个节点
            Node next;//后一个节点

            @Override
            public String toString() {
                return "Node{" +
                        "item=" + item +
                        '}';
            }
        }

        //根据索引获取节点
        public Node getNode(int index) {
            if (index < 0 || index >= N) {
                throw new IndexOutOfBoundsException();
            }
            Node node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        }

        //判断是否为空
        public boolean isEmpty() {
            return N == 0;
        }

        //元素个数
        public int size() {
            return N;
        }

        //在表头插入节点
        public void pushFirst(T item) {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            if (isEmpty()) {
                last = first;
            } else {
                first.next = oldFirst;
                oldFirst.perv = first;
            }
            N++;
        }

        //在指定节点前添加新节点
        public void pushBefore(Node newNode, Node node) {
            newNode.next = node;
            newNode.perv = node.perv;
            newNode.next.perv = newNode;
            //防止在头结点前面插入新节点
            if (newNode.perv != null) {
                newNode.perv.next = newNode;
            }
            N++;
        }

        //在指定索引前插入新节点
        public void pushBeforeOfIndex(T item, int index) {
            Node node = getNode(index);
            Node newNode = new Node();
            newNode.item = item;
            if (index == 0) {
                first = newNode;
            }
            pushBefore(newNode, node);
        }

        // 从表尾插入
        public void pushLast(T item) {
            Node oldLast = last;
            last = new Node();
            last.item = item;
            last.next = null;
            if (isEmpty()) {
                first = last;
            } else {
                oldLast.next = last;
                last.perv = oldLast;
            }
            N++;
        }

        // 在指定节点后添加新节点
        public void pushAfter(Node newNode, Node node) {
            newNode.perv = node;
            newNode.next = node.next;
            newNode.perv.next = newNode;
            //防止在尾节点之后插入新节点
            if (newNode.next != null) {
                newNode.next.perv = newNode;
            }
            N++;
        }

        //在指定索引之后添加节点
        public void pushAfterOfIndex(T item, int index) {
            Node newNode = new Node();
            newNode.item = item;
            Node node = getNode(index);
            pushAfter(newNode, node);
        }

        // 从表头删除一个节点
        public void popFirst() {
            first = first.next;
            if (first != null) {
                first.perv = null;
            }
            N--;
        }

        //从表尾删除一个节点
        public void popLast() {
            last.perv.next = null;
            last.perv = null;
            N--;
        }

        //删除指定的节点
        public void pop(Node node) {
            node.perv.next = node.next;
            node.next.perv = node.perv;
            node.perv = null;
            node.next = null;
            N--;
        }

        //删除指定索引的节点
        public void popOfIndex(int index) {
            if (index == 0) {
                popFirst();
            } else if (index == N - 1) {
                popLast();
            } else {
                Node node = getNode(index);
                pop(node);
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            Node node = first;
            for (int i = 0; i < N; i++) {
                sb.append("[");
                sb.append(node);
                sb.append("]");
                sb.append(",");
                node = node.next;
            }
            return sb.toString();
        }

    }

    public static void main(String[] args) {
        DoubleNode<Integer> d = new DoubleNode<>();
        d.pushFirst(2);
        d.pushFirst(4);
        d.pushFirst(5);
        d.pushBeforeOfIndex(10, 0);
        System.out.println(d);
    }
}
