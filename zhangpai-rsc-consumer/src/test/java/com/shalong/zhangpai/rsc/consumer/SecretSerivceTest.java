package com.shalong.zhangpai.rsc.consumer;

import java.util.Map;

import com.shalong.zhangpai.rabbit.framework.core.util.JsonUtils;
import com.shalong.zhangpai.rsc.consumer.handler.SigningSecretSerivce;

/**
 * @FileName: SecretSerivceTest.java
 * @Package com.shalong.zhangpai.rsc.consumer
 * 
 * @author Huangyunjun
 * @created 2016年7月24日 下午5:12:09
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

public class SecretSerivceTest {
@SuppressWarnings({ "unchecked", "rawtypes" })
public static void main(String []args){
	    String secret = "ebc81743771381db341a180e22edb8e1";
	   
	    String secret_array = "{'appkey':'zhangpaidaf4a81a3fd8e12d','sign':'1FAE1EB496D7465E27E7BD8707746CDB','timestamp':'201607242035'}";
	    Map<String, Object> secretMap = JsonUtils.json2Object(secret_array, Map.class);
	    String appkey = secretMap.get("appkey").toString();
	    String sign = secretMap.get("sign").toString();
	    String timestamp = secretMap.get("timestamp").toString();
	    
	    String jsonArgs = "{'buyerId':'100010','status':'0','pageSize':'10','page':'1'}";
	    
	 
	    System.out.println("Secret:"+SigningSecretSerivce.Secret(sign, jsonArgs));
	     
        System.out.println("diffTimestamp:" + SigningSecretSerivce.diffTimestamp(timestamp));
        
		//System.out.println("=appkey==="+appkey+"====sign=="+sign+"===="+timestamp+"==JsonKeyValues="+JsonKeyValues);
	    //System.out.println("sign:"+string2MD5(string2MD5(JsonKeyValues)).toUpperCase());
	}
}
	
