package sort;

import java.util.Arrays;

/**
 * @Description 快速排序, 分治思想
 * 首先会在序列中随机选择一个基准值，然后将除了基准值以外的数分为"比基准值小的数"和"比基准值大的数"这两个类别，
 * 再将其排列成以下形式：[比基准小的数]基准值[比基准大的数]
 * @Date 2020/11/10 14:21
 * @Author wuby31052
 */
public class QuickSort2 {

    public static void quickSort(int[] arr, int startIndex, int endIndex) {
        // 递归结束条件：startIndex大等于endIndex的时候
        if (startIndex >= endIndex) {
            return;
        }
        // 得到基准元素位置
        int pivotIndex = partition(arr, startIndex, endIndex);
        // 用分治法递归数列的两部分
        quickSort(arr, startIndex, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, endIndex);
    }

    public static void quickSort2(int[] arr, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }
        int pivot = partition2(arr, startIndex, endIndex);
        quickSort2(arr, startIndex, pivot - 1);
        quickSort2(arr, pivot + 1, endIndex);
    }

    private static int partition2(int[] arr, int startIndex, int endIndex) {
        // 基准元素
        int pivot = arr[startIndex];
        // 左右指针位置
        int left = startIndex;
        int right = endIndex + 1;
        while (right >= left) {
            while (arr[++left] < pivot) {

            }
            while (arr[--right] > pivot) {

            }
            if (left >= right) {
                break;
            }
            swap(arr, left, right);
        }
        // 把基准元素放到此时的arr[right]位置
        swap(arr, startIndex, right);
        // 此时right指针等于基准元素位置
        return right;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    private static int partition(int[] arr, int startIndex, int endIndex) {
        // 取第一个位置的元素作为基准元素
        int pivot = arr[startIndex];
        int left = startIndex;
        int right = endIndex;
        // 坑的位置，初始等于pivot的位置
        int index = startIndex;
        // 大循环在左右指针重合或者交错时结束
        while (right >= left) {
            // right指针从右向左进行比较
            while (right >= left) {
                if (arr[right] < pivot) {
                    arr[left] = arr[right];
                    index = right;
                    left++;
                    break;
                }
                right--;
            }
            // left指针从左向右进行比较
            while (right >= left) {
                if (arr[left] > pivot) {
                    arr[right] = arr[left];
                    index = left;
                    right--;
                    break;
                }
                left++;
            }
        }
        arr[index] = pivot;
        return index;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 4, 7, 6, 9, 0, 2, 8, 1, 5};
        quickSort(arr, 0, arr.length - 1);
        System.out.println("quickSort:" + Arrays.toString(arr));

        int[] arr2 = new int[]{2, 3, 8, 1, 9, 0, 5, 6, 4, 7};
        quickSort2(arr2, 0, arr.length - 1);
        System.out.println("quickSort2:" + Arrays.toString(arr2));

    }
}
