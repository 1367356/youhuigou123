package com.egzosn.pay.common.util.sign.encrypt;


import com.egzosn.pay.common.util.str.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;


import static com.egzosn.pay.common.util.str.StringUtils.getContentBytes;

/**
 * MD5签名工具
 */
public class MD5 {

    /**
     * 签名字符串
     *
     * @param text          需要签名的字符串
     * @param key           密钥
     * @param input_charset 编码格式
     * @return 签名结果
     */
    public static String sign(String text, String key, String input_charset) {
        //拼接key
        text = text + key;
        return DigestUtils.md5Hex(getContentBytes(text, input_charset));
    }

    /**
     * 签名字符串
     *
     * @param text          需要签名的字符串
     * @param sign          签名结果
     * @param key           密钥
     * @param input_charset 编码格式
     * @return 签名结果
     */
    public static boolean verify(String text, String sign, String key, String input_charset) {
        //判断是否一样
        return StringUtils.equals(sign(text, key, input_charset).toUpperCase(), sign.toUpperCase());
    }




}