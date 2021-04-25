package sort;

import java.util.Arrays;

/**
 * @Description 冒泡排序
 * 就是重复"从序列右边开始比较相邻两个数字的大小，再根据结果交换两个数字的位置"这一操作的算法
 * @Date 2020/11/10 16:13
 * @Author wuby31052
 */
public class BubbleSort {
    private static int x =1;

    public static void bubbleSort(int[] arr) {
        if (arr.length <= 1) {
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
