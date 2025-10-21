package com.tencent.wxcloudrun.utils;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 字符串工具类
 * 提供常用的字符串处理方法
 */
public final class StringUtils {

    private StringUtils() {
        // 工具类，防止实例化
    }

    /**
     * 判断字符串是否为空
     * @param str 待判断字符串
     * @return 为空返回true，否则返回false
     */
    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

    /**
     * 判断字符串是否不为空
     * @param str 待判断字符串
     * @return 不为空返回true，否则返回false
     */
    public static boolean isNotEmpty(CharSequence str) {
        return !isEmpty(str);
    }

    /**
     * 判断字符串是否为空或空白
     * @param str 待判断字符串
     * @return 为空或空白返回true，否则返回false
     */
    public static boolean isBlank(CharSequence str) {
        if (isEmpty(str)) {
            return true;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串是否不为空且非空白
     * @param str 待判断字符串
     * @return 不为空且非空白返回true，否则返回false
     */
    public static boolean isNotBlank(CharSequence str) {
        return !isBlank(str);
    }

    /**
     * 去除字符串两端空白，如果为空则返回空字符串
     * @param str 待处理字符串
     * @return 处理后的字符串
     */
    public static String trim(String str) {
        return str == null ? "" : str.trim();
    }

    /**
     * 去除字符串所有空白（包括中间的空白）
     * @param str 待处理字符串
     * @return 处理后的字符串
     */
    public static String removeAllWhitespace(String str) {
        if (isEmpty(str)) {
            return str;
        }
        return str.replaceAll("\\s+", "");
    }

    /**
     * 字符串反转
     * @param str 待反转字符串
     * @return 反转后的字符串
     */
    public static String reverse(String str) {
        if (str == null) {
            return null;
        }
        return new StringBuilder(str).reverse().toString();
    }

    /**
     * 字符串首字母大写
     * @param str 待处理字符串
     * @return 首字母大写的字符串
     */
    public static String capitalize(String str) {
        if (isEmpty(str)) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 字符串首字母小写
     * @param str 待处理字符串
     * @return 首字母小写的字符串
     */
    public static String uncapitalize(String str) {
        if (isEmpty(str)) {
            return str;
        }
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    /**
     * 驼峰命名转下划线命名
     * @param camelCase 驼峰命名字符串
     * @return 下划线命名字符串
     */
    public static String camelToSnakeCase(String camelCase) {
        if (isEmpty(camelCase)) {
            return camelCase;
        }
        return camelCase.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();
    }

    /**
     * 下划线命名转驼峰命名
     * @param snakeCase 下划线命名字符串
     * @return 驼峰命名字符串
     */
    public static String snakeToCamelCase(String snakeCase) {
        if (isEmpty(snakeCase)) {
            return snakeCase;
        }
        String[] parts = snakeCase.split("_");
        StringBuilder result = new StringBuilder(parts[0]);
        for (int i = 1; i < parts.length; i++) {
            result.append(capitalize(parts[i]));
        }
        return result.toString();
    }

    /**
     * 检查字符串是否以指定前缀开头（忽略大小写）
     * @param str 待检查字符串
     * @param prefix 前缀
     * @return 是否以指定前缀开头
     */
    public static boolean startsWithIgnoreCase(String str, String prefix) {
        if (str == null || prefix == null) {
            return false;
        }
        return str.regionMatches(true, 0, prefix, 0, prefix.length());
    }

    /**
     * 检查字符串是否以指定后缀结尾（忽略大小写）
     * @param str 待检查字符串
     * @param suffix 后缀
     * @return 是否以指定后缀结尾
     */
    public static boolean endsWithIgnoreCase(String str, String suffix) {
        if (str == null || suffix == null) {
            return false;
        }
        int suffixLength = suffix.length();
        return str.regionMatches(true, str.length() - suffixLength, suffix, 0, suffixLength);
    }

    /**
     * 统计子字符串出现的次数
     * @param str 原字符串
     * @param sub 子字符串
     * @return 出现次数
     */
    public static int countOccurrences(String str, String sub) {
        if (isEmpty(str) || isEmpty(sub)) {
            return 0;
        }
        int count = 0;
        int idx = 0;
        while ((idx = str.indexOf(sub, idx)) != -1) {
            count++;
            idx += sub.length();
        }
        return count;
    }

    /**
     * 生成指定长度的随机字符串
     * @param length 字符串长度
     * @return 随机字符串
     */
    public static String generateRandomString(int length) {
        if (length <= 0) {
            return "";
        }
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    /**
     * 判断字符串是否为数字
     * @param str 待判断字符串
     * @return 是否为数字
     */
    public static boolean isNumeric(String str) {
        if (isEmpty(str)) {
            return false;
        }
        return str.chars().allMatch(Character::isDigit);
    }

    /**
     * 判断字符串是否为字母
     * @param str 待判断字符串
     * @return 是否为字母
     */
    public static boolean isAlpha(String str) {
        if (isEmpty(str)) {
            return false;
        }
        return str.chars().allMatch(Character::isLetter);
    }

    /**
     * 判断字符串是否为字母或数字
     * @param str 待判断字符串
     * @return 是否为字母或数字
     */
    public static boolean isAlphanumeric(String str) {
        if (isEmpty(str)) {
            return false;
        }
        return str.chars().allMatch(Character::isLetterOrDigit);
    }

    /**
     * 判断字符串是否为有效的邮箱格式
     * @param email 邮箱字符串
     * @return 是否为有效邮箱
     */
    public static boolean isValidEmail(String email) {
        if (isEmpty(email)) {
            return false;
        }
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }

    /**
     * 字符串脱敏处理
     * @param str 原字符串
     * @param start 开始位置
     * @param end 结束位置
     * @param maskChar 脱敏字符
     * @return 脱敏后的字符串
     */
    public static String mask(String str, int start, int end, char maskChar) {
        if (isEmpty(str)) {
            return str;
        }
        if (start < 0) start = 0;
        if (end > str.length()) end = str.length();
        if (start >= end) {
            return str;
        }

        char[] chars = str.toCharArray();
        for (int i = start; i < end; i++) {
            chars[i] = maskChar;
        }
        return new String(chars);
    }

    /**
     * 手机号脱敏
     * @param phone 手机号
     * @return 脱敏后的手机号
     */
    public static String maskPhone(String phone) {
        if (isEmpty(phone) || phone.length() < 7) {
            return phone;
        }
        return mask(phone, 3, 7, '*');
    }

    /**
     * 邮箱脱敏
     * @param email 邮箱
     * @return 脱敏后的邮箱
     */
    public static String maskEmail(String email) {
        if (isEmpty(email)) {
            return email;
        }
        int atIndex = email.indexOf('@');
        if (atIndex <= 1) {
            return email;
        }
        return mask(email, 1, atIndex, '*');
    }

    /**
     * 连接字符串数组
     * @param delimiter 分隔符
     * @param elements 字符串元素
     * @return 连接后的字符串
     */
    public static String join(String delimiter, String... elements) {
        if (elements == null || elements.length == 0) {
            return "";
        }
        return String.join(delimiter, elements);
    }

    /**
     * 连接集合中的字符串
     * @param delimiter 分隔符
     * @param collection 字符串集合
     * @return 连接后的字符串
     */
    public static String join(String delimiter, Collection<String> collection) {
        if (collection == null || collection.isEmpty()) {
            return "";
        }
        return collection.stream().collect(Collectors.joining(delimiter));
    }

    /**
     * 截取字符串，如果超过最大长度则添加省略号
     * @param str 原字符串
     * @param maxLength 最大长度
     * @return 截取后的字符串
     */
    public static String abbreviate(String str, int maxLength) {
        if (isEmpty(str) || str.length() <= maxLength) {
            return str;
        }
        if (maxLength <= 3) {
            return str.substring(0, maxLength);
        }
        return str.substring(0, maxLength - 3) + "...";
    }

    /**
     * 移除字符串中的HTML标签
     * @param html 包含HTML的字符串
     * @return 纯文本字符串
     */
    public static String removeHtmlTags(String html) {
        if (isEmpty(html)) {
            return html;
        }
        return html.replaceAll("<[^>]*>", "");
    }

    /**
     * 比较两个字符串（null安全）
     * @param str1 字符串1
     * @param str2 字符串2
     * @return 是否相等
     */
    public static boolean equals(String str1, String str2) {
        if (str1 == null) {
            return str2 == null;
        }
        return str1.equals(str2);
    }

    /**
     * 比较两个字符串（忽略大小写，null安全）
     * @param str1 字符串1
     * @param str2 字符串2
     * @return 是否相等
     */
    public static boolean equalsIgnoreCase(String str1, String str2) {
        if (str1 == null) {
            return str2 == null;
        }
        return str1.equalsIgnoreCase(str2);
    }

    /**
     * 获取字符串的字节长度（考虑中文）
     * @param str 字符串
     * @return 字节长度
     */
    public static int getByteLength(String str) {
        if (isEmpty(str)) {
            return 0;
        }
        try {
            return str.getBytes("UTF-8").length;
        } catch (Exception e) {
            return str.getBytes().length;
        }
    }
}
