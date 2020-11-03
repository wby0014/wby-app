package number1;

/**
 * 二维数组
 *
 * @author wubinyu
 * @date 2019/1/16 17:05.
 */
public class TwoArrays {

    public static void main(String[] args) {
        // 二维数组反转打印
        int[][] arr = new int[][]{{4, 5, 6}, {2, 3, 9}};
        int[][] arrtrans = new int[arr[0].length][arr.length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arrtrans[j][i] = arr[i][j];
            }
        }
        for (int i = 0; i < arrtrans.length; i++) {
            for (int j = 0; j < arrtrans[0].length; j++) {
                System.out.print(arrtrans[i][j]);
            }
            System.out.println();
        }
    }

}
