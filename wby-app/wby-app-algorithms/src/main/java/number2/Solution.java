package number2;

/**
 * @Description 数组中占比超过一半的元素称之为主要元素。给定一个整数数组，找到它的主要元素。若没有，返回-1。
 * 使用摩尔投票法
 * @Date 2020/11/19 15:11
 * @Author wuby31052
 */
public class Solution {

    public static int majorityElement(int[] A) {
        int length = A.length;
        int N = length / 2;
        int num = -1;
        int count = 0;
        for (int a : A) {
            if (count == 0) {
                num = a;
                count++;
            } else {
                if (a == num) {
                    count++;
                } else {
                    count--;
                }
            }
        }
        if (count < 0) {
            return -1;
        }
        int k = 0;
        for (int b : A) {
            if (b == num) {
                k++;
            }
        }
        if (k >= N) {
            return num;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = {2, 1, 2, 5, 3, 2};
        int i = majorityElement(a);
        System.out.println(i);
    }
}
