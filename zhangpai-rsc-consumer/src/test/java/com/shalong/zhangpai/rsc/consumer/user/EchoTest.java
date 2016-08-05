/**
 * @FileName: EchoTest.java
 * @Package com.hyde.rsc
 * 
 * @author Huangyunjun
 * @created 2016年07月15日 下午12:00:49
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */
package com.shalong.zhangpai.rsc.consumer.user;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.dubbo.rpc.service.EchoService;
import com.shalong.zhangpai.rsc.service.user.UserService;

/**
 * <p>回声测试</p>
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
public class EchoTest {
	
    private static ClassPathXmlApplicationContext context = null;
	
	static {
		context = new ClassPathXmlApplicationContext(new String[] {"./META-INF/spring/dubbo-zhangpai-consumer.xml"});
	    context.start();
	}
	
	public static void main(String []args){
		
		UserService userService = (UserService)context.getBean("userService");
		EchoService echoService = (EchoService) userService; // 强制转型为EchoService
		String status = (String) echoService.$echo("OK"); // 回声测试可用性
		System.out.println("===="+status);
		
	}

}
