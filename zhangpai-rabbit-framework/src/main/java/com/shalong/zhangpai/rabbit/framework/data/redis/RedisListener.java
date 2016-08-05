package com.shalong.zhangpai.rabbit.framework.data.redis;

import redis.clients.jedis.JedisPubSub;

/**
 * @FileName: RedisListener.java
 * @Package com.shalong.zhangpai.rabbit.framework.data.redis
 * 
 * @author Huangyunjun
 * @created 2016年7月31日 下午2:22:39
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

public class RedisListener extends JedisPubSub{
	// 取得订阅的消息后的处理
		@Override
		public void onMessage(String channel, String message) {
			System.out.println(channel + "=" + message);
		}

		// 初始化订阅时候的处理
		@Override
		public void onSubscribe(String channel, int subscribedChannels) {
			 System.out.println(channel + "=" + subscribedChannels);
		}

		// 取消订阅时候的处理
		@Override
		public void onUnsubscribe(String channel, int subscribedChannels) {
		     System.out.println(channel + "=" + subscribedChannels);
		}

		// 初始化按表达式的方式订阅时候的处理
		@Override
		public void onPSubscribe(String pattern, int subscribedChannels) {
			 System.out.println(pattern + "=" + subscribedChannels);
		}

		// 取消按表达式的方式订阅时候的处理
		@Override
		public void onPUnsubscribe(String pattern, int subscribedChannels) {
			 System.out.println(pattern + "=" + subscribedChannels);
		}

		// 取得按表达式的方式订阅的消息后的处理
		@Override
		public void onPMessage(String pattern, String channel, String message) {
			System.out.println(pattern + "=" + channel + "=" + message);
		}
	}

