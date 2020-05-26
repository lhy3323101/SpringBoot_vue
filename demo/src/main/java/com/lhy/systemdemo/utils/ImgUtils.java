package com.lhy.systemdemo.utils;

import java.util.Random;

/**
 * Description:图片处理工具类
 *
 * @author Angel
 * @since 2020/5/25 15:52
 */
public class ImgUtils {

    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
