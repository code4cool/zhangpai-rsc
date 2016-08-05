package com.shalong.zhangpai.rsc.timer;

import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.shalong.zhangpai.rsc.timer.boot.BootStrap;
import com.shalong.zhangpai.rsc.timer.boot.OneTimeBootStrap;
import com.shalong.zhangpai.rsc.timer.boot.PunctualBootStrap;
import com.shalong.zhangpai.rsc.timer.boot.SceneBootStrap;
import com.shalong.zhangpai.rsc.timer.config.RabbitTimerConfig;
import com.shalong.zhangpai.rsc.timer.handler.task.DefaultSceneHandler;
import com.shalong.zhangpai.rsc.timer.handler.task.OneTimeHandler;
import com.shalong.zhangpai.rsc.timer.handler.task.PunctualListenerHandler;

/**
 * @FileName: Main.java
 * @Package com.shalong.zhangpai.rsc.timer
 * 
 * @author Huangyunjun
 * @created 2016年7月30日 下午8:34:09
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

public class Main {
	static Logger log = Logger.getLogger(Main.class);
	
public static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:./META-INF/spring/applicationContext.xml");
	
	public static void main(String[] args) {
		try {
			//配置文件读取
			context.start();
			RabbitTimerConfig.init();
			
			/* 初始化线程 */
			TimerTask oneTimeH = new OneTimeHandler();
			BootStrap oneTimeBoot = new OneTimeBootStrap(oneTimeH);
			oneTimeBoot.execute();
			
			/* 拍场线程 */
				
			TimerTask sceneH = new DefaultSceneHandler();
			BootStrap sceneBoot = new SceneBootStrap(sceneH);
			sceneBoot.execute();
			
			/* 正点线程 */
			TimerTask punctualH = new PunctualListenerHandler();
			BootStrap punctualBoot = new PunctualBootStrap(punctualH);
			punctualBoot.execute();
		 
			
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		log.info("zhangpai timer start up!");

	}
	

}
