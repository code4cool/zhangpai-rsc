package com.shalong.zhangpai.rsc.timer.config;

import java.util.List;

import com.shalong.zhangpai.rsc.timer.Main;
import com.shalong.zhangpai.rsc.timer.dao.RabbitTimerDao;
import com.shalong.zhangpai.rsc.timer.message.TimerConfig;

/**
 * @FileName: RabbitTimerConfig.java
 * @Package com.shalong.zhangpai.rsc.timer.config
 * 
 * @author Huangyunjun
 * @created 2016年7月30日 下午8:28:06
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

public class RabbitTimerConfig {

 
	static TimerConfig oneTimeConfig = new TimerConfig();
	static TimerConfig defaultSceneConfig = new TimerConfig();
	static TimerConfig messageConfig = new TimerConfig();
	
	static RabbitTimerDao dao = (RabbitTimerDao) Main.context.getBean("rabbitTimerDao");
	
	/**
	 * 拍场定时器启动延迟秒数
	 * @return
	 */
	public static long defaultSceneDelay() {
		return defaultSceneConfig.timerDelay;
	}
	
	/**
	 * 拍场定时器执行间隔
	 * @return
	 */
	public static long defaultScenePeriod() {
		return defaultSceneConfig.timerPeriod;
	}
	/**
	 * 消息定时器执行间隔
	 * @return
	 */
	public static long messagePeriod() {
		return messageConfig.timerPeriod;
	}
	/**
	 * 初始化线程是否启动
	 * @return
	 */
	public static boolean isOneTimeHandlerExecute() {
		if(oneTimeConfig.isExecute == 1) {
			return true;
		}
		return false;
	}

	public static void setOneTime(int value) throws Exception {
		oneTimeConfig.isExecute = value;
		dao.updateTimerConfig(oneTimeConfig);
	}
	
	public static long oneTimeDelay() {
		return oneTimeConfig.timerDelay;
	}
	
	 

	public static void init() throws Exception {
		List<TimerConfig> list = dao.queryTimerConfig();
		if(list != null) {
			for(TimerConfig config : list) {
				if("OneTime".equals(config.timerTaskName)) {
					oneTimeConfig = config;
				} else if ("SceneTime".equals(config.timerTaskName)) {
					defaultSceneConfig = config;
				}
				 else if ("MessageTime".equals(config.timerTaskName)) {
					 messageConfig = config;
					}
			}
		}
		 
	}
}
