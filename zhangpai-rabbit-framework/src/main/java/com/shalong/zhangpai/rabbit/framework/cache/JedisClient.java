package com.shalong.zhangpai.rabbit.framework.cache;

import redis.clients.jedis.JedisPubSub;

/**
 * @FileName: JedisClient.java
 * @Package com.shalong.zhangpai.rabbit.framework.cache
 * 
 * @author Huangyunjun
 * @created 2016年8月2日 上午8:18:36
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

public interface JedisClient {
	
	public void set(String key, String value);
	public void set(byte[] key, byte[] value);
	public String get(String key);
	public byte[] get(byte[] key);
	
	public void cleanup();
	public Long publish(final String channel, final String message);
	public void subscribe(final JedisPubSub jedisPubSub, final String... channels);
}
