package number2;

/**
 * @Description 在大小为 2N 的数组 A 中有 N+1 个不同的元素，其中有一个元素重复了 N 次。
 * 返回重复了 N 次的那个元素。
 * 说明其他元素都没重复，只有一个
 * @Date 2020/11/19 15:33
 * @Author wuby31052
 */
public class RepeatNtimes {

    public static int repeatNTimes(int[] A) {
        int[] B = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                if (A[j] == A[i]) {
                    ++B[i];
                }
            }
        }
        for (int j = 0; j < B.length; j++) {
            if (B[j] == 1) {
                return A[j];
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = {1,2,3,3};
        int i = repeatNTimes(a);
        System.out.println(i);
    }
}
