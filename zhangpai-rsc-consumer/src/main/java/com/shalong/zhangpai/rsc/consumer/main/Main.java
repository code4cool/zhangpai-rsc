package com.shalong.zhangpai.rsc.consumer.main;

/**
 * @FileName: Main.java
 * @Package com.shalong.zhangpai.rsc.consumer.main
 * 
 * @author Huangyunjun
 * @created 2016年7月17日 上午9:34:10
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

import com.alibaba.dubbo.container.log4j.Log4jContainer;
import com.alibaba.dubbo.container.spring.SpringContainer;

public class Main {
	
	public static void main(String []args){
		
		// 启动日志
		Log4jContainer log4jContainer = new Log4jContainer();
		log4jContainer.start();		
		
		// 启动spring
		SpringContainer springContainer = new SpringContainer();
		springContainer.start();
		
		// 启动thrift servers
		Server.init();
		
	}

}