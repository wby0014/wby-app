package sort;

import java.util.Arrays;

/**
 * @Description 选择排序
 * 就是重复"从待排序的数据中寻找最小值，将其与序列最左边的数字进行交换"这一操作的算法，在序列中寻找最小值时用的是线性查找
 * @Date 2020/11/11 13:56
 * @Author wuby31052
 */
public class SelectionSort {

    public static void selectionSort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    swap(arr, i, j);
                }
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {3, 5, 7, 8, 2, 1, 0, 4, 6, 9};
        selectionSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
