package com.shalong.zhangpai.rsc.consumer.main;

import com.alibaba.dubbo.container.spring.SpringContainer;
import com.shalong.zhangpai.rsc.service.region.RegionService;
import com.shalong.zhangpai.rsc.service.user.UserService;
 
 

/**
 * @FileName: SpringContext.java
 * @Package com.shalong.zhangpai.rsc.consumer.main
 * 
 * @author Huangyunjun
 * @created 2016年7月17日 上午11:56:22
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

public class SpringContext {
	
	/**
	 * 
	 * 获取需要的userservice对象，供handler调用
	 *
	 * @author Huangyunjun
	 * @created 2016年07月20日 上午9:29:03
	 *
	 * @return
	 */
	public static UserService getUserService(){
		return UserService.class.cast(SpringContainer.getContext().getBean("userService"));
	}
	
	
	/**
	 * 
	 * 获取区域服务对象
	 *
	 * @author Huangyunjun
	 * @created 2016年07月20日 下午12:10:11
	 *
	 * @return
	 */
	public static RegionService getRegionService(){
		return RegionService.class.cast(SpringContainer.getContext().getBean("regionService"));
	}
	
	/**
	 * 
	 * 获取服务对象
	 *
	 * @author Huangyunjun
	 * @created 2016年07月22日 下午12:10:11
	 *
	 * @param serviceName 服务名字
	 * @return
	 */
	public static Object getServiceObject(String serviceName){
		return SpringContainer.getContext().getBean(serviceName);
	}
	

}
