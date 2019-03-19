package number1;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/**
 * p27 利用algs.jar 画一个随机数组
 *
 * @author wubinyu
 * @date 2019/1/16 17:05.
 */
public class DrawArrays {

    public static void main(String[] args) {
        int N = 50;
        double[] a = new double[N];
        //产生0到1之间的随机数
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.random();
        }
        Arrays.sort(a);
        //画随机数组
        for (int i = 0; i < N; i++) {
            // 坐标
            double x = 1.0 * i / N;
            double y = a[i] / 2.0;
            // 宽高
            double rw = 0.5 / N;
            double rh = a[i] / 2.0;
            StdDraw.filledRectangle(x, y, rw, rh);
        }
    }

}
