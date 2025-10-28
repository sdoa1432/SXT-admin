package com.tencent.wxcloudrun.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class ChineseFirstLetter {

    public static String getFirstLetters(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }

        // 配置拼音格式（大写、无音调）
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        StringBuilder result = new StringBuilder();
        for (char c : str.toCharArray()) {
            // 如果是中文，提取首字母
            if (Character.toString(c).matches("[\\u4E00-\\u9FA5]+")) {
                try {
                    // 多音字可能返回多个拼音，取第一个
                    String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c, format);
                    if (pinyinArray != null && pinyinArray.length > 0) {
                        result.append(pinyinArray[0].charAt(0));
                    }
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                // 非中文直接拼接（如字母、数字、符号）
                result.append(c);
            }
        }
        return result.toString();
    }
}
