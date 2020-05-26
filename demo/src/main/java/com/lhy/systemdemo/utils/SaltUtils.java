package com.lhy.systemdemo.utils;

import com.lhy.systemdemo.base.exception.BusinessRuntimeException;
import com.lhy.systemdemo.base.exception.EncryException;
import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.util.UUID;

/**
 * Description:盐加密工具类
 *
 * @author Angel
 * @since 2020/5/9 13:42
 */
public class SaltUtils {

    /**
     * sha-256加密
     */
    public static final String SHA_256 = "sha-256";

    private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
            "6", "!", "#", "@", "a", "b", "c", "d", "*", "f", "g", "F" };

    /**
     * 使用盐加密
     * @param password 需要加密原始报文
     * @param salt 盐
     * @param encodeType
     * @return
     */
    public static String encode(String password, String salt, String encodeType){
        String encodeStr = null;
        try {
            MessageDigest md = MessageDigest.getInstance(encodeType);
            // 1.组合加密字符串 password + {salt}
            if (StringUtils.isBlank(password)){
                throw new EncryException("加密字段不能为空");
            }
            String originalStr = password;
            if (StringUtils.isNotBlank(salt)){
                originalStr += "{" + salt +"}";
            }
            // 2.sha加密比并转换成16进制字串
            encodeStr = byteArrayToHexString(md.digest(originalStr.getBytes("utf-8")));
        } catch (EncryException e){
            throw e;
        } catch (Exception e){
            throw new BusinessRuntimeException("盐加密操作异常",e);
        }
        return encodeStr;
    }

    /**
     * 简易生成盐
     * @return 盐
     */
    public static String createSalt(){
        return UUID.randomUUID().toString();
    }

    /**
     * 转换字节数组为16进制字串
     *
     * @param b
     *            字节数组
     * @return 16进制字串
     */
    private static String byteArrayToHexString(byte[] b){
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0){
            n = 256 + n;
        }
        int d1 = n / hexDigits.length;
        int d2 = n % hexDigits.length;
        return hexDigits[d1] + hexDigits[d2];
    }
    public static void main(String[] args) {
        /*String uuid = createSalt();
        String str = encode("123456",uuid,SHA_256);
        System.out.println("uuid:" + uuid);
        System.out.println("encodeStr:" + str);*/
        String url = "/test/login/index";
        System.out.println(StringUtils.remove(url,"" + "/"));
    }
}
