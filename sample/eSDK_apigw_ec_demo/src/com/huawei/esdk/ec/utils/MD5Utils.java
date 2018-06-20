package com.huawei.esdk.ec.utils;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

    public static String do32BitMD5(String plainStr) {
        if (null == plainStr) {
            return plainStr;
        }
        String result;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainStr.getBytes(Charset.forName("UTF-8")));
            byte[] b = md.digest();

            StringBuilder sb = new StringBuilder("");
            for (int index = 0; index < b.length; index++) {
                int i = b[index];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(i));
            }
            result = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            result = plainStr;
        }

        return result;
    }
}
