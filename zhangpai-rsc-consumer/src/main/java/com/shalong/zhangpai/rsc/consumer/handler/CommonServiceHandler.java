package com.shalong.zhangpai.rsc.consumer.handler;
/**
 * @FileName: CommonServiceHandler.java
 * @Package com.shalong.zhangpai.rsc.consumer.handler
 * 
 * @author Huangyunjun
 * @created 2016年7月22日 下午9:52:15
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.thrift.TException;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.shalong.zhangpai.rabbit.framework.core.log.RabbitLogger;
import com.shalong.zhangpai.rabbit.framework.core.util.JsonUtils;
import com.shalong.zhangpai.rsc.consumer.main.SpringContext;
import com.shalong.zhangpai.rsc.thrift.enums.ReturnCode;
import com.shalong.zhangpai.rsc.thrift.service.CommonService.Iface;
import com.shalong.zhangpai.rsc.thrift.service.ServiceMainInput;
 

/**
 * <p>公共服务处理</p>
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
public class CommonServiceHandler implements Iface {
	
	private static RabbitLogger LOGGER = RabbitLogger.getInstance(CommonServiceHandler.class);

	/* (non-Javadoc)
	 * @see com.shalong.zhangpai.rsc.thrift.service.CommonService.Iface#serviceMain(com.shalong.zhangpai.rsc.thrift.service.ServiceMainInput)
	 */
	@Override
	public String serviceMain(ServiceMainInput input) throws TException {
		long sumTime = 0L;
		String result = null;
		if (input == null) {
			result = "{\"code\":\""+ ReturnCode.C_DATANULL.getValue() +"\",\"msg\":\"参数为null\"}";
			LOGGER.error(result, null);
			return result;
		}
		String serviceName = input.getServiceName();
		String methodName = input.getMethodName();
		String secretArgs = input.getSecretArgs();
		String jsonArgs = input.getJsonArgs();
		
		LOGGER.info("serviceName:"+serviceName + "  methodName:" + methodName + "  secretArgs:" + secretArgs +"  jsonArgs:" + jsonArgs);
		
		
		
		
		if (StringUtils.isNotEmpty(serviceName) && StringUtils.isNotEmpty(methodName) && StringUtils.isNotEmpty(secretArgs) && StringUtils.isNotEmpty(jsonArgs)) {
			boolean flag = true;
			Map<String, Object> errorSecretKeyMap = null;
			try {
				errorSecretKeyMap = identifySecretArgs(secretArgs);
				flag = (Boolean)errorSecretKeyMap.get("flag");
			} catch (Exception e1) {
				result = "{\"code\":\""+ ReturnCode.C_EXCEPTION.getValue() +"\",\"msg\":\"签名验证错误,请检查签名参数.\"}";
				LOGGER.error(e1.getMessage(), e1);
				return result;
			}
			Map<String, Object> errorJsonKeyMap = null;
			try {
				errorJsonKeyMap = identifyJsonArgs(jsonArgs);
				flag = (Boolean)errorJsonKeyMap.get("flag");
			} catch (Exception e1) {
				result = "{\"code\":\""+ ReturnCode.C_EXCEPTION.getValue() +"\",\"msg\":\"参数错误,请检查参数.\"}";
				LOGGER.error(e1.getMessage(), e1);
				return result;
			}
			
			
			 
			if (flag) {
				try {
					long startTime = System.currentTimeMillis();
					result = invoke(serviceName, methodName, new Class<?> [] {String.class}, new Object [] {jsonArgs});
					LOGGER.info("result:" + result);
					long endTime = System.currentTimeMillis();
					sumTime = endTime - startTime;
				} catch (Exception e) {
					result = "{\"code\":\""+ ReturnCode.C_EXCEPTION.getValue() +"\",\"msg\":\"消费者调用提供者异常\"}";
					LOGGER.error(e.getMessage(), e);
				}
			} else {
				String errorKey = (String)errorSecretKeyMap.get("errorKey") +"," + (String)errorJsonKeyMap.get("errorKey");
				
				
				result = "{\"code\":\""+ ReturnCode.C_EXCEPTION.getValue() +"\",\"msg\":\"参数不能为空,错误参数列表: "+errorKey+"\"}";
			}
		} else {
			result = "{\"code\":\""+ ReturnCode.C_DATANULL.getValue() +"\",\"msg\":\"参数中的值为空异常"+"serviceName:" + serviceName + "  methodName:" + methodName + "  jsonArgs:" + jsonArgs + "\"}";
			LOGGER.error(result, null);
		}
		LOGGER.info("总耗时：" + sumTime + " 服务名:"+serviceName + "  方法名:" + methodName + " 返回结果:" +result);
		return result;
	}
	/**
	 * 
	 * 签名验证参数不能为空
	 * 所有参数传递字符串
	 *
	 * @author Huangyunjun
	 * @created 2016年07月22日 下午1:41:49
	 *
	 * @param secretArgs
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String,Object> identifySecretArgs(String secretArgs) throws Exception{
		Map<String,Object> map = new HashMap<String, Object>();
		StringBuffer errorKey = new StringBuffer();
		boolean flag = true;
		Map<String,Object> paramMap = JsonUtils.json2Object(secretArgs, Map.class);
		if (paramMap == null) {
			flag = false;
			map.put("flag", flag);
			map.put("errorKey", "签名json转换成map为空");
			return map;
		} 
		String key = null;
		String value = null;
		for (Entry<String, Object> item : paramMap.entrySet()) {
			key = item.getKey();
			value = item.getValue().toString();
			if (StringUtils.isEmpty(value)) {
				flag = false;
				errorKey.append(key+",");
			}
		}
		map.put("flag", flag);
		map.put("errorKey", errorKey.toString());
		return map;
	}
	
	
	/**
	 * 
	 * 严格验证参数不能为空
	 * 所有参数传递字符串
	 *
	 * @author Huangyunjun
	 * @created 2016年07月22日 下午1:41:49
	 *
	 * @param jsonArgs
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String,Object> identifyJsonArgs(String jsonArgs) throws Exception{
		Map<String,Object> map = new HashMap<String, Object>();
		StringBuffer errorKey = new StringBuffer();
		boolean flag = true;
		Map<String,Object> paramMap = JsonUtils.json2Object(jsonArgs, Map.class);
		if (paramMap == null) {
			flag = false;
			map.put("flag", flag);
			map.put("errorKey", "json转换成map为空");
			return map;
		} 
		String key = null;
		String value = null;
		for (Entry<String, Object> item : paramMap.entrySet()) {
			key = item.getKey();
			value = item.getValue().toString();
			if (StringUtils.isEmpty(value)) {
				flag = false;
				errorKey.append(key+",");
			}
		}
		map.put("flag", flag);
		map.put("errorKey", errorKey.toString());
		return map;
	}
	
	/**
	 * 
	 * 直接调用服务并返回值
	 *
	 * @author Huangyunjun
	 * @created 2016年07月22日 下午3:32:07
	 *
	 * @param serviceName
	 * @param methodName
	 * @param argsClazz
	 * @param argsValue
	 * @return
	 * @throws Exception 
	 */
	private String invoke(String serviceName, String methodName, Class<?>[] argsClazz, Object[] argsValue) throws Exception{
	    String result = null;
	    Object service = SpringContext.getServiceObject(serviceName);
	    if(service != null) {
	    	LOGGER.info("invoke service name :" + serviceName);
	    	Method method = service.getClass().getMethod(methodName, argsClazz);
	    	result = (String)method.invoke(service, argsValue);
	    } else {
	    	LOGGER.info("invoke service is null.serviceName:" + serviceName);
	    }
	    return result;
	  }
	
	
	

}
