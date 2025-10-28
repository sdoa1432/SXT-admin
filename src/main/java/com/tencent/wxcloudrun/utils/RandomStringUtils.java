package com.tencent.wxcloudrun.utils;

import java.security.SecureRandom;
import java.util.Random;

/**
 * 随机字符串生成工具类
 * 提供各种类型的随机字符串生成功能
 */
public final class RandomStringUtils {

    public static final String UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
    public static final String DIGITS = "0123456789";
    public static final String ALPHANUMERIC = UPPER_CASE + LOWER_CASE + DIGITS;

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();
    private static final Random RANDOM = new Random();

    private RandomStringUtils() {
        // 工具类，防止实例化
    }

    /**
     * 生成32位随机字符串（包含大小写字母和数字）
     * @return 32位随机字符串
     */
    public static String generate32() {
        return generate(32, ALPHANUMERIC, SECURE_RANDOM);
    }

    /**
     * 生成指定位数的随机字符串（包含大小写字母和数字）
     * @param length 字符串长度
     * @return 随机字符串
     */
    public static String generate(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("长度必须大于0");
        }
        return generate(length, ALPHANUMERIC, SECURE_RANDOM);
    }

    /**
     * 生成32位只包含数字的随机字符串
     * @return 32位数字随机字符串
     */
    public static String generateNumeric32() {
        return generate(32, DIGITS, SECURE_RANDOM);
    }

    /**
     * 生成32位只包含字母的随机字符串
     * @return 32位字母随机字符串
     */
    public static String generateAlpha32() {
        return generate(32, UPPER_CASE + LOWER_CASE, SECURE_RANDOM);
    }

    /**
     * 生成32位只包含大写字母的随机字符串
     * @return 32位大写字母随机字符串
     */
    public static String generateUpperCase32() {
        return generate(32, UPPER_CASE, SECURE_RANDOM);
    }

    /**
     * 生成32位只包含小写字母的随机字符串
     * @return 32位小写字母随机字符串
     */
    public static String generateLowerCase32() {
        return generate(32, LOWER_CASE, SECURE_RANDOM);
    }

    /**
     * 生成指定字符集的随机字符串
     * @param length 字符串长度
     * @param characters 字符集
     * @return 随机字符串
     */
    public static String generate(int length, String characters) {
        if (length <= 0) {
            throw new IllegalArgumentException("长度必须大于0");
        }
        if (characters == null || characters.isEmpty()) {
            throw new IllegalArgumentException("字符集不能为空");
        }
        return generate(length, characters, SECURE_RANDOM);
    }

    /**
     * 使用普通Random生成32位随机字符串（性能更好，安全性较低）
     * @return 32位随机字符串
     */
    public static String generateFast32() {
        return generate(32, ALPHANUMERIC, RANDOM);
    }

    /**
     * 核心生成方法
     * @param length 字符串长度
     * @param characters 字符集
     * @param random 随机数生成器
     * @return 随机字符串
     */
    private static String generate(int length, String characters, Random random) {
        StringBuilder sb = new StringBuilder(length);
        int charLength = characters.length();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(charLength);
            sb.append(characters.charAt(index));
        }

        return sb.toString();
    }

    /**
     * 生成UUID风格的32位随机字符串（8-4-4-4-12格式）
     * @return UUID风格的随机字符串
     */
    public static String generateUUIDStyle() {
        String raw = generate32();
        return raw.substring(0, 8) + "-" +
                raw.substring(8, 12) + "-" +
                raw.substring(12, 16) + "-" +
                raw.substring(16, 20) + "-" +
                raw.substring(20, 32);
    }

    /**
     * 批量生成随机字符串
     * @param count 生成数量
     * @param length 每个字符串长度
     * @return 随机字符串数组
     */
    public static String[] generateBatch(int count, int length) {
        if (count <= 0 || length <= 0) {
            throw new IllegalArgumentException("数量和长度必须大于0");
        }

        String[] results = new String[count];
        for (int i = 0; i < count; i++) {
            results[i] = generate(length);
        }
        return results;
    }

    /**
     * 验证字符串是否符合32位字母数字格式
     * @param str 待验证字符串
     * @return 是否符合格式
     */
    public static boolean isValid32Alphanumeric(String str) {
        if (str == null || str.length() != 32) {
            return false;
        }
        return str.matches("^[a-zA-Z0-9]{32}$");
    }

    /**
     * 获取字符统计信息
     * @param str 字符串
     * @return 统计信息字符串
     */
    public static String getStatistics(String str) {
        if (str == null) {
            return "字符串为null";
        }

        int upperCase = 0;
        int lowerCase = 0;
        int digits = 0;
        int other = 0;

        for (char c : str.toCharArray()) {
            if (Character.isUpperCase(c)) {
                upperCase++;
            } else if (Character.isLowerCase(c)) {
                lowerCase++;
            } else if (Character.isDigit(c)) {
                digits++;
            } else {
                other++;
            }
        }

        return String.format("长度: %d, 大写字母: %d, 小写字母: %d, 数字: %d, 其他: %d",
                str.length(), upperCase, lowerCase, digits, other);
    }
}
