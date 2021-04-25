package sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @Description
 * @Date 2021/4/25 10:50
 * @Author wuby31052
 */
public class QuickSort3 {

    public static void quick(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    public static void quickSort(int[] arr, int l, int r) {
        if (l < r) {
            swap(arr, new Random().nextInt(r - l + 1) + l, r);
            int[] partition = partition(arr, l, r);
            quickSort(arr, l, partition[0] - 1);
            quickSort(arr, partition[1] + 1, r);
        }
    }

    /**
     * 分区的过程，整数数组 arr 的[L, R]部分上，使得：
     * 大于 arr[R] 的元素位于[L, R]部分的右边，但这部分数据不一定有序
     * 小于 arr[R] 的元素位于[L, R]部分的左边，但这部分数据不一定有序
     * 等于 arr[R] 的元素位于[L, R]部分的中间
     * 返回等于部分的第一个元素的下标和最后一个下标组成的整数数组
     */
    public static int[] partition(int[] arr, int L, int R) {
        int basic = arr[R];
        int less = L - 1;
        int more = R + 1;
        while (L < more) {
            if (arr[L] < basic) {
                swap(arr, ++less, L++);
            } else if (arr[L] > basic) {
                swap(arr, --more, L);
            } else {
                L++;
            }
        }
        return new int[]{less + 1, more - 1};
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{2,4,1,6,9,3,7,5,8,0};
        quick(arr);
        System.out.println(Arrays.toString(arr));
    }

}
