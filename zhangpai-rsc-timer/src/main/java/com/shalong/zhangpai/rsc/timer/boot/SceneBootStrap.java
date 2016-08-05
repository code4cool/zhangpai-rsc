package com.shalong.zhangpai.rsc.timer.boot;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.shalong.zhangpai.rsc.timer.config.RabbitTimerConfig;

/**
 * @FileName: SceneBootStrap.java
 * @Package com.shalong.zhangpai.rsc.timer.boot
 * 
 * @author Huangyunjun
 * @created 2016年7月30日 下午10:25:39
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

public class SceneBootStrap extends BootStrap{
	Logger log = Logger.getLogger(SceneBootStrap.class);
	
	public SceneBootStrap(TimerTask handler) {
		super(handler);
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		String timerName = handler.getClass().getName();
		
		if(log.isDebugEnabled()) {
			log.debug(String.format("%s 将要启动。",timerName));
		}
		try {
			Timer timer = new Timer(timerName);
			long delay = RabbitTimerConfig.defaultSceneDelay();
			long period = RabbitTimerConfig.defaultScenePeriod();
			timer.scheduleAtFixedRate(handler, delay, period);
			
			log.info(String.format("%s 线程将在 %d s 后启动，执行间隔为: %d",timerName,delay,period));
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
	}
}
