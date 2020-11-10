package sort;

import java.util.Arrays;

/**
 * @Description
 * @Date 2020/11/10 17:01
 * @Author wuby31052
 */
public class InsertSort {

    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0 && (arr[j] < arr[j - 1]); j--) {
                int temp = arr[j - 1];
                arr[j - 1] = arr[j];
                arr[j] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {4, 1, 6, 8, 2, 9, 3, 7, 5};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
