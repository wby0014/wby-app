package number2;

import java.math.BigInteger;

/**
 * @Description
 * @Date 2020/11/27 15:43
 * @Author wuby31052
 */
public class TestDemo {

    final static char[] digits = {
            '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
            't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F',
            'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
            'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '+', '/'};

    public static String spliceCdCard(String cdCard, String partner) {
        if (cdCard != null && cdCard.length() != 36 && partner != null) {
            if (cdCard.startsWith("@")) {
                cdCard = cdCard.substring(1);
                cdCard = stringToNumeric(cdCard, new BigInteger("64")).toString();
            }
            if (cdCard.length() > 8) {
                if (cdCard.length() == 30) {
                    //新协议号去除机构号后是30位。修改后的格式为：前22位日期与其它信息 + 6位机构号(机构号不到6位前补0，超过6位去除后面的) + 8位序列号
                    String partnerSub = "";
                    if (partner.length() < 6) {
                        partnerSub = fillRight(partner, 6, '0');
                    } else {
                        partnerSub = partner.substring(0, 6);
                    }
                    cdCard = cdCard.substring(0, 22) + partnerSub.toLowerCase() + cdCard.substring(22);
                } else {
                    cdCard = cdCard.substring(0, 8) + (partner.toLowerCase()) + cdCard.substring(8, cdCard.length());
                }
            }
        }
        return cdCard;
    }

    public static BigInteger stringToNumeric(String s, BigInteger system) {
        char[] buf = new char[s.length()];
        s.getChars(0, s.length(), buf, 0);
        BigInteger num = new BigInteger("0");
        for (int i = 0; i < buf.length; i++) {
            for (int j = 0; j < digits.length; j++) {
                if (digits[j] == buf[i]) {
                    BigInteger bj = new BigInteger(j + "");
                    num = num.add(bj.multiply(system.pow(buf.length - i - 1)));
                    break;
                }
            }
        }
        return num;
    }

    public static String fillRight(String sOld, int iLength, char c) {
        StringBuffer sb = new StringBuffer(sOld);
        int length = iLength;
        //modified by cheng,@2007-3-27,对于中文字符，需要进行解码后，再获取字节长度；
        try {
            byte[] tmp = sOld.getBytes("GBK");
            length = tmp.length;
        } catch (Exception e) {
            length = iLength;
        }
        for (int i = length; i < iLength; i++) {
            sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String cdCard = spliceCdCard("@123456789012345678901234567890", "6667776667777666777");
        System.out.println("处理后长度:" + cdCard.length() + " 处理后协议号:" + cdCard);
    }

}
