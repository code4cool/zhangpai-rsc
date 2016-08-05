package com.shalong.zhangpai.rsc.scheduler;

import org.junit.Test;

import com.shalong.zhangpai.rabbit.framework.cache.JedisCacheClient;
import com.shalong.zhangpai.rabbit.framework.core.config.GlobalConfig;
import com.shalong.zhangpai.rsc.scheduler.handler.task.helper.RedisMsgPubSubListener;

/**
 * @FileName: TestSubscribe.java
 * @Package com.shalong.zhangpai.rsc.scheduler
 * 
 * @author Huangyunjun
 * @created 2016年8月1日 下午1:51:59
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

public class TestSubscribe {
	 /**
	    * reids客户端
	    */
		private JedisCacheClient jedis = new JedisCacheClient(GlobalConfig.getReidsHostAndPort(),"doctor");
		
		
    @Test
    public void testSubscribe() throws Exception{
        
        RedisMsgPubSubListener listener = new RedisMsgPubSubListener();
        while(true) {
        	jedis.subscribe(listener, "zhangpai");
        	jedis.subscribe(listener, "zhangpaiMsg");
        }
        
        
    }
}
