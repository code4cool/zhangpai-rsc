package com.shalong.zhangpai.rsc.timer.handler.task.helper;
/**
 * @FileName: MessageListener.java
 * @Package com.shalong.zhangpai.rsc.timer.handler.task.helper
 * 
 * @author Huangyunjun
 * @created 2016年7月31日 上午10:17:20
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

import java.util.EventListener;

import com.shalong.zhangpai.rsc.timer.message.PunctualEvent;

public interface PunctualListener extends EventListener {
	
	public void message(PunctualEvent e) throws Exception;
	
}
