package number1;

/**
 * 单链表的反转
 *
 * @author wubinyu
 * @date 2019/1/17 14:59.
 */
public class ReverseLinkedList {


    static class Node<T> {
        T item;
        Node next = null;

        public Node(T item) {
            this.item = item;
        }
    }

    //迭代实现链表反转
    public static Node reverse(Node x) {
        Node first = x;
        Node reverse = null;
        while (first != null) {
            Node second = first.next;
            first.next = reverse;
            reverse = first;
            first = second;
        }
        return reverse;
    }

    //递归实现链表反转
    public static Node reverseGcd(Node first) {
        if (first == null) {
            return null;
        }
        if (first.next == null) {
            return first;
        }
        Node second = first.next;
        Node rest = reverseGcd(second);
        second.next = first;
        first.next = null;
        return rest;
    }

    public static void main(String[] args) {
        Node<Integer> node = new Node<>(1);
        node.next = new Node(2);
        node.next.next = new Node(3);
//        System.out.println(reverse(node).item);
        System.out.println(reverseGcd(node).item);
    }

}
