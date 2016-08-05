/**
 * @FileName: DateUtil.java
 * @Package com.rabbit.framework.core.util
 * 
 * Huangyunjun
 * @created 2016年07月18日 下午5:53:07
 * 
 
 */
package com.shalong.zhangpai.rabbit.framework.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.shalong.zhangpai.rabbit.framework.core.exception.BusinessException;

/**
 * <p>日期工具类</p>
 * 
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * <PRE>
 * 
 * @author Huangyunjun
 * @since 1.0
 * @version 1.0
 */
public class DateUtil {
	
	private static final Map<String, ThreadLocal<SimpleDateFormat>> timestampFormatPool = new HashMap<String, ThreadLocal<SimpleDateFormat>>();  
	
	private static final Map<String, ThreadLocal<SimpleDateFormat>> dateFormatPool = new HashMap<String, ThreadLocal<SimpleDateFormat>>();  
	
	private static final Object timestampFormatLock = new Object(); 
	
	private static final Object dateFormatLock = new Object(); 
	
	private static final Calendar cal = Calendar.getInstance(Locale.CHINA);
	
	private static String dateFormatPattern = "yyyy-MM-dd";
	
	private static String timestampPattern = "yyyy-MM-dd HH:mm:ss";
	
	private static SimpleDateFormat getDateFormat() {
		ThreadLocal<SimpleDateFormat> tl = dateFormatPool.get(dateFormatPattern);
		if (null == tl) {
			synchronized (dateFormatLock) {
				tl = dateFormatPool.get(dateFormatPattern);
				if (null == tl) {
					tl = new ThreadLocal<SimpleDateFormat>() {
						protected synchronized SimpleDateFormat initialValue() {
							return new SimpleDateFormat(dateFormatPattern);
						}
					};
					dateFormatPool.put(dateFormatPattern, tl);
				}
			}
		}
		return tl.get();
	}
	
	private static SimpleDateFormat getTimestampFormat() {
		ThreadLocal<SimpleDateFormat> tl = timestampFormatPool.get(timestampPattern);
		if (null == tl) {
			synchronized (timestampFormatLock) {
				tl = timestampFormatPool.get(timestampPattern);
				if (null == tl) {
					tl = new ThreadLocal<SimpleDateFormat>() {
						protected synchronized SimpleDateFormat initialValue() {
							return new SimpleDateFormat(timestampPattern);
						}
					};
					timestampFormatPool.put(timestampPattern, tl);
				}
			}
		}
		return tl.get();
	}


	
	/**
	 * 时间戳格式
	 */
//	private static SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 日期格式
	 */
//	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 
	 * 格式化成时间戳格式
	 *
	 * @author Huangyunjun
	 * @created 2016年07月16日 下午6:05:59
	 *
	 * @param date	要格式化的日期
	 * @return	"年年年年-月月-日日 时时:分分:秒秒"格式的日期字符串
	 */
	public static String timestampFormat(Date date){
		if(date == null){
			return "";
		}
//		return timestampFormat.format(date);
		return getTimestampFormat().format(date);
	}
	
	/**
	 * 
	 * 格式化成时间戳格式
	 *
	 * @author Huangyunjun
	 * @created 2016年07月16 下午6:14:18
	 *
	 * @param datetime	要格式化的日期
	 * @return	"年年年年-月月-日日 时时:分分:秒秒"格式的日期字符串
	 */
	public static String timestampFormat(long datetime){
//		return timestampFormat.format(new Date(datetime));
		return getTimestampFormat().format(new Date(datetime));
	}
	
	/**
	 * 
	 * 将"年年年年-月月-日日 时时:分分:秒秒"格式的日期字符串转换成Long型日期
	 *
	 * @author Huangyunjun
	 * @created 2016年07月16日 下午6:23:28
	 *
	 * @param timestampStr	年年年年-月月-日日 时时:分分:秒秒"格式的日期字符串
	 * @return	Long型日期
	 */
	public static long formatTimestampToLong(String timestampStr){
		Date date;
		try {
//			date = timestampFormat.parse(timestampStr);
			date = getTimestampFormat().parse(timestampStr);
		} catch (ParseException e) {
			return 0L;
		}
		return date.getTime();
	}
	
	/**
	 * 
	 * 格式化成日期格式
	 *
	 * @author Huangyunjun
	 * @created 2016年07月16日 下午6:10:42
	 *
	 * @param date	要格式化的日期
	 * @return	"年年年年-月月-日日"格式的日期字符串
	 */
	public static String dateFormat(Date date){
		if(date == null){
			return "";
		}
//		return dateFormat.format(date);
		return getDateFormat().format(date);
	}
	
