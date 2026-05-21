package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static boolean isBetweenHalfOpen(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        boolean afterStart = startTime == null || !lt.isBefore(startTime);
        boolean beforeEnd = endTime == null || !lt.isAfter(endTime);
        return afterStart && beforeEnd;
    }

    public static boolean isBetweenClosed(LocalDate ld, LocalDate startDate, LocalDate endDate) {
        boolean afterStart = startDate == null || !ld.isBefore(startDate);
        boolean beforeEnd = endDate == null || !ld.isAfter(endDate);
        return afterStart && beforeEnd;
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }
}
