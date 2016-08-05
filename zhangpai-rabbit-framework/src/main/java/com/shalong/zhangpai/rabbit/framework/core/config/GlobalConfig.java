/**
 * @FileName: GlobalConfig.java
 * @Package com.rabbit.framework.core.config
 * 
 * @author narisu
 * @created 2013-11-19 下午2:04:09
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */
package com.shalong.zhangpai.rabbit.framework.core.config;

import java.util.Properties;

import com.shalong.zhangpai.rabbit.framework.core.log.RabbitLogger;
import com.shalong.zhangpai.rabbit.framework.core.util.StringUtil;

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
public class GlobalConfig {
	
	private static RabbitLogger LOGGER = RabbitLogger.getInstance(GlobalConfig.class);

	static Properties redisProperties = null;
	static Properties providerProperties = null;
	static ConfigurationManager manager = null;
	static {
		try {
			redisProperties = loadRedisProperties("redis");
			providerProperties = loadRedisProperties("provider");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	
	private static Properties loadRedisProperties(String app) throws Exception {
		try {
			manager = new ConfigurationManager(app);
		} catch (Exception e) {
			throw e;
		}
		return manager.getProperties();
	}
	
	public static String getReidsHostAndPort() {
		return redisProperties.get("zhangpai-cache-redis").toString();
	}
	
	public static int one_hour() {
		return Integer.valueOf(redisProperties.get("one_hour").toString());
	}
	
	public static int two_hour() {
		return Integer.valueOf(redisProperties.get("two_hour").toString());
	}
	
	public static int nine_hour() {
		return Integer.valueOf(redisProperties.get("nine_hour").toString());
	}
	
	/**
	 * 远程请求接口地址
	 *
	 * @author narisu
	 * @created 2014年1月14日 下午3:58:25
	 *
	 * @return
	 */
	public static String getRemoteWebsiteUrl() {
		return providerProperties.getProperty("remoting_website_url");
	}
	
	/**
	 * 获取远程添加收藏方法
	 *
	 * @author Huangyunjun
	 * @created 2016年07月16日 下午4:03:06
	 *
	 * @return
	 */
	public static String getFavoritesMethodAdd() {
		return providerProperties.getProperty("remoting_website_add_favorites_method");
	}
	
	/**
	 * 获取远程删除收藏方法
	 *
	 * @author Huangyunjun
	 * @created 2016年6月15日 下午4:03:06
	 *
	 * @return
	 */
	public static String getFavoritesMethodDelete() {
		return providerProperties.getProperty("remoting_website_delete_favorites_method");
	}
	
	public static int getAndriodMessageType() {
		String messageTypeString = providerProperties.getProperty("andriod.message.type","1");
		return Integer.valueOf(messageTypeString);
	}
	
	public static int getAndriodDeviceType() {
		String messageTypeString = providerProperties.getProperty("andriod.device.type","1");
		return Integer.valueOf(messageTypeString);
	}
	
	public static String getAndriodApikey() throws Exception {
		String apikey = providerProperties.getProperty("andriod.apikey");
		if(StringUtil.isEmpty(apikey)) {
			throw new Exception("andriod apikey不能为空。");
		}
		return apikey;
	}
	
	public static String getAndriodSecretkey() throws Exception {
		String secretkey = providerProperties.getProperty("andriod.secretkey");
		if(StringUtil.isEmpty(secretkey)) {
			throw new Exception("andriod secretkey不能为空。");
		}
		return secretkey;		
	}
	
	public static int getIOSMessageType() {
		String messageTypeString = providerProperties.getProperty("ios.message.type","1");
		return Integer.valueOf(messageTypeString);
	}
	
	public static int getIOSDeviceType() {
		String messageTypeString = providerProperties.getProperty("ios.device.type","1");
		return Integer.valueOf(messageTypeString);
	}
	
	public static String getIOSApikey() throws Exception {
		String apikey = providerProperties.getProperty("ios.apikey");
		if(StringUtil.isEmpty(apikey)) {
			throw new Exception("ios apikey不能为空。");
		}
		return apikey;
	}
	
	public static String getIOSSecretkey() throws Exception {
		String secretkey = providerProperties.getProperty("ios.secretkey");
		if(StringUtil.isEmpty(secretkey)) {
			throw new Exception("ios secretkey不能为空。");
		}
		return secretkey;		
	}
	
	public static int getIOSDeployStatus() {
		String deployStatus = providerProperties.getProperty("ios.deploy.status","2");
		return Integer.valueOf(deployStatus);
	}
}
