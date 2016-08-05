package com.shalong.zhangpai.rabbit.framework.cache;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * @FileName: JedisClusterClient.java
 * @Package com.shalong.zhangpai.rabbit.framework.cache
 * 
 * @author Huangyunjun
 * @created 2016年8月2日 上午8:07:40
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

public class JedisCacheClient implements JedisClient { 
	
	// 默认超时时间
	private static final int DEFAULT_TIMEOUT = 2000;

	// 默认最大活动连接数
	private static final int MAX_ACTIVE = 200;

	// 默认最大空闲连接数
	private static final int MAX_IDLE = 2;
	private Jedis jedis;
	  
	public JedisCacheClient(String servers, String app) {
		String[] hosts = servers.trim().split(":");
		 
 
		
		jedis = new Jedis(hosts[0]);
		 
		   
		  
	  }
	  
		@Override
		public void cleanup() {
			 
		}
	  
		@Override
		public Long publish(final String channel, final String message) {
			Long result = jedis.publish(channel.getBytes(), message.getBytes());
			return result;	
		}

		@Override
		public void subscribe(JedisPubSub jedisPubSub, String... channels) {
			// TODO Auto-generated method stub
			jedis.subscribe(jedisPubSub, channels);
			
		}

		@Override
		public void set(String key, String value) {
			try {
				jedis.set(key, value);
				 
			} catch (Exception e) {
				 
			}
		}

		@Override
		public String get(String key) {
			// TODO Auto-generated method stub
			String result = jedis.get(key);
			return result;
		}

		@Override
		public void set(byte[] key, byte[] value) {
			// TODO Auto-generated method stub
			jedis.set(key, value);
		}

		@Override
		public byte[] get(byte[] key) {
			byte[] result = jedis.get(key);
			 
			return result;
			 
		}
		

}
