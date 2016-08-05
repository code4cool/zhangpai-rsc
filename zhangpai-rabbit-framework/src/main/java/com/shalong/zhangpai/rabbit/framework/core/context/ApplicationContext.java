/**
 * @FileName: ApplicationContext.java
 * @Package com.rabbit.framework.core.context
 * 
 * @author Huangyunjun
 * @created 2016年07月18日  上午8:22:03
 * 
 * Copyright 2011-2015 rabbit
 */
package com.shalong.zhangpai.rabbit.framework.core.context;

import com.alibaba.dubbo.common.utils.ConfigUtils;

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
public class ApplicationContext {
	
	/**
	 * 
	 * 获取当前应用名称
	 *
	 * @author Huangyunjun
	 * @created 2016年07月16日 下午12:30:10
	 *
	 * @return	应用名称
	 */
	public static String getApplicationName() {
		return ConfigUtils.getProperty("dubbo.application.name");
	}
	
	/**
	 * 
	 * 获取注册中心地址
	 *
	 * @author Huangyunjun
	 * @created 2016年07月18日  下午12:32:38
	 *
	 * @return
	 */
	public static String getRegistryAddress() {
		return ConfigUtils.getProperty("dubbo.registry.address");
	}
	
	/**
	 * 
	 * 当前应用是否为服务提供者
	 *
	 * @author Huangyunjun
	 * @created 2016年07月18日 下午5:57:34
	 *
	 * @return
	 */
	public static boolean isProviderSide() {
		return getApplicationName().endsWith("provider");
	}
	
	/**
	 * 
	 * 当前应用是否为服务消费者
	 *
	 * @author Huangyunjun
	 * @created 2016年07月18日 下午5:57:51
	 *
	 * @return
	 */
	public static boolean isConsumerSide() {
		return getApplicationName().endsWith("consumer");
	}

}
