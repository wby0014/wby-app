package number2;

/**
 * @Description 冒泡排序
 * @Date 2020/11/3 10:54
 * @Author wuby31052
 */
public class BubbleSort {

    /**
     * 冒泡排序
     *
     * @param arr
     */
    private static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {   // 这里说明为什么需要-1
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 5, 1, 3, 8, 5, 7, 4, 3};
        bubbleSort(arr);

        for (int a : arr) {
            System.out.println(a);
        }
    }
}
