package com.shalong.zhangpai.rabbit.framework.base.model;
/**
 * @FileName: BaseModel.java
 * @Package com.shalong.zhangpai.rabbit.framework.base.model
 * 
 * @author Huangyunjun
 * @created 2016年7月16日 下午2:18:28
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * <p>
 * 实体基类
 * </p>
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
public class BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3915574660761747303L;

	/**
	 * 
	 * toString方法，返回属性名称及值
	 * 
	 * @author Huangyunjun
	 * @created 2016年07月19日 上午10:16:37
	 * 
	 * @return 属性名称及值，格式：[name]张三 [sex]男
	 */
	public String toString() {
		StringBuffer result = new StringBuffer();
		try {
			Class<? extends Object> clazz = this.getClass();
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				String fieldName = field.getName();
				if ("serialVersionUID".equals(fieldName)) {
					continue;
				}
				String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
				Method method = null;
				Object resultObj = null;
				method = clazz.getMethod(methodName);
				resultObj = method.invoke(this);
				if (resultObj != null && !"".equals(resultObj)) {
					result.append("[").append(fieldName).append("]").append(resultObj).append(" ");
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return result.toString();
	}

}
