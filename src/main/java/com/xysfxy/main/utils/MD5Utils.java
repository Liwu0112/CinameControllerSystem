package com.xysfxy.main.utils;

import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * @author 余胜军
 * @ClassName MD5Utils
 * @qq 644064779
 * @addres www.mayikt.com
 * 微信:yushengjun644
 */
@Slf4j
public class MD5Utils {
    public static String md5(String source) {

        // 1.判断 source 是否有效
        if (source == null || source.length() == 0) {
            // 2.如果不是有效的字符串抛出异常
            throw new RuntimeException("字符串不可为空");
        }
        try {
            // 3.获取 MessageDigest 对象
            String algorithm = "md5";
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            // 4.获取明文字符串对应的字节数组
            byte[] input = source.getBytes();
            //执行加密
            byte[] output = messageDigest.digest(input);
            // 6.创建 BigInteger 对象
            int signum = 1;
            BigInteger bigInteger = new BigInteger(signum, output);
            // 7.按照 16 进制将 bigInteger 的值转换为大写字符串
            int radix = 16;
            String encoded = bigInteger.toString(radix).toUpperCase();
            return encoded;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            log.error("e:{}", e);
            return null;
        }
    }

    public static void main(String[] args) {
        String pwd = MD5Utils.md5("123456"+ UUID.randomUUID().toString().replace("-",""));
        System.out.println(pwd);
        // 在线MD5 破解工具 https://www.cmd5.com/
        /**
         * 加密方案
         * 对称加密 非对称加密 单向加密
         * 单向加密----不能够解密  只能够通过暴力破解的形式猜密码
         * gitee 上 权限系统 密码 取出手机号码最后四位 加盐、生成随机数6位
         * MD5 单向加密应用场景  例如 分布式配置中心 判断文件内容是否发生变化
         * 单向加密  相同的内容加密  后都是一样的内容
         * 盐值
         */
    }
}