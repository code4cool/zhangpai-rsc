package com.shalong.zhangpai.rsc.scheduler;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.shalong.zhangpai.rabbit.framework.cache.JedisCacheClient;
import com.shalong.zhangpai.rabbit.framework.core.config.GlobalConfig;
import com.shalong.zhangpai.rsc.scheduler.handler.task.helper.RedisMsgPubSubListener;

 

public class Main {
	static Logger logger = Logger.getLogger(Main.class);	
 
    public static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:./META-INF/spring/applicationContext.xml");
    /**
	 * reids客户端
	 */
	private static JedisCacheClient jedis = new JedisCacheClient(GlobalConfig.getReidsHostAndPort(),"doctor");
		
    
    public static void main(String[] args) throws Exception {
 
    	 RedisMsgPubSubListener listener = new RedisMsgPubSubListener();
         while(true) {
        	 jedis.subscribe(listener, "zhangpai");
         }
         
    	 
       
    }
}
