package sort;

import java.util.Arrays;

/**
 * @Description 冒泡排序
 * @Date 2020/11/10 16:13
 * @Author wuby31052
 */
public class BubbleSort {

    public static void bubbleSort(int[] arr) {
        if (arr.length == 0 || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 5, 7, 2, 8, 4, 9, 6};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
