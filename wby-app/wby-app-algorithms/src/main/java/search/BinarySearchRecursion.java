package search;

/**
 * @Description 二分查找-递归
 * @Date 2020/11/3 10:08
 * @Author wuby31052
 */
public class BinarySearchRecursion {

    public static int search(int key, int a[], int start, int end) {
        if (end < start) {
            return -1 ;
        }
        int mid = start + (end - start) / 2;
        if (key < a[mid]) {
            return search(key, a, start, mid - 1);
        } else if (key > a[mid]) {
            return search(key, a, mid + 1, end);
        } else {
            return mid;
        }
    }

    public static int search(int key, int a[]) {
        return search(key, a, 0, a.length - 1);
    }

    public static void main(String[] args) {
        int a[] = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int val = search(7, a);
        System.out.println(val);
    }
}
