package number1;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 二分查找
 *
 * @author wubinyu
 * @date 2019/1/15 22:26.
 */
public class BinarySearch {

    public static int rank(int key, int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) {
                hi = mid - 1;
            } else if (key > a[mid]) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] whiteList = {1, 2, 3, 55, 44, 33, 25, 6, 4};
        Arrays.sort(whiteList);
        for (int i = 0; i < whiteList.length; i++) {
            System.out.println(whiteList[i] + "");
        }

        System.out.println("\n 请输入要查询的数");
        Scanner sc = new Scanner(System.in);
        int key = sc.nextInt();
        System.out.println("下标索引为" + BinarySearch.rank(key, whiteList));
    }
}
