package org.chihx.seckill.util;


import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {

    private static final String salt = "1a2b3c4d";

    public static String MD5(String src) {
        return DigestUtils.md5Hex(src);
    }

    public static String inputPassToFormPass(String inputPass) {
        /**
         * 两个char变量ascii值相加结果
         */
        String str = "" + salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(5) + salt.charAt(4);
        return MD5(str);
    }

    public static String formPassToDBPass(String inputPass, String salt) {
        /**
         * 两个char变量ascii值相加结果
         */
        String str = "" + salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(5) + salt.charAt(4);
        return MD5(str);
    }

    public static String inputPassToDBPass(String input, String saltDB) {
        String formPass = inputPassToFormPass(input);
        String dbPass = formPassToDBPass(formPass, saltDB);
        return dbPass;
    }

    public static void main(String[] args) {
        System.out.println(inputPassToFormPass("123456"));// d3b1294a61a07da9b49b6e22b2cbd7f9
        System.out.println(formPassToDBPass(inputPassToFormPass("123456"), "1a2b3c4d"));
        System.out.println(inputPassToDBPass("123456", "1a2b3c4d"));// b7797cce01b4b131b433b6acf4add449
    }
}