	/**
	 * 
	 * 格式化成日期格式
	 *
	 * @author Huangyunjun
	 * @created 2016年07月16日 下午6:13:41
	 *
	 * @param datetime	要格式化的日期
	 * @return	"年年年年-月月-日日"格式的日期字符串
	 */
	public static String dateFormat(long datetime){
//		return dateFormat.format(new Date(datetime));
		return getDateFormat().format(new Date(datetime));
	}
	
	/**
	 * 
	 * 将"年年年年-月月-日日"格式的日期字符串转换成Long型日期
	 *
	 * @author Huangyunjun
	 * @created 2016年07月16日 下午6:25:21
	 *
	 * @param dateStr	"年年年年-月月-日日"格式的日期字符串
	 * @return	Long型日期
	 * @throws BusinessException	日期格式化异常
	 */
	public static long formatDateToLong(String dateStr) throws BusinessException {
		Date date;
		try {
//			date = dateFormat.parse(dateStr);
			date = getDateFormat().parse(dateStr);
		} catch (ParseException e) {
			throw new BusinessException("将输入内容格式化成日期类型时出错。", e);
		}
		return date.getTime();
	}
	


    /**
     * 
     * 得到本月的第一天
     *
     * @author Huangyunjun
     * @created 2016年07月16日 下午6:33:43
     *
     * @return	以"年年年年-月月-日日"格式返回当前月第一天的日期
     */
    public static String getFirstDayOfCurrentMonth() {   
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar
                .getActualMinimum(Calendar.DAY_OF_MONTH));
//        return dateFormat.format(calendar.getTime());
        return getDateFormat().format(calendar.getTime());
    }   

    /**
     * 
     * 得到本月的最后一天 
     *
     * @author Huangyunjun
     * @created 2016年07月16日 下午6:39:38
     *
     * @return	以"年年年年-月月-日日"格式返回当前月最后一天的日期
     */
    public static String getLastDayOfCurrentMonth() {   
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
//        return dateFormat.format(calendar.getTime());
        return getDateFormat().format(calendar.getTime());
    }
    
    /**
     * 
     * 获取指定日期所在月的第一天
     *
     * @author Huangyunjun
     * @created 2016年07月16日 下午6:45:07
     *
     * @param date	日期
     * @return	以"年年年年-月月-日日"格式返回当指定月第一天的日期
     */
    public static String getFirstDayOfMonth(Date date) {   
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.set(Calendar.DAY_OF_MONTH, 1);
//		return dateFormat.format(ca.getTime());
		return getDateFormat().format(ca.getTime());
    }   
    
    /**
     * 
     * 获取指定日期所在月的最后一天
     *
     * @author Huangyunjun
     * @created 2016年07月16日 下午7:04:10
     *
     * @param date
     * @return	以"年年年年-月月-日日"格式返回当指定月最后一天的日期
     */
    public static String getLastDayOfMonth(Date date) {   
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.set(Calendar.DAY_OF_MONTH, 1);
		ca.roll(Calendar.DAY_OF_MONTH, -1);
//		return dateFormat.format(ca.getTime());
		return getDateFormat().format(ca.getTime());
    }
    
    /**
     * 
     * 获取指定日期所在周的第一天
     *
     * @author Huangyunjun
     * @created 2016年07月16日 下午6:45:07
     *
     * @param date	日期
     * @return	以"年年年年-月月-日日"格式返回当指定周第一天的日期
     */
    public static String getFirstDayOfWeek(Date date) {   
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.set(Calendar.DAY_OF_WEEK, 2);
//		return dateFormat.format(ca.getTime());
		return getDateFormat().format(ca.getTime());
    }   
    
    /**
     * 
     * 获取指定日期所在月的最后一天
     *
     * @author Huangyunjun
     * @created 2016年07月16日 下午7:04:10
     *
     * @param date
     * @return	以"年年年年-月月-日日"格式返回当指定周最后一天的日期
     */
    public static String getLastDayOfWeek(Date date) {   
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.set(Calendar.DAY_OF_WEEK, 2);
		ca.set(Calendar.DAY_OF_WEEK, -1);
		ca.add(Calendar.DATE, 2);
//		return dateFormat.format(ca.getTime());
		return getDateFormat().format(ca.getTime());
    }
    
    /**
     * 
     * 获取当前日期的前一天
     *
     * @author Huangyunjun
     * @created 2016年07月16日 上午8:58:12
     *
     * @return	以"年年年年-月月-日日"格式返回当前日期的前一天的日期
     */
	public static String getDayBeforeCurrentDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
//		return dateFormat.format(calendar.getTime());
		return getDateFormat().format(calendar.getTime());
	}
	
	/**
	 * 
	 * 获取指定日期的前一天
	 *
	 * @author Huangyunjun
	 * @created 2016年07月16日 上午9:35:59
	 *
	 * @param date
	 * @return	以"年年年年-月月-日日"格式返回指定日期的前一天的日期
	 */
	public static String getDayBeforeDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -1);
