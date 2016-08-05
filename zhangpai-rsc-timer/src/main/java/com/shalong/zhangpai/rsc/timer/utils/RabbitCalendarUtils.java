package com.shalong.zhangpai.rsc.timer.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.shalong.zhangpai.rsc.timer.enumer.WeekReference;

/**
 * @FileName: RabbitCalendarUtils.java
 * @Package com.shalong.zhangpai.rsc.timer.utils
 * 
 * @author Huangyunjun
 * @created 2016年7月30日 下午8:55:36
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

public class RabbitCalendarUtils {

	
	/**
	 * 一分钟
	 */
	private static final long MINUTES = 6 * 1000L;
	/**
	 * 一小时 秒
	 */
	private static final long HOUR = 60 * MINUTES;
	
	private static final SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
	
	private static final SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static final SimpleDateFormat HH = new SimpleDateFormat("HH");
	/**
	 * 当前23:59:59的Date对象
	 * @return java.util.Date
	 */
	public static Date dayPoints() {

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		
		cal.set(year, month, day, 23, 59, 59);
		
		return cal.getTime();
	}
	
	/**
	 * @return 一小时的 秒
	 */
	public static long oneHour() {
		return HOUR;
	}
	
	/**
	 * @return 24小时的秒数
	 */
	public static long twentyFourHour() {
		return 24 * HOUR;
	}
	
	public static String date2string(Date date) {
		return yyyyMMddHHmmss.format(date);
	}
	
	public static String sqldate2string(java.sql.Date date) {
		return yyyyMMddHHmmss.format(date);
	}
	
	public static long long2interval(long interval) {
		return interval / HOUR;
	}

	/**
	 * 5分钟后
	 * @return
	 */
	public static long after5minutes() {
		return 5 * MINUTES;
	}
	
	public static int getWeekOfDate(Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w <= 0)
            w = 7;
        return w;
    }
	
	public static int getWeekOfDate(int n) {
		Date date = afterNday(n);
        return getWeekOfDate(date) ;
    }
	
	public static int getWeekOfDate() {
		Date date = after7day();
        return getWeekOfDate(date) ;
    }
	
	public static Date after7day() {
		 Calendar cal = Calendar.getInstance();
		 cal.add(Calendar.DATE, 7);
		 return cal.getTime();
	}
	
	public static java.sql.Date after7sqlday() {
		 Calendar cal = Calendar.getInstance();
		 cal.add(Calendar.DATE, 7);
		 long millis = cal.getTimeInMillis();
		 java.sql.Date date = new java.sql.Date(millis);
		 return date;
	}
	
	public static java.sql.Date afterNsqlday(int n) {
		
		 Calendar cal = Calendar.getInstance();
		 if(n > 0) {
			 cal.add(Calendar.DATE, n);
		 }
		 long millis = cal.getTimeInMillis();
		 java.sql.Date date = new java.sql.Date(millis);
		 return date;
	}
	
	public static String genColumn(int n) {
		int week = getWeekOfDate(n);
		return WeekReference.getWeek(week);
	}
	
	public static Date afterNday(int n) {
		 Calendar cal = Calendar.getInstance();
		 if(n > 0) {
			 cal.add(Calendar.DATE, n);
		 }
		 return cal.getTime();
	}
	
	public static int randomInt(int bhour,int ehour) {
		int x = (int) ((Math.random() * (ehour - bhour)) + bhour);
		return x;
	}

	public static String timestamp2string(Timestamp timestamp) {
		return yyyyMMddHHmmss.format(timestamp);
	}

	public static java.sql.Date timestamp2sqldate(Timestamp timestamp) {
		return new java.sql.Date(timestamp.getTime());
	}
	
	public static long currentTime() {
		return System.currentTimeMillis();
	}
	
	/**
	 * 当前时间 小时数
	 * @return
	 */
	public static int getCurrentHour() {
		String hourString = HH.format(new java.util.Date());
		return Integer.valueOf(hourString);
	}
	
	/**
	 * 次日结束的字符串拼接
	 * @return
	 */
	public static String getNextDayEnd() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		
		cal.set(year, month, day, 23, 59, 59);
		
		cal.add(Calendar.DAY_OF_MONTH, 1);
		
		return date2string(cal.getTime());
	}
	
	public static String getCurrentStartTime() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		
		return date2string(cal.getTime());
	}

	public static String getSpecifiedStartTime(int hour) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return date2string(cal.getTime());
	}

	public static Date nextPunctualHour() {
		Calendar currentTime = Calendar.getInstance();
		currentTime.setTime(new Date());

		int currentHour = currentTime.get(Calendar.HOUR);

		currentTime.set(Calendar.HOUR, currentHour + 1);
		currentTime.set(Calendar.MINUTE, 0);
		currentTime.set(Calendar.SECOND, 0);
		currentTime.set(Calendar.MILLISECOND, 0);

		Date nextHour = currentTime.getTime();

		return nextHour;
	}
}
