package com.example.seckill.utils;

import org.springframework.stereotype.Component;
import org.apache.commons.codec.digest.DigestUtils;
@Component
public class MD5 {
    public static String md5(String src){
        return DigestUtils.md5Hex(src);
    }
    private static final String salt= "1a2b3c4d";
    public static String inputPassToFromPass(String inputPass){
        String str =salt.charAt(0)+salt.charAt(2)+inputPass+salt.charAt(5)+salt.charAt(4);
        return md5(str);
    }
    public static String fromPassToDBPass(String fromPass,String salt){
        String str = salt.charAt(0)+salt.charAt(2)+fromPass +salt.charAt(5)+salt.charAt(4);
        return md5(str);
    }
    public static String inputPassToDBPass(String inputPass, String salt){
        String fromPass = inputPassToFromPass(inputPass);
        String dbPass = fromPassToDBPass(fromPass,salt);
        return dbPass;
    }
//    Test
    public static void main(String[] args){
        String rawPassWord = "123456";
        String salt = "1a2b3c4d";
        String out1 = inputPassToFromPass(rawPassWord);
        System.out.println(out1);
        String out2 =fromPassToDBPass(out1,salt);
        System.out.println(out2);
        String out3 =inputPassToDBPass(rawPassWord,salt);
        System.out.println(out3);
        if(out3.equals(out2)){
            System.out.println("Implemented correctly!");
        }
        else{
            System.out.println("Wrongly Implemented!");
        }
    }
}
