package com.shalong.zhangpai.rabbit.framework.data.database;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shalong.zhangpai.rabbit.framework.core.util.StringUtil;

public class DataMapper {

	public <T> T mapper(ResultSet rs, Class<T> clazz) throws Exception {
		if (rs == null || rs.wasNull() || !rs.next() || clazz == null) {
			return null;
		}
		Object value;
		Method method;
		T t = clazz.newInstance();
		ResultSetMetaData metaData = rs.getMetaData();
		Map<String, Method> methodMap = getMethodMap(clazz);
		for (int i = 0; i < metaData.getColumnCount();) {
			method = methodMap.get(metaData.getColumnLabel(++i));
			if (method != null) {
				value = rs.getObject(i);				
				if (value == null) {
					continue;
				}
				if (value instanceof Integer) {
					value = ((Number) value).intValue();
				} else if (value instanceof Long) {
					value = ((Number) value).longValue();
				} else if (value instanceof java.sql.Timestamp) {
					value = StringUtil.timestamp2string((Timestamp)value);
				}
				method.invoke(t, value);
			}
		}
		return t;
	}

	public <T> List<T> mapperSet(ResultSet rs, Class<T> clazz) throws Exception {
		if (rs == null || rs.wasNull() || clazz == null) {
			return null;
		}
		Object value;
		Method method;
		ResultSetMetaData metaData = rs.getMetaData();
		Map<String, Method> methodMap = getMethodMap(clazz);
		List<T> ret = new ArrayList<T>();
		while (rs.next()) {
			T t = clazz.newInstance();
			for (int i = 0; i < metaData.getColumnCount();) {
				method = methodMap.get(metaData.getColumnLabel(++i));
				if (method != null) {
					value = rs.getObject(i);
					if (value == null) {
						continue;
					}
					if (value instanceof Integer) {
						value = ((Number) value).intValue();
					} else if (value instanceof Long) {
						value = ((Number) value).longValue();
					}else if(value instanceof BigDecimal){ //?需要考虑
						value =((Number) value).intValue();
					}
					method.invoke(t, value);
				}
			}
			if (t != null) {
				ret.add(t);
			}
		}
		return ret;
	}

	private Map<String, Method> getMethodMap(Class<?> clazz)
			throws SQLException {
		Map<String, Method> methodMap = new HashMap<String, Method>();
		Method[] methods = clazz.getMethods();
		if (methods != null)
			for (int i = 0; i < methods.length; i++) {
				Column column = methods[i].getAnnotation(Column.class);
				if (column != null && StringUtil.isNotEmpty(column.value())) {
					methodMap.put(column.value(), methods[i]);
				}
			}
		return methodMap;
	}
}
