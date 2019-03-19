package number1;

/**
 * 判断一个字符是否是回文 p50
 *
 * @author wubinyu
 * @date 2019/1/17 11:29.
 */
public class Plalindrome {

    public static boolean isPlalindrome(String string) {
        int len = string.length();
        boolean b = false;
        for (int i = 0; i < len / 2; i++) {
            b = string.charAt(i) != string.charAt(len - 1 - i) ? false : true;
            if (!b) {
                return b;
            }
        }
        return b;
    }

    public static void main(String[] args) {
        System.out.println(isPlalindrome("abcdcbaa"));
    }
}
