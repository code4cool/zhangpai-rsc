package com.shalong.zhangpai.rsc.timer.boot;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.shalong.zhangpai.rsc.timer.config.RabbitTimerConfig;

/**
 * @FileName: OneTimeBootStrap.java
 * @Package com.shalong.zhangpai.rsc.timer.boot
 * 
 * @author Huangyunjun
 * @created 2016年7月30日 下午9:07:24
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

public class OneTimeBootStrap extends BootStrap {

	Logger log = Logger.getLogger(DispatchBootStrap.class);
	
	public OneTimeBootStrap(TimerTask handler) {
		super(handler);
	}

	@Override
	public void execute() {

		String timerName = handler.getClass().getName();
		if(log.isDebugEnabled()) {
			log.debug(String.format("%s 将要启动。",timerName));
		}
		
		try {
			Timer timer = new Timer();
			long delay = RabbitTimerConfig.oneTimeDelay();
			timer.schedule(handler, delay);
			log.info(String.format("%s 线程将在 %d ms 后启动.",timerName,delay));
			
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
	}

}