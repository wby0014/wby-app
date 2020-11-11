package sort;

import java.util.Arrays;

/**
 * @Description 自定向下的归并
 * @Date 2020/11/11 14:48
 * @Author wuby31052
 */
public class MergeSort {

    private static int[] aux;

    public static void mergeSort(int[] arr, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        mergeSort(arr, lo, mid);
        mergeSort(arr, mid + 1, hi);
        merge(arr, lo, mid, hi);
    }

    private static void merge(int[] arr, int lo, int mid, int hi) {
        int l = lo;
        int r = mid + 1;
        for (int k = lo; k <= hi; k++) {
            aux[k] = arr[k];
        }
        for (int i = lo; i <= hi; i++) {
            if (r > hi) {
                arr[i] = aux[l++];
            } else if (l > mid) {
                arr[i] = aux[r++];
            } else if (aux[l] > aux[r]) {
                arr[i] = aux[r++];
            } else {
                arr[i] = aux[l++];
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 4, 1, 9, 5, 6, 2, 7, 0, 8};
        aux = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

}
