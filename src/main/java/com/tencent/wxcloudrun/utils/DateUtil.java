package com.tencent.wxcloudrun.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

/**
 * 日期操作工具类
 * 支持 Java 8 新的日期时间 API 和传统的 Date 类
 */
public class DateUtil {

    // 常用日期格式
    public static final String PATTERN_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_DATE_ONLY = "yyyy-MM-dd";
    public static final String PATTERN_TIME_ONLY = "HH:mm:ss";
    public static final String PATTERN_CHINESE = "yyyy年MM月dd日 HH时mm分ss秒";
    public static final String PATTERN_SLASH = "yyyy/MM/dd HH:mm:ss";
    public static final String PATTERN_COMPACT = "yyyyMMddHHmmss";

    private DateUtil() {
        // 工具类，防止实例化
    }

    // ==================== 日期创建相关 ====================

    /**
     * 获取当前日期时间
     */
    public static Date now() {
        return new Date();
    }

    /**
     * 获取当前日期时间字符串
     */
    public static String now(String pattern) {
        return format(new Date(), pattern);
    }

    /**
     * 根据年月日创建日期
     */
    public static Date of(int year, int month, int day) {
        LocalDate localDate = LocalDate.of(year, month, day);
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 根据年月日时分秒创建日期
     */
    public static Date of(int year, int month, int day, int hour, int minute, int second) {
        LocalDateTime localDateTime = LocalDateTime.of(year, month, day, hour, minute, second);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 从时间戳创建日期
     */
    public static Date of(long timestamp) {
        return new Date(timestamp);
    }

    // ==================== 日期格式化与解析 ====================

    /**
     * 日期转字符串（默认格式）
     */
    public static String format(Date date) {
        return format(date, PATTERN_DEFAULT);
    }

    /**
     * 日期转字符串（指定格式）
     */
    public static String format(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 字符串转日期（自动尝试多种格式）
     */
    public static Date parse(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return null;
        }

        // 常见日期格式列表
        String[] patterns = {
                PATTERN_DEFAULT,
                PATTERN_DATE_ONLY,
                PATTERN_CHINESE,
                PATTERN_SLASH,
                PATTERN_COMPACT,
                "yyyy-MM-dd HH:mm",
                "yyyy/MM/dd",
                "yyyyMMdd",
                "yyyy年MM月dd日"
        };

        for (String pattern : patterns) {
            try {
                return parse(dateStr, pattern);
            } catch (Exception e) {
                // 尝试下一种格式
            }
        }
        throw new IllegalArgumentException("无法解析日期字符串: " + dateStr);
    }

    /**
     * 字符串转日期（指定格式）
     */
    public static Date parse(String dateStr, String pattern) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            sdf.setLenient(false); // 严格模式
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            throw new IllegalArgumentException("日期解析失败: " + dateStr + ", 格式: " + pattern, e);
        }
    }

    // ==================== 日期计算相关 ====================

    /**
     * 日期加减（年）
     */
    public static Date addYears(Date date, int years) {
        LocalDateTime localDateTime = toLocalDateTime(date);
        localDateTime = localDateTime.plusYears(years);
        return toDate(localDateTime);
    }

    /**
     * 日期加减（月）
     */
    public static Date addMonths(Date date, int months) {
        LocalDateTime localDateTime = toLocalDateTime(date);
        localDateTime = localDateTime.plusMonths(months);
        return toDate(localDateTime);
    }

    /**
     * 日期加减（天）
     */
    public static Date addDays(Date date, int days) {
        LocalDateTime localDateTime = toLocalDateTime(date);
        localDateTime = localDateTime.plusDays(days);
        return toDate(localDateTime);
    }

    /**
     * 日期加减（小时）
     */
    public static Date addHours(Date date, int hours) {
        LocalDateTime localDateTime = toLocalDateTime(date);
        localDateTime = localDateTime.plusHours(hours);
        return toDate(localDateTime);
    }

    /**
     * 日期加减（分钟）
     */
    public static Date addMinutes(Date date, int minutes) {
        LocalDateTime localDateTime = toLocalDateTime(date);
        localDateTime = localDateTime.plusMinutes(minutes);
        return toDate(localDateTime);
    }

