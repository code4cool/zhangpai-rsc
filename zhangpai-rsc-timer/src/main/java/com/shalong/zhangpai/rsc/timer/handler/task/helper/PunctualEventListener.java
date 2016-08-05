package com.shalong.zhangpai.rsc.timer.handler.task.helper;

import org.apache.log4j.Logger;

import com.shalong.zhangpai.rsc.timer.message.PunctualEvent;
import com.shalong.zhangpai.rsc.timer.utils.RabbitCalendarUtils;

/**
 * @FileName: MessageEventListener.java
 * @Package com.shalong.zhangpai.rsc.timer.handler.task.helper
 * 
 * @author Huangyunjun
 * @created 2016年7月31日 上午10:22:13
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

public class PunctualEventListener implements PunctualListener{
	
	static Logger log = Logger.getLogger(PunctualEventListener.class);
	
	public void message(PunctualEvent e) throws Exception {
		// TODO Auto-generated method stub
		try {
		//正点运行
		String startTime = RabbitCalendarUtils.getCurrentStartTime();
		String endTime = RabbitCalendarUtils.getNextDayEnd();
		
		log.info("startTime："+startTime+"=====endTime:"+endTime);
		
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw ex;
		} finally {
 		 
		}
		
	}

}
