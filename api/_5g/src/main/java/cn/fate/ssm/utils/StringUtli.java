package cn.fate.ssm.utils;

public class StringUtli {
    public static String chString(String string){
        string=string.replace("<","&lt;");
        string=string.replace(">","&gt;");
        return string;
    }

    public boolean isNull(String string){
        if (string==null || "".equals(string)){
            return true;
        }
        return false;
    }
}
