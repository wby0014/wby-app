package sort;

import java.util.Arrays;

/**
 * @Description 首先会在序列中随机选择一个基准值，然后将除了基准值以外的数分为"比基准值小的数"和"比基准值大的数"这两个类别，
 * 再将其排列成以下形式：[比基准小的数]基准值[比基准大的数]
 * @Date 2020/11/10 10:29
 * @Author wuby31052
 */
public class QuickSort {

    // 快速排序的切分
    private static int partition(int[] a, int lo, int hi) {
        // 左右扫描指针
        int l = lo;
        int r = hi + 1;
        // 切分元素
        int v = a[lo];
        while (true) {
            // 如果a[L]小于v时，增大L
            while (a[++l] < v) {
                if (l == hi) {
                    break;
                }
            }
            // 如果a[R]大于v时，减小R
            while (v < a[--r]) {
                // if (R == lo)
                // break;
            }
            if (l >= r) {
                break;
            }
            swap(a, l, r);// 交换L,R的位置
        }
        swap(a, lo, r);// 将v=a[R]放入正确的位置
        return r;
    }

    private static void quickSort(int[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int j = partition(a, lo, hi);// 切分
        quickSort(a, lo, j - 1);// 左边排序
        quickSort(a, j + 1, hi);// 右边排序
    }


    // 交换元素位置
    public static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        int[] arr = {4, 10, 5, 2, 6, 3, 9, 2, 1, 8, 11, 0, 13, 7};
        quickSort(arr, 0, arr.length - 1);
        System.out.print(Arrays.toString(arr));
    }

}
