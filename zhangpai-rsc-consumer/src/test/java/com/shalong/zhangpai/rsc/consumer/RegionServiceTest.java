package com.shalong.zhangpai.rsc.consumer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.dubbo.common.json.JSON;
import com.shalong.zhangpai.rabbit.framework.cache.RedisCacheClient;
import com.shalong.zhangpai.rsc.model.region.CountyModel;

/**
 * @FileName: RegionServiceTest.java
 * @Package com.shalong.zhangpai.rsc.consumer
 * 
 * @author Huangyunjun
 * @created 2016年7月20日 上午7:44:50
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

public class RegionServiceTest {
	public static void main(String []args) throws IOException{
		RedisCacheClient jedis = new RedisCacheClient("127.0.0.1:6379", "redis_app");
		List<CountyModel> countyOutputs = new  ArrayList<CountyModel>();
		CountyModel output1 = new CountyModel();
		output1.setCityId(1000);
		output1.setName("北京通州");
		CountyModel output2 = new CountyModel();
		output2.setCityId(2000);
		output2.setName("北京");;
		countyOutputs.add(output2);
		countyOutputs.add(output1);
		String json = JSON.json(countyOutputs);
		System.out.println(json);
		jedis.set("zhang", json);
		System.out.println(jedis.get("zhang"));
	}
}