//		return dateFormat.format(calendar.getTime());
		return getDateFormat().format(calendar.getTime());
	}
	
	/**
	 * 
	 * 获取当前日期的后一天
	 *
	 * @author Huangyunjun
	 * @created 2016年07月16日 上午9:44:36
	 *
	 * @return	以"年年年年-月月-日日"格式返回当前日期的后一天的日期
	 */
	public static String getDayAfterCurrentDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 1);
//		return dateFormat.format(calendar.getTime());
		return getDateFormat().format(calendar.getTime());
	}
	
	/**
	 * 
	 * 获取当前日期的后一天
	 *
	 * @author Huangyunjun
	 * @created 2016年07月16日 上午9:44:36
	 *
	 * @return	以"年年年年-月月-日日"格式返回指定日期的后一天的日期
	 */
	public static String getDayAfterDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
//		return dateFormat.format(calendar.getTime());
		return getDateFormat().format(calendar.getTime());
	}
	
    /**
     * 
     * 获取当前时间，精确到秒
     *
     * @author Huangyunjun
     * @created 2016年07月16日 下午4:40:19
     *
     * @return	精确到秒的当前时间
     */
    public static int currentTimeSecond(){
        return Long.valueOf(System.currentTimeMillis()/1000).intValue();
    }
    
    /**
     * 
     * 替换掉日期格式中所有分隔符（含“-”、“:”及空格）
     *
     * @author Huangyunjun
     * @created 2016年07月16日 下午1:17:18
     *
     * @param target	字符型目标日期
     * @return	替换后的结果
     */
    public static String replaceAllSeparator(String target) {
    	return target.replace("-", "").replace(":", "").replace(" ", "");
    }
    
    /**
     * 
     * 替换掉日志格式中指定的分隔符
     *
     * @author Huangyunjun
     * @created 2016年07月16日 下午1:18:01
     *
     * @param target	字符型目标日期
     * @param separator	要被替换掉的分割符
     * @return	替换后的结果
     */
    public static String replaceSeparator(String target, String... separator) {
    	String temp = target;
    	for (String sep : separator) {
    		temp = temp.replace(sep, "");
    	}
    	return temp;
    }
    
    /**
     * 当前为一年的第几周
     *
     * @author Huangyunjun
     * @created 2016年07月16日 下午1:50:54
     *
     * @return
     */
    public static int currentWeekOfYear() {
    	return cal.get(Calendar.WEEK_OF_YEAR) - 1;
    }
    
    /**
     * 
     * 获取当前时间戳
     *
     * @author Huangyunjun
     * @created 2016年07月16日 下午5:22:52
     *
     * @return
     */
    public static long getCurrentTime() {
    	return new Date().getTime();
    }
    
    /**
     * 
     * 获取当前开始时间  timestamp类型
     *
     * @author Huangyunjun
     * @created 2016年07月16日 下午5:37:37
     *
     * @return
     */
    public static String getCurrentTimeStampStart() {
    	return dateFormat(new Date()) + " 00:00:00";
    }
    
    /**
     * 
     * 获取当前结束时间  timestamp类型
     *
     * @author Huangyunjun
     * @created 2016年07月16日 下午5:37:37
     *
     * @return
     */
    public static String getCurrentTimeStampEnd() {
    	return dateFormat(new Date()) + " 23:59:59";
    }
    
    /**
     * 上午，下午还是晚上
     *
     * @param type
     * @return
     */
    public static String theMorningAfternoonEvening(int type) {
    	String result = null;
    	switch(type) {
    	case 1:
    		result = "上午";
    	case 2:
    		result ="下午";
    	case 3:
    		result = "晚上";
    	default :
    		result = "上午";
    	}
    	return result;
    }
    
    public static String dateAdd(String date,int off) throws ParseException {
    	Calendar cal = Calendar.getInstance();
    	Date d = getDateFormat().parse(date);
    	cal.setTime(d);
    	cal.add(Calendar.DATE, off);
    	
    	return getDateFormat().format(cal.getTime());
    }
    
    public static double date2double(String dateString) throws ParseException {
    	Date date = getDateFormat().parse(dateString);
    	long time = date.getTime();
    	return (double)time;
    }

	/**
	 * 日期相减
	 * @return
	 * @throws ParseException 
	 */
	public static int dateDiff(String date1, long date2) throws ParseException {
		long time = getDateFormat().parse(date1).getTime();
		return dateDiff(time,date2);
	}

	public static int dateDiff(long date1, long date2) {
		long diff = date1 - date2;
		System.out.println(diff);
		long day = diff / (1000 * 3600 * 24); 
		return (int)day;
	}
	
	public static Date afterNday(int n) {
		
		 Calendar cal = Calendar.getInstance();
		 if(n > 0) {
			 cal.add(Calendar.DATE, n);
		 }
		 long millis = cal.getTimeInMillis();
		 Date date = new Date(millis);
		 return date;
	}
}
