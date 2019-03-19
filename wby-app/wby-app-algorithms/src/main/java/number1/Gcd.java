package number1;

/**
 * p1
 * 求最大公约数
 * 用递归思想
 * 两个非负整数p,q的最大公约数：若q 是0，则最大公约数为p，否则p除以q得到的余数r,p和q的最大公约数即为q和r的最大公约数
 *
 * @author wubinyu
 * @date 2019/1/17 11:19.
 */
public class Gcd {

    public static int gcd(int p, int q) {
        if (q == 0) {
            return p;
        }
        int r = p % q;
        //递归
        return gcd(q, r);
    }

    public static void main(String[] args) {
        System.out.println(gcd(2, 8));
    }
}
