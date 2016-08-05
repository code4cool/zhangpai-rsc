package com.shalong.zhangpai.rsc.provider.common.utils;
/**
 * @FileName: BusinessUtils.java
 * @Package com.shalong.zhangpai.rsc.provider.common.utils
 * 
 * @author Huangyunjun
 * @created 2016年7月16日 下午5:35:59
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

import java.util.HashMap;
import java.util.Map;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.shalong.zhangpai.rabbit.framework.base.model.DataModel;
import com.shalong.zhangpai.rabbit.framework.core.exception.BusinessException;
import com.shalong.zhangpai.rabbit.framework.core.log.RabbitLogger;
import com.shalong.zhangpai.rsc.thrift.enums.ReturnCode;

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
public class BusinessUtils {
	
	private static RabbitLogger LOGGER = RabbitLogger.getInstance(BusinessUtils.class);
	
	// service层常量
	public final static String ERROR_BUSINESS = "操作数据库出现异常.";
	public final static String ERROR_RET_VALIDATA = "参数验证失败,请检查.";
	public final static String ERROR_VALIDATA = "参数验证失败:";
	public final static String ERROR_JSON2OBJECT = "jsonArgs参数格式有问题或者数字类型错误,注意数字类型不要使用字符串类型.";
	public final static String ERROR_DATAOBJECT = "数据对象：";
	
	// dao层常量
	public final static String DB_ERROR_SEARCH = "数据库查询异常";
	public final static String DB_ERROR_ROLLBACK = "数据库回滚异常";
	public final static String DB_ERROR_CLOSE = "数据库关闭异常";
	public final static String DB_ERROR_EXECUTE = "操作数据库异常";
	
	/**
	 * 
	 * 业务异常
	 *
	 * @author Huangyunjun
	 * @created 2013年11月27日 下午7:16:03
	 *
	 * @return
	 */
	public static String errorBusiness() {
		return returnString(ReturnCode.SERVER_ERROR.getValue(), ERROR_BUSINESS, "", "");
	}
	
	/**
	 * 
	 * model验证错误
	 *
	 * @author Huangyunjun
	 * @created 2016年07月19日 下午7:10:58
	 *
	 * @return
	 */
	public static String errorValidata() {
		return returnString(ReturnCode.SERVER_BUSY.getValue(), ERROR_RET_VALIDATA, "", "");
	}
	
	
	/**
	 * 
	 * json参数转换成对象异常方法
	 *
	 * @author Huangyunjun
	 * @created 2016年07月19日 下午7:07:52
	 *
	 * @return
	 */
	public static String errorJson2Object() {
		return returnString(ReturnCode.SERVER_BUSY.getValue(), ERROR_JSON2OBJECT, "", "");
	}
	
	/**
	 * 
	 * 返回值
	 *
	 * @author Huangyunjun
	 * @created 2016年07月19日 下午6:52:47
	 *
	 * @param code
	 * @param msg
	 * @param key
	 * @param value
	 * @return
	 */
	public static String returnString(int code, String msg, String key, Object value) {
		DataModel dataModel = new DataModel();
		dataModel.setCode(code);
		dataModel.setMsg(msg);
		if (StringUtils.isNotEmpty(key)) {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put(key, value);
			dataModel.setData(data);
		}
		String jsonString = null;
		try {
			jsonString = dataModel.json();
		} catch (BusinessException e) {
			jsonString = "{\"code\":\""+ ReturnCode.SERVER_BUSY +"\",\"msg\":\"处理返回值转换成json串异常\"}";
			LOGGER.error(e.getMessage(), e);
		}
		return jsonString;
	}
	
	/**
	 * 
	 * 返回值
	 *
	 * @author Huangyunjun
	 * @created 2016年07月19日 下午6:52:47
	 *
	 * @param code
	 * @param msg
	 * @param value
	 * @return
	 */
	public static String returnString(int code, String msg, Map<String, Object> values) {
		DataModel dataModel = new DataModel();
		dataModel.setCode(code);
		dataModel.setMsg(msg);
		if (values != null) {
			dataModel.setData(values);
		}
		String jsonString = null;
		try {
			jsonString = dataModel.json();
		} catch (BusinessException e) {
			jsonString = "{\"code\":\""+ ReturnCode.SERVER_BUSY +"\",\"msg\":\"处理返回值转换成json串异常\"}";
			LOGGER.error(e.getMessage(), e);
		}
		return jsonString;
	}

}
