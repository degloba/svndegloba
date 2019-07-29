package com.degloba.utils;

import java.security.InvalidParameterException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    /**
     * The minimum date is set to January 1, 1000
     */
    public static final Date MIN_DATE = date(1000, 1, 1);
    /**
     * The maximum date, set on January 1 8888
     */
    public static final Date MAX_DATE = date(8888, 1, 1);
    private static final long MILLIS_IN_A_SECOND = 1000;
    private static final long SECONDS_IN_A_MINUTE = 60;
    private static final long MINUTES_IN_AN_HOUR = 60;
    private static final long HOURS_IN_A_DAY = 24;

	//private static final int[] daysInMonth = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    private static final int DAYS_IN_A_WEEK = 7;
    private static final int MONTHS_IN_A_YEAR = 12;

    private DateUtils() {
        super();
    }

    /**
     * According to date build date objects. Note that month is counted from the beginning, that month is 1 for January.
     *
     * @param year Year
     * @param month Months. Note 1 for January, and so on.
     * @param day Japan
     * @return A date
     */
    public static Date date(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * According to date build date objects. Note that month is counted from the beginning, that month is 1 for January.
     *
     * @param year Year
     * @param month Months. Note 1 for January, and so on.
     * @param day Japan
     * @param hour Time
     * @param minute Minute
     * @param second Second
     * @return A date
     */
    public static Date date(int year, int month, int day,
            int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day, hour, minute, second);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * The difference between the number of years two dates (excluding time)
     *
     * @param date1 First date
     * @param date2 The second date
     * @return Number of years between the two dates separated
     */
    public static int getYearDiff(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new InvalidParameterException(
                    "date1 and date2 cannot be null!");
        }
        if (date1.after(date2)) {
            throw new InvalidParameterException("date1 cannot be after date2!");
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        int year1 = calendar.get(Calendar.YEAR);
        int month1 = calendar.get(Calendar.MONTH);
        int day1 = calendar.get(Calendar.DATE);

        calendar.setTime(date2);
        int year2 = calendar.get(Calendar.YEAR);
        int month2 = calendar.get(Calendar.MONTH);
        int day2 = calendar.get(Calendar.DATE);

        int result = year2 - year1;
        if (month2 < month1) {
            result--;
        } else if (month2 == month1 && day2 < day1) {
            result--;
        }
        return result;
    }

    /**
     * The difference between the number of whole months between two dates (excluding time)
     *
     * @param date1 First date
     * @param date2 The second date
     * @return Number of whole months between the two dates separated
     */
    public static int getMonthDiff(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new InvalidParameterException(
                    "date1 and date2 cannot be null!");
        }
        if (date1.after(date2)) {
            throw new InvalidParameterException("date1 cannot be after date2!");
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        int year1 = calendar.get(Calendar.YEAR);
        int month1 = calendar.get(Calendar.MONTH);
        int day1 = calendar.get(Calendar.DATE);

        calendar.setTime(date2);
        int year2 = calendar.get(Calendar.YEAR);
        int month2 = calendar.get(Calendar.MONTH);
        int day2 = calendar.get(Calendar.DATE);

        int months = 0;
        if (day2 >= day1) {
            months = month2 - month1;
        } else {
            months = month2 - month1 - 1;
        }
        return (year2 - year1) * MONTHS_IN_A_YEAR + months;
    }

    /**
     * The number of days between the statistics contain two dates. It contains date1, but does not include date2
     *
     * @param date1 First date
     * @param date2 The second date
     * @return Several day interval between the two dates
     */
    public static int getDayDiff(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new InvalidParameterException(
                    "date1 and date2 cannot be null!");
        }
        Date startDate = org.apache.commons.lang3.time.DateUtils.truncate(
                date1, Calendar.DATE);
        Date endDate = org.apache.commons.lang3.time.DateUtils.truncate(date2,
                Calendar.DATE);
        if (startDate.after(endDate)) {
            throw new InvalidParameterException("date1 cannot be after date2!");
        }
        long millSecondsInOneDay = HOURS_IN_A_DAY * MINUTES_IN_AN_HOUR
                * SECONDS_IN_A_MINUTE * MILLIS_IN_A_SECOND;
        return (int) ((endDate.getTime() - startDate.getTime()) / millSecondsInOneDay);
    }

    /**
     * Time1 time2 ratio calculated how many minutes late, ignoring the date portion
     *
     * @param time1 First time
     * @param time2 The second time
     * @return The number of minutes between the two time intervals
     */
    public static int getMinuteDiffByTime(Date time1, Date time2) {
        long startMil = 0;
        long endMil = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time1);
        calendar.set(1900, 1, 1);
        startMil = calendar.getTimeInMillis();
        calendar.setTime(time2);
        calendar.set(1900, 1, 1);
        endMil = calendar.getTimeInMillis();
        return (int) ((endMil - startMil) / MILLIS_IN_A_SECOND / SECONDS_IN_A_MINUTE);
    }

    /**
     * Calculate the day before the specified date
     *
     * @param date A date
     * @return Date the day before the date of the representative
     */
    public static Date getPrevDay(Date date) {
        return org.apache.commons.lang3.time.DateUtils.addDays(date, -1);
    }

    /**
     * After calculating the date specified day
     *
     * @param date A date
     * @return After the day's date on behalf of date
     */
    public static Date getNextDay(Date date) {
        return org.apache.commons.lang3.time.DateUtils.addDays(date, 1);
    }

    /**
     * Analyzing date1 is after date2, omit the time portion
     *
     * @param date1 First date
     * @param date2 The second date
     * @return If after date1 in date2, it returns true, false otherwise
     */
    public static boolean isDateAfter(Date date1, Date date2) {
        Date theDate1 = org.apache.commons.lang3.time.DateUtils.truncate(date1,
                Calendar.DATE);
        Date theDate2 = org.apache.commons.lang3.time.DateUtils.truncate(date2,
                Calendar.DATE);
        return theDate1.after(theDate2);
    }

    /**
     * Analyzing date1 is before date2, omit the time portion
     *
     * @param date1 First date
     * @param date2 The second date
     * @return If date1 in before date2, returns true, false otherwise
     */
    public static boolean isDateBefore(Date date1, Date date2) {
        return isDateAfter(date2, date1);
    }

    /**
     * Analyzing time1 is after time2, ignoring the date portion
     *
     * @param time1 First time
     * @param time2 The second time
     * @return If time1 time2 located after returns true, false otherwise
     */
    public static boolean isTimeAfter(Date time1, Date time2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(time1);
        calendar1.set(1900, 1, 1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(time2);
        calendar2.set(1900, 1, 1);
        return calendar1.after(calendar2);
    }

    /**
     * Analyzing time1 is before time2, ignoring the date portion
     *
     * @param time1 First time
     * @param time2 The second time
     * @return If located before time1 time2, returns true, false otherwise
     */
    public static boolean isTimeBefore(Date time1, Date time2) {
        return isTimeAfter(time2, time1);
    }

    /**
     * Determine whether the same day two dates (ignoring the time part)
     *
     * @param date1 First date
     * @param date2 The second date
     * @return If date1 and date2 represent the same date, returns true, false otherwise
     */
    public static boolean isSameDay(Date date1, Date date2) {
        return org.apache.commons.lang3.time.DateUtils.isSameDay(date1, date2);
    }

    /**
     * Determine whether the same day two calendar days (ignoring the time part)
     *
     * @param date1 First date
     * @param date2 The second date
     * @return If date1 and date2 represent the same date, returns true, false otherwise
     */
    public static boolean isSameDay(Calendar date1, Calendar date2) {
        return org.apache.commons.lang3.time.DateUtils.isSameDay(date1, date2);
    }

    /**
     * The string of dates expressed resolve to date objects
     *
     * @param dateString String representing the date
     * @return A date
     */
    public static Date parseDate(String dateString) {
        try {
            return org.apache.commons.lang3.time.DateUtils.parseDate(
                    dateString, new String[]{"yyyy-MM-dd", "yyyy-M-d",
                        "yyyy-MM-d", "yyyy-M-dd"});
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * The string representation of time to resolve as time target
     *
     * @param timeString String that represents the time
     * @return A date
     */
    public static Date parseTime(String timeString) {
        try {
            return org.apache.commons.lang3.time.DateUtils.parseDate(
                    timeString, new String[]{"hh:mm:ss", "h:m:s", "hh:mm",
                        "h:m"});
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * The string representation of the date and time resolved to time objects
     *
     * @param timeString String that represents the time
     * @return A date
     */
    public static Date parseDateTime(String timeString) {
        try {
            return org.apache.commons.lang3.time.DateUtils.parseDate(
                    timeString, new String[]{"yyyy-MM-dd HH:mm:ss",
                        "yyyy-M-d H:m:s", "yyyy-MM-dd H:m:s",
                        "yyyy-M-d HH:mm:ss"});
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * Calculated from the number of days between two dates a week contains X's.
     *
     * @param fromDate Start Date
     * @param toDate End Date
     * @param dayOfWeekWeek, for example, Wednesday, Thursday
     * @return The number of weeks between the two dates contain X's
     */
    public static int getWeekDaysBetween(Date fromDate, Date toDate,
            int dayOfWeek) {
        int result = 0;
        Date firstDate = getFirstWeekdayBetween(fromDate, toDate, dayOfWeek);
        if (firstDate == null) {
            return 0;
        }
        Calendar aDay = Calendar.getInstance();
        aDay.setTime(firstDate);
        while (aDay.getTime().before(toDate)) {
            result++;
            aDay.add(Calendar.DATE, DAYS_IN_A_WEEK);
        }
        return result;
    }

    /**
     * Get between the two dates the first week X
     *
     * @param fromDate Start Date
     * @param toDate End Date
     * @param dayOfWeek Week, for example, Wednesday, Thursday
     * @return Between the two dates the first week X
     */
    public static Date getFirstWeekdayBetween(Date fromDate, Date toDate,
            int dayOfWeek) {
        Calendar aDay = Calendar.getInstance();
        aDay.setTime(fromDate);
        while (aDay.getTime().before(toDate)) {
            if (aDay.get(Calendar.DAY_OF_WEEK) == dayOfWeek) {
                return aDay.getTime();
            }
            aDay.add(Calendar.DATE, 1);
        }
        return null;
    }

    /**
     * Get parameter specifies the number of the year year total day
     *
     * @param year Years
     * @return The total number of days the year
     */
    public static int getDaysInYear(int year) {
        Calendar aDay = Calendar.getInstance();
        aDay.set(year, 1, 1);
        Date from = aDay.getTime();
        aDay.set(year + 1, 1, 1);
        Date to = aDay.getTime();
        return getDayDiff(from, to);
    }

    /**
     * Made of the total number of days specified date
     *
     * @param year Years
     * @param month Month
     * @return The total number of days of the month
     */
    public static int getDaysInMonth(int year, int month) {
        Calendar aDay = Calendar.getInstance();
        aDay.set(year, month, 1);
        Date from = aDay.getTime();
        if (month == Calendar.DECEMBER) {
            aDay.set(year + 1, Calendar.JANUARY, 1);
        } else {
            aDay.set(year, month + 1, 1);
        }
        Date to = aDay.getTime();
        return getDayDiff(from, to);
    }

    /**
     * The year for the specified date
     *
     * @param date A date
     * @return Year date falls
     */
    public static int getYear(Date date) {
        return getFieldValue(date, Calendar.YEAR);
    }

    /**
     * Month obtain the specified date
     *
     * @param date A date
     * @return Month date falls
     */
    public static int getMonth(Date date) {
        return getFieldValue(date, Calendar.MONTH) + 1;
    }

    /**
     * For the specified date is the day of the year
     *
     * @param date A date
     * @return Date is the first year in a few days
     */
    public static int getDayOfYear(Date date) {
        return getFieldValue(date, Calendar.DAY_OF_YEAR);
    }

    /**
     * For the specified date is the day of the month
     *
     * @param date A date
     * @return Date is the first few days of the month in
     */
    public static int getDayOfMonth(Date date) {
        return getFieldValue(date, Calendar.DAY_OF_MONTH);
    }

    /**
     * For the specified date is the day of the week
     *
     * @param date A date
     * @return Date is the day in the week
     */
    public static int getDayOfWeek(Date date) {
        return getFieldValue(date, Calendar.DAY_OF_WEEK);
    }

    private static int getFieldValue(Date date, int field) {
        if (date == null) {
            throw new InvalidParameterException("date cannot be null!");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(field);
    }

    /**
     * The date for the specified period of time after the date. For example, three days after a date date.
     *
     * @param origDate Base Date
     * @param quantitat Number
     * @param timeUnit Time units, such as year, month, date and so on. Calendar of constant use on behalf of
     * @return A date
     */
    public static final Date dateAfter(Date origDate, int quantitat, int timeUnit) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(origDate);
        calendar.add(timeUnit, quantitat);
        return calendar.getTime();
    }

    /**
     * Obtain a specified date prior to the date a period. For example, a date three days before the date.
     *
     * @param origDate Base Date
     * @param quantitat Number
     * @param timeUnit Time units, such as year, month, date and so on. Calendar of constant use on behalf of
     * @return A date
     */
    public static final Date dateBefore(Date origDate, int quantitat, int timeUnit) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(origDate);
        calendar.add(timeUnit, -quantitat);
        return calendar.getTime();
    }
}
