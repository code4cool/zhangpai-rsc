package com.shalong.zhangpai.rsc.provider.order;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shalong.zhangpai.rsc.service.order.OrderService;

/**
 * @FileName: OrderServiceTest.java
 * @Package com.shalong.zhangpai.rsc.provider.order
 * 
 * @author Huangyunjun
 * @created 2016年7月21日 下午7:50:51
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:./META-INF/spring/application-context.xml"})
public class OrderServiceTest {
	@Autowired
	OrderService orderService;
	
	@Test
	public void cancelOrder() {
		String paramString = "{'buyerId':'100010','orderNumber':'10001020898989989100010'}";
	    System.out.println(orderService.cancelOrder(paramString));
	}
	@Test
	public void searchMyOrderList(){
		
		String paramString = "{'buyerId':'100010','status':'0','pageSize':'10','page':'1'}";
		System.out.println(orderService.searchMyOrderList(paramString));
		
	}
}
