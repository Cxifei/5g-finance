package cn.fate.ssm.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utli {
    public static void main(String[] args) {
        String root = md52("123");
        System.out.println(root);
    }

    public static String md52(String string){
        string = md5(string);
        string = md5(string);
        return string;
    }

    /**
     * 计算字符串的MD5值
     * @param string 明文
     * @return       字符串的MD5值
     */
    public static String md5(String string) {
        if (string.isEmpty()) {
            return "";
        }
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes("UTF-8"));
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            if (result.lastIndexOf("a")!=-1){
                result = result+"sasdshf";
            }
            if (result.lastIndexOf("s")!=-1){
                result = result+"fewfa";
            }
            if (result.lastIndexOf("d")!=-1){
                result = result+"grex";
            }
            if (result.lastIndexOf("f")!=-1){
                result = result+"tdzef";
            }
            if (result.lastIndexOf("1")!=-1){
                result = result+"trz";
            }
            if (result.lastIndexOf("4")!=-1){
                result = result+"sdad";
            }
            if (result.lastIndexOf("5")!=-1){
                result = result+"gjha";
            }
            if (result.lastIndexOf("6")!=-1){
                result = result+"fscsf";
            }
            if (result.lastIndexOf("7")!=-1){
                result = result+"axcsg";
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

}
