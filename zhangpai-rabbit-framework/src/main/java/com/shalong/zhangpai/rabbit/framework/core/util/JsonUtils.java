/**
 * @FileName: JsonUtils.java
 * @Package com.rabbit.framework.core.util
 * 
 * Huangyunjun
 * @created 2016年07月18日 下午5:51:04
 * 
 
 */
package com.shalong.zhangpai.rabbit.framework.core.util;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.ParseException;
import com.shalong.zhangpai.rabbit.framework.core.exception.BusinessException;

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
public class JsonUtils {
	
	
	/**
	 * 
	 * json转换成指定类型的对象
	 *
	 * @author Huangyunjun
	 * @created 2016年07月16日 下午6:02:19
	 *
	 * @param paramString
	 * @param clazz
	 * @return
	 */
	public static <T extends Object> T json2Object(String paramString, Class<T> clazz) throws BusinessException{
		T t = null;
		try {
			t = JSON.parse(paramString, clazz);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new BusinessException("json转换成指定对象异常", e);
		}
		return t;
	}
	
	
 

}
