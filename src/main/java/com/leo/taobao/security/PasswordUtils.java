package com.leo.taobao.security;

import org.springframework.util.DigestUtils;
import sun.misc.BASE64Encoder;

import java.security.SecureRandom;
import java.util.Random;

/**
 * 密码工具类。
 *
 * @author Naiming
 */
public final class PasswordUtils {
    private static final String SALT64 = "q0kt8GbHeeSbr5H54Yfnk*ItgmZ*FrZ1sn!vo#CK6^sYhkPclq28m5U8XZTb$yKZ";
    private static final String SALT128 = "M!KPOHyZjOkO@QVJ6N#mIzdk7rKyD53F5EG$Tvr94lVh6pJH1Qlpj0zEHIK73V!ousNej4fa8IK70Mj#0JAWpO#UUAHSd$Zx7aMi8PzKjLjwwbsdlpm5a#BBf*FswaBv";

    /**
     * 加密。
     *
     * @param s 需要被加密的字符串。
     * @return 加密后的字符串。
     */
    public static String encrypt(String s) {
        String base = s + SALT64;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }

    public static String random() {
        Random RANDOM = new SecureRandom();
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return new BASE64Encoder().encode(salt);
    }
}
