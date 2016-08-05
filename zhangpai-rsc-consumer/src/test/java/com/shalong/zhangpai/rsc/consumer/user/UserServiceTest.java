package com.shalong.zhangpai.rsc.consumer.user;


/**
 * @FileName: TestUser.java
 * @Package com.shalong.zhangpai.rsc.consumer.user
 * 
 * @author Huangyunjun
 * @created 2016年7月16日 下午8:00:53
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.shalong.zhangpai.rabbit.framework.core.util.JsonUtils;
import com.shalong.zhangpai.rsc.service.user.UserService;

 
public class UserServiceTest { 
    private static ClassPathXmlApplicationContext context = null;
    
    static {
		context = new ClassPathXmlApplicationContext(new String[] {"./META-INF/spring/dubbo-zhangpai-consumer.xml"});
	    context.start();
	}
    
	  
	  
	public static void main(String []args){
		UserService userService = (UserService)context.getBean("userService");
		
		String paramString = "{\"userName\":\"huangyunjun\"}"; 
		Map<String,String> paramMap = JsonUtils.json2Object(paramString, Map.class);
		System.out.println(paramMap);
		
		String username = paramMap.get("userName");
		System.out.println(username);
		
		System.out.println(userService.getUser(paramString));
	}
}
