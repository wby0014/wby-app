package number2;

import java.lang.ref.SoftReference;

/**
 * 关联索引优先队列
 *
 * @author wubinyu
 * @date 2019/1/30 16:27.
 */
public class IndexPriorityQueue<key extends Comparable<key>> {

    private int N = 0; //pq中的元素数量
    private int[] pq; //索引二叉堆，由1开始
    private int[] qp; //逆序 qp[pq[i]] = pq[qp[i]]=i
    // qp存的是pq值的索引，主要用于pq的排序见方法change()
    private key[] keys; //有优先级之分的元素

    public IndexPriorityQueue(int maxN) {
        keys = (key[]) new Comparable[maxN + 1];
        pq = new int[maxN + 1];
        qp = new int[maxN + 1];
        for (int i = 0; i <= maxN; i++) {
            qp[i] = -1;
        }
    }

    //上浮
    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    //下沉
    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) {
                j++;
            }

            if (!less(k, j)) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public boolean contains(int k) {
        return qp[k] != -1;
    }

    public int size() {
        return N;
    }

    /**
     * 使keys[] 按升序排列，则对应的移除最小元素
     *
     * @param i
     * @param j
     * @return
     */
    private boolean less(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    private void exch(int i, int j) {
        //pq[]数组中的qp[i]和pq[j]交换
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        //同时更新qp[]数组，保证qp[]是pq[]的逆序
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }


    public synchronized void test() {

    }

    public static synchronized void test3() {

    }

    public void test2() {
        synchronized (IndexPriorityQueue.class){

        }
        Object obj = new Object();
        synchronized (obj){

        }
    }




    public static void main(String[] args) {
        SoftReference<Integer> softReference = new SoftReference<>(1);


    }

}
