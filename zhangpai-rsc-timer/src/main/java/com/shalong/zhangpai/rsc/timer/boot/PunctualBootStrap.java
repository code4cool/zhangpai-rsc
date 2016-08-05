package com.shalong.zhangpai.rsc.timer.boot;



/**
 * @FileName: MessageBootStrap.java
 * @Package com.shalong.zhangpai.rsc.timer.boot
 * 
 * @author Huangyunjun
 * @created 2016年7月31日 上午10:44:52
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.shalong.zhangpai.rsc.timer.config.RabbitTimerConfig;
import com.shalong.zhangpai.rsc.timer.utils.RabbitCalendarUtils;

public class PunctualBootStrap extends BootStrap {
	
	Logger log = Logger.getLogger(PunctualBootStrap.class);
	
	public PunctualBootStrap(TimerTask handler) {
		super(handler);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
			String timerName = handler.getClass().getName();
			if(log.isDebugEnabled()) {
				log.debug(String.format("%s 将要启动。",timerName));
			}
			
			try {
				Timer timer = new Timer();

				Date nextHour = RabbitCalendarUtils.nextPunctualHour();
				
				long period = RabbitTimerConfig.messagePeriod();
				timer.scheduleAtFixedRate(handler, nextHour, period);
				log.info(String.format("%s 线程将在 %s 时启动.间隔为 %d ms.",timerName,RabbitCalendarUtils.date2string(nextHour),period));
				
			} catch (Exception e) {
				log.error(e.getMessage(),e);
			}
		}

	}

