package com.shalong.zhangpai.rsc.timer.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.shalong.zhangpai.rsc.model.message.Message;

/**
 * @FileName: LocalCache.java
 * @Package com.shalong.zhangpai.rsc.timer.cache
 * 
 * @author Huangyunjun
 * @created 2016年7月30日 下午8:57:53
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

public class LocalCache {

	public static Map<String,Message> messages = new HashMap<String,Message>(100);
	/**
	 * 等待处理队列
	 */
	public static Vector<String> waitingRevision = new Vector<String>();
	
	
	 
	
}