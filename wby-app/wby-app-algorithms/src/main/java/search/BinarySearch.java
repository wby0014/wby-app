package search;

/**
 * @Description 二分查找
 * @Date 2020/11/3 9:42
 * @Author wuby31052
 */
public class BinarySearch {

    public static int search(int key, int a[]) {
        int lo = 0;
        int hi = a.length -1;
        int mid;
        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;
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
        int a[] = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int val = search(3, a);
        System.out.println(val);
    }
}
