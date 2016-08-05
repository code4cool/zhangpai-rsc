package com.shalong.zhangpai.rabbit.framework.base.model;
/**
 * @FileName: ModelMapper.java
 * @Package com.shalong.zhangpai.rabbit.framework.base.model
 * 
 * @author Huangyunjun
 * @created 2016年7月16日 下午2:22:16
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 	thrift生成的数据模型和业务数据模型映射类
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
public class ModelMapper {
	
	/**
	 * 
	 * 映射方法
	 *
	 * @author Huangyunjun
	 * @created 2016年07月18日 下午5:34:48
	 *
	 * @param resObject 源对象, 该对象包含具体的业务数据
	 * @param disClass 目标对象， 最终转换的对象
	 * @return 返回转换成的目标对象
	 * @throws Exception
	 */
	public <T> T mapper(Object resObject, Class<T> disClass) throws Exception{
		if (disClass == null || resObject == null) {
			return null;
		}
		// 存放目标对象的方法
		Map<String, Method> disMethodMap = getMethodMap(disClass);
		T t = disClass.newInstance();
		Field [] resFields = resObject.getClass().getDeclaredFields();
		Method getMethod = null;
		Method setMethod = null;
		Object resultObj = null;
		for (Field resField : resFields) {
			String resFieldName = resField.getName();
			if ("serialVersionUID".equals(resFieldName)) {
				continue;
			}
			String setMethodName = "set" + resFieldName.substring(0,1).toUpperCase() + resFieldName.substring(1, resFieldName.length());
			if (disMethodMap.containsKey(setMethodName)) {
				String getMethodName = "get" + resFieldName.substring(0,1).toUpperCase() + resFieldName.substring(1, resFieldName.length());
				getMethod = resObject.getClass().getMethod(getMethodName);
				resultObj = getMethod.invoke(resObject);
				setMethod = disMethodMap.get(setMethodName);
				setMethod.invoke(t, resultObj);
			}
		}
		return t;
	}
	
	/**
	 * 
	 * 映射方法
	 *
	 * @author Huangyunjun
	 * @created 2016年07月18日  下午5:38:48
	 *
	 * @param resObjects 源对象集合
	 * @param disClass 目标对象
	 * @return 目标对象集合
	 * @throws Exception
	 */
	public <T> List<T> mapperList(List<? extends Object> resObjects, Class<T> disClass) throws Exception {
		if (disClass == null || resObjects == null || resObjects.size() == 0) {
			return null;
		}
		T t;
		List<T> list = new ArrayList<T>();
		for (Object resObject : resObjects) {
			t = mapper(resObject, disClass);
			if (t != null) {
				list.add(t);
			}
		}
		return list;
	}
	
	/**
	 * 
	 * 获取对象所有的方法map<方法名,Method对象>
	 *
	 * @author Huangyunjun
	 * @created 2016年07月18日  下午5:29:52
	 *
	 * @param clazz
	 * @return
	 */
	private Map<String, Method> getMethodMap(Class<?> clazz){
		Map<String, Method> methodMap = new HashMap<String, Method>();
		Method [] disMethods = clazz.getDeclaredMethods();
		for (Method disMethod : disMethods) {
			methodMap.put(disMethod.getName(), disMethod);
		}
		return methodMap;
	}
	
	

}

