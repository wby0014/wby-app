package number2;

import java.util.Arrays;

/**
 * @Description P156 选择排序
 * @Date 2020/11/3 10:46
 * @Author wuby31052
 */
public class Selection {

    // 第一种实现
    public static void sort(int[] a) {
        // 将数组a按升序排
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[min]) {
                    // 交换索引，使min指向最小的元素下标
                    min = j;
                }
            }
            // 交换元素位置
            swap(a, i, min);
        }
    }

    // 第二种实现
    public static void selectionSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    swap(nums, i, j);
                }
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int a[] = {2, 3, 4, 1, 5, 8, 9, 6, 7};
//        sort(a);
        selectionSort(a);
        System.out.println(Arrays.toString(a));
    }

}