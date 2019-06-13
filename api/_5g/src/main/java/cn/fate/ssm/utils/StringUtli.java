package cn.fate.ssm.utils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtli {
    /**
     * 大文本转义尖括号
     * @param string 转义前
     * @return 转义后
     */
    public static String chString(String string){
        string=string.replace("<","&lt;");
        string=string.replace(">","&gt;");
        return string;
    }

    /**
     * 检查是否有非法字符
     * @param string 被检查的字符串
     * @return 返回判断后的结果
     */
    public static boolean canshu(String string){
        String regEx = "[ _`~!@#$%^&*()+=|{}':;'\\[\\]<>/~@#￥%……&*（）——+|{}【】]|\n|\r|\t";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(string);
        return m.find();
    }

    /**
     * 判断字符串是否有值
     *
     * @param string 输入的字符串
     * @return 返回结果
     */
    public static boolean isNull(String string){
        if (string==null || "".equals(string)){
            return true;
        }
        return false;
    }


    //length用户要求产生字符串的长度

    /**
     *
     * @param length 随机生成字符串的长度
     * @return 生成的字符串
     */
    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer stringBuffer=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            stringBuffer.append(str.charAt(number));
        }
        return stringBuffer.toString();
    }
}
