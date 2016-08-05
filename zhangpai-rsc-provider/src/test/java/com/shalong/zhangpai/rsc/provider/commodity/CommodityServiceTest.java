package com.shalong.zhangpai.rsc.provider.commodity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shalong.zhangpai.rsc.service.commodity.CommodityService;

/**
 * @FileName: CommodityServiceTest.java
 * @Package com.shalong.zhangpai.rsc.provider.commodity
 * 
 * @author Huangyunjun
 * @created 2016年7月23日 下午3:24:20
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:./META-INF/spring/application-context.xml"})
public class CommodityServiceTest {
	
	@Autowired
	CommodityService commodityService;
	
	@Test
	public void searchCommodityList(){
		String paramString = "{'ownerId':'32','classify':'0','pageSize':'10','page':'1'}";
		System.out.println(commodityService.searchCommodityList(paramString));
	}
	
	@Test
	public void getCommodityDetail(){
		String paramString = "{'ownerId':'0','commId':'7'}";
		System.out.println(commodityService.getCommodityDetail(paramString));
	}

}
