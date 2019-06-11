package cn.fate.ssm.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author fate
 * @date 2019-06-11 10:27
 */
public class PhoneUtliTest {
    @Test
    public void phoneCode(){
        String s = PhoneUtli.phoneCode();
        System.out.println(s);

    }
}