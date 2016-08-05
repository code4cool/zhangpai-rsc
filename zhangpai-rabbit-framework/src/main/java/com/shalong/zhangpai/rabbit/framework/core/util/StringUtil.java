/**
 * @FileName: StringUtil.java
 * @Package com.rabbit.framework.core.util
 * 
 * Huangyunjun
 * @created 2016年07月18日 下午5:52:52
 * 
 
 */
package com.shalong.zhangpai.rabbit.framework.core.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * <p>字符串工具类</p>
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
public class StringUtil {
	
	static SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
	static SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 
	 * 截取目标字符串（即：target）最后一个标识符（即：separator）前的子串
	 *
	 * @author Huangyunjun
	 * @created 2016年07月16日  下午5:56:29
	 *
	 * @param target	目标字符串
	 * @param separator	标识符
	 * @return
	 */
	public static String substringBeforeLastIgnoreCase(String target, String separator) {
		if ("".equals(target))
			return target;
		if ("".equals(separator))
			return target;
		String tempStr = target.toUpperCase();
		String tempSeparator = separator.toUpperCase();

		int index = tempStr.lastIndexOf(tempSeparator);
		if (index == -1)
			return target;
		return target.substring(0, index);
	}
	
	/**
	 * 
	 * 单引号（“'”）逃逸，用于构建SQL语句时使用
	 *
	 * @author Huangyunjun
	 * @created 2016年07月16日  下午5:55:44
	 *
	 * @param target	目标字符串
	 * @return
	 */
	public static String singleQuotoAndEscape(String target) {
		if (!isEmpty(target)) {
			StringBuffer t = new StringBuffer(target.length() + 3);
			t.append("'");
			t.append(target.replaceAll("'", "''"));
			t.append("'");
			return t.toString();
		}
		return "''";
	}
	
	/**
	 * 
	 * 字符串非空判断
	 *
	 * @author Huangyunjun
	 * @created 2016年07月16日  下午5:55:27
	 *
	 * @param target	目标字符串
	 * @return
	 */
	public static final boolean isEmpty(String input) {
		return input == null || input.length() == 0 || input.trim().length() == 0;
	}

	/**
	 * 
	 * 字符串非空判断
	 *
	 * @author Huangyunjun
	 * @created 2016年07月16日  下午5:16:23
	 *
	 * @param input	目标字符串
	 * @return
	 */
	public static final boolean isNotEmpty(String input) {
		return !isEmpty(input);
	}
	
	/**
	 * 
	 * 字符串左补字符到固定长度（如：高位补0到10位）
	 *
	 * @author Huangyunjun
	 * @created 2016年07月16日 上午11:20:41
	 *
	 * @param input 原字符串
	 * @param padCode 补充字符
	 * @param toLength 到固定长度
	 * @return 补充完字符后字符串
	 */
	public static final String lpad(String input, String padCode, int toLength) {
		if (input != null && input.length() < toLength) {
			StringBuffer sb = new StringBuffer(input);
			while (sb.length() < toLength) {
				sb.insert(0, padCode);
			}
			return sb.toString();
		} else {
			return input;
		}
	}
	
	/**
	 * 
	 * 字符串右补字符到固定长度（如：地位补0到10位）
	 *
	 * @author Huangyunjun
	 * @created 2016年07月16日  上午11:20:41
	 *
	 * @param input 原字符串
	 * @param padCode 补充字符
	 * @param toLength 到固定长度
	 * @return 补充完字符后字符串
	 */
	public static final String rpad(String input, String padCode, int toLength) {
		if (input != null && input.length() < toLength) {
			StringBuffer sb = new StringBuffer(input);
			while (sb.length() < toLength) {
				sb.append(padCode);
			}
			return sb.toString();
		} else {
			return input;
		}
	}
	
	/**
	 * 
	 * 字符串首字母大小
	 *
	 * @author Huangyunjun
	 * @created 2016年07月16日  下午1:46:06
	 *
	 * @param target
	 * @return
	 */
	public static final String firstToUpperCase(String target){
		return target.substring(0,1).toUpperCase() + target.substring(1, target.length());
	}
	
	/**
	 * 
	 * 获取字符串所占字符数量
	 *
	 * @author Huangyunjun
	 * @created 2016年07月16日  下午4:13:14
	 *
	 * @param str
	 * @return
	 */
	public static int count(String str) {
		int length = 0;
		str = str.trim();
		if (isEmpty(str)) {
			return length;
		}
		char[] temp = str.toCharArray();
		for (char t : temp) {
			if (String.valueOf(t).matches("^[\\u4e00-\\u9fa5]*$")) {
				length += 2;
			} else if (String.valueOf(t).matches("^\\S*$")) {
				length += 1;
			}
		}
		return length;
	}
	
	public static String timestamp2string(Timestamp timestamp) {
		if(timestamp == null) {
			return null;
		}
		return yyyyMMddHHmmss.format(timestamp);
	}
	
	public static String date2string(java.sql.Date date) {
		if(date == null) {
			return null;
		}
		return yyyyMMdd.format(date);
	}
	
	/**
	 * String str = "a,b,c,d";
	 * 处理之后
	 * str = "'a','b','c','d'";
	 * 应用场景： sql语句的IN（%s）时，如果是"a,b,c,d"形式，这时我们需要转换成"'a','b','c','d'"
	 * 注意：在mysql中字段定义是varchar类型的需要转换。int型不用转换。
	 * @author Huangyunjun
	 * @created 2016年07月16日  下午2:56:38
	 *
	 * @param str
	 * @return
	 */
	public static String embraceComma(String str) {
		StringBuffer sb = new StringBuffer("'");
		char[] chars = str.toCharArray();
		for(char c : chars) {
			switch(c) {
			case 44 :
				sb.append("'").append(c).append("'");
				break;
			case 32 :
				break;
			case 0 :
				break;
			default:
				sb.append(c);
			}
		}
		sb.append("'");
		return sb.toString();
	}
	
	/**
	 * 将list转换成string，如"a,b,c,d" "123,345,567,789"
	 *
	 * @author Huangyunjun
	 * @created 2016年07月16日  上午10:45:44
	 *
	 * @param list
	 * @return
	 */
	public static String surroundByComma(List<?> list) {
		String split = "";
		if(list != null && !list.isEmpty()) {
			for(Object obj : list) {
				split += String.valueOf(obj) + ",";
			}
			split = split.substring(0, split.lastIndexOf(","));
		}
		return split;
	}
}
