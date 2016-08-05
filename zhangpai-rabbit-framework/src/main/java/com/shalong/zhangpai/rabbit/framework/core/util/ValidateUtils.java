/**
 * @FileName: ValidateUtils.java
 * @Package com.rabbit.framework.utils
 * 
 * @author narisu
 * @created 2013-11-13 下午8:03:31
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */
package com.shalong.zhangpai.rabbit.framework.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>TODO</p>
 * 
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 * 
 * @author Huangyunjun
 * @since 1.0
 * @version 1.0
 */
public class ValidateUtils {

	public final static String mobileRegex = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
//	public final static String passportRegex = "^1[45][0-9]{7}|G[0-9]{8}|P[0-9]{7}|S[0-9]{7,8}|D[0-9]+$";
	//总长度最多输入32位，最少输入7位；只允许输入数字，字母+数字，不允许输入汉字，符号
	public final static String passportRegex = "^[A-Za-z0-9]{7,32}$";
	public final static String soldierPegex = "^(\\S+)字第((\\d|-)+)号";
	public final static Pattern mobilePattern = Pattern.compile(mobileRegex);
	public final static Pattern passportPattern = Pattern.compile(passportRegex);
	public final static Pattern soldierPattern = Pattern.compile(soldierPegex);
	/**
	 * 验证UserId的合法性
	 * 
	 * @author Huangyunjun
	 * @created 2016年07月16日  下午8:06:49
	 *
	 * @param userId
	 * @return
	 */
	public static boolean isUserId(int userId) {
		if(userId < 1000) // || userId > Integer.MAX_VALUE) 
		{
			return false;
		}
		return true;
	}
	/**
	 * 验证UserId的合法性
	 *
	 * @author Huangyunjun
	 * @created 2016年07月16日  下午8:07:29
	 *
	 * @param userId
	 * @return
	 */
	public static boolean isNotUserId(int userId) {
		return !isUserId(userId);
	}
	
	/**
	 * 验证diseaseId的合法性
	 *
	 * @author Huangyunjun
	 * @created 2016年07月16日 下午8:23:53
	 *
	 * @param diseaseId
	 * @return
	 */
	public static boolean isDiseaseId(int diseaseId) {
		if(diseaseId < 0) {
			return false;
		}
		return true;
	}
	/**
	 * 验证diseaseId的合法性
	 *
	 * @author Huangyunjun
	 * @created 2016年07月16日 下午8:23:53
	 *
	 * @param diseaseId
	 * @return
	 */
	public static boolean isNotDiseaseId(int diseaseId) {
		return !isDiseaseId(diseaseId);
	}
	
	
	public static boolean isMobile(String mobile) {
		if(mobile == null || "".equals(mobile)) {
			return false;
		}
		
		if(mobile.contains("+")) {
			mobile = mobile.replace("+", "");
		}
		
		if(mobile.startsWith("86")) {
			mobile = mobile.replaceFirst("86", "");
		} 
		Matcher m = mobilePattern.matcher(mobile);
		return m.matches();
	}
	
	public static boolean isPassport(String passport) {
		
		if(StringUtil.isEmpty(passport)) {
			return false;
		}
		
		Matcher m = passportPattern.matcher(passport);
		return m.matches();
	}
	
	public static boolean isSoldier(String soldier) {
		
		if(StringUtil.isEmpty(soldier)) {
			return false;
		}
		
		Matcher m = soldierPattern.matcher(soldier);
		return m.matches();
		
	}
}