    /**
     * 日期加减（秒）
     */
    public static Date addSeconds(Date date, int seconds) {
        LocalDateTime localDateTime = toLocalDateTime(date);
        localDateTime = localDateTime.plusSeconds(seconds);
        return toDate(localDateTime);
    }

    // ==================== 日期比较相关 ====================

    /**
     * 判断两个日期是否相等（忽略时间）
     */
    public static boolean isSameDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        LocalDate localDate1 = toLocalDate(date1);
        LocalDate localDate2 = toLocalDate(date2);
        return localDate1.isEqual(localDate2);
    }

    /**
     * 判断日期是否在指定范围内
     */
    public static boolean isBetween(Date date, Date start, Date end) {
        if (date == null || start == null || end == null) {
            return false;
        }
        return !date.before(start) && !date.after(end);
    }

    /**
     * 判断日期是否在今天之前
     */
    public static boolean isBeforeToday(Date date) {
        return isBefore(date, now());
    }

    /**
     * 判断日期是否在今天之后
     */
    public static boolean isAfterToday(Date date) {
        return isAfter(date, now());
    }

    /**
     * 判断 date1 是否在 date2 之前
     */
    public static boolean isBefore(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        return date1.before(date2);
    }

    /**
     * 判断 date1 是否在 date2 之后
     */
    public static boolean isAfter(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        return date1.after(date2);
    }

    // ==================== 日期差计算 ====================

    /**
     * 计算两个日期相差的天数
     */
    public static long daysBetween(Date start, Date end) {
        LocalDate startDate = toLocalDate(start);
        LocalDate endDate = toLocalDate(end);
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    /**
     * 计算两个日期相差的小时数
     */
    public static long hoursBetween(Date start, Date end) {
        LocalDateTime startDateTime = toLocalDateTime(start);
        LocalDateTime endDateTime = toLocalDateTime(end);
        return ChronoUnit.HOURS.between(startDateTime, endDateTime);
    }

    /**
     * 计算两个日期相差的分钟数
     */
    public static long minutesBetween(Date start, Date end) {
        LocalDateTime startDateTime = toLocalDateTime(start);
        LocalDateTime endDateTime = toLocalDateTime(end);
        return ChronoUnit.MINUTES.between(startDateTime, endDateTime);
    }

    /**
     * 计算两个日期相差的秒数
     */
    public static long secondsBetween(Date start, Date end) {
        LocalDateTime startDateTime = toLocalDateTime(start);
        LocalDateTime endDateTime = toLocalDateTime(end);
        return ChronoUnit.SECONDS.between(startDateTime, endDateTime);
    }

    // ==================== 特殊日期获取 ====================

    /**
     * 获取当天的开始时间（00:00:00）
     */
    public static Date getStartOfDay(Date date) {
        LocalDateTime localDateTime = toLocalDateTime(date);
        localDateTime = localDateTime.toLocalDate().atStartOfDay();
        return toDate(localDateTime);
    }

    /**
     * 获取当天的结束时间（23:59:59）
     */
    public static Date getEndOfDay(Date date) {
        LocalDateTime localDateTime = toLocalDateTime(date);
        localDateTime = localDateTime.toLocalDate().atTime(23, 59, 59);
        return toDate(localDateTime);
    }

    /**
     * 获取本周的开始日期（周一）
     */
    public static Date getStartOfWeek(Date date) {
        LocalDate localDate = toLocalDate(date);
        LocalDate startOfWeek = localDate.with(DayOfWeek.MONDAY);
        return toDate(startOfWeek.atStartOfDay());
    }

    /**
     * 获取本周的结束日期（周日）
     */
    public static Date getEndOfWeek(Date date) {
        LocalDate localDate = toLocalDate(date);
        LocalDate endOfWeek = localDate.with(DayOfWeek.SUNDAY);
        return toDate(endOfWeek.atTime(23, 59, 59));
    }

    /**
     * 获取本月的开始日期
     */
    public static Date getStartOfMonth(Date date) {
        LocalDate localDate = toLocalDate(date);
        LocalDate startOfMonth = localDate.with(TemporalAdjusters.firstDayOfMonth());
        return toDate(startOfMonth.atStartOfDay());
    }

    /**
     * 获取本月的结束日期
     */
    public static Date getEndOfMonth(Date date) {
        LocalDate localDate = toLocalDate(date);
        LocalDate endOfMonth = localDate.with(TemporalAdjusters.lastDayOfMonth());
        return toDate(endOfMonth.atTime(23, 59, 59));
    }

    /**
     * 获取本年的开始日期
     */
    public static Date getStartOfYear(Date date) {
        LocalDate localDate = toLocalDate(date);
        LocalDate startOfYear = localDate.with(TemporalAdjusters.firstDayOfYear());
        return toDate(startOfYear.atStartOfDay());
    }

    /**
     * 获取本年的结束日期
     */
    public static Date getEndOfYear(Date date) {
        LocalDate localDate = toLocalDate(date);
        LocalDate endOfYear = localDate.with(TemporalAdjusters.lastDayOfYear());
        return toDate(endOfYear.atTime(23, 59, 59));
    }

    // ==================== 日期信息获取 ====================

    /**
     * 获取日期的年份
     */
    public static int getYear(Date date) {
        return toLocalDate(date).getYear();
    }

    /**
     * 获取日期的月份（1-12）
     */
    public static int getMonth(Date date) {
        return toLocalDate(date).getMonthValue();
    }

    /**
     * 获取日期的天数（1-31）
     */
    public static int getDay(Date date) {
        return toLocalDate(date).getDayOfMonth();
    }

    /**
     * 获取日期是星期几（1-7，1代表周一）
     */
    public static int getDayOfWeek(Date date) {
        DayOfWeek dayOfWeek = toLocalDate(date).getDayOfWeek();
        return dayOfWeek.getValue();
    }

    /**
     * 获取日期是当年的第几天
     */
    public static int getDayOfYear(Date date) {
        return toLocalDate(date).getDayOfYear();
    }

    /**
     * 获取日期的小时（0-23）
     */
    public static int getHour(Date date) {
        return toLocalDateTime(date).getHour();
    }

    /**
     * 获取日期的分钟（0-59）
     */
    public static int getMinute(Date date) {
        return toLocalDateTime(date).getMinute();
    }

    /**
     * 获取日期的秒（0-59）
     */
    public static int getSecond(Date date) {
        return toLocalDateTime(date).getSecond();
    }

    // ==================== 日期转换 ====================

    /**
     * Date 转 LocalDate
     */
    public static LocalDate toLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Date 转 LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * LocalDate 转 Date
     */
    public static Date toDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * LocalDateTime 转 Date
     */
    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取时间戳（毫秒）
     */
    public static long getTimestamp(Date date) {
        return date.getTime();
    }

    // ==================== 其他实用方法 ====================

    /**
     * 判断是否为闰年
     */
    public static boolean isLeapYear(Date date) {
        return toLocalDate(date).isLeapYear();
    }

    /**
     * 获取两个日期之间的所有日期
     */
    public static List<Date> getDatesBetween(Date start, Date end) {
        List<Date> dates = new ArrayList<>();
        LocalDate startDate = toLocalDate(start);
        LocalDate endDate = toLocalDate(end);

        while (!startDate.isAfter(endDate)) {
            dates.add(toDate(startDate));
            startDate = startDate.plusDays(1);
        }
        return dates;
    }

    /**
     * 获取年龄（根据生日计算）
     */
    public static int getAge(Date birthday) {
        LocalDate birthDate = toLocalDate(birthday);
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }

    /**
     * 计算工作日（排除周末）
     */
    public static long getWorkDaysBetween(Date start, Date end) {
        LocalDate startDate = toLocalDate(start);
        LocalDate endDate = toLocalDate(end);

        long workDays = 0;
        LocalDate date = startDate;
        while (!date.isAfter(endDate)) {
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            if (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY) {
                workDays++;
            }
            date = date.plusDays(1);
        }
        return workDays;
    }
}
