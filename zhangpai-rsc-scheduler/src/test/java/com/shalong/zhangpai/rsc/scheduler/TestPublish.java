package com.shalong.zhangpai.rsc.scheduler;

import org.junit.Test;

import com.shalong.zhangpai.rabbit.framework.cache.JedisCacheClient;
import com.shalong.zhangpai.rabbit.framework.core.config.GlobalConfig;

/**
 * @FileName: TestPublish.java
 * @Package com.shalong.zhangpai.rsc.scheduler
 * 
 * @author Huangyunjun
 * @created 2016年8月1日 下午1:55:04
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

public class TestPublish {
	
	 /**
	    * reids客户端
	    */
		private JedisCacheClient jedis = new JedisCacheClient(GlobalConfig.getReidsHostAndPort(),"doctor");
		
		
    @Test
    public void testPublish() throws Exception{
        
        while(true) {
			//发布消息
        	 int rand =  (int)(1+Math.random()*(1000000-1+1));
			jedis.publish("zhangpai", "zhangpaiV3:"+rand);
			
			try {
				Thread.currentThread().sleep(2000);
				jedis.publish("zhangpaiMsg", "zhangpaiMsg:"+rand);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }
}
