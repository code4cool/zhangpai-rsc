package com.shalong.zhangpai.rsc.timer.handler.task;

import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.shalong.zhangpai.rsc.timer.Main;
import com.shalong.zhangpai.rsc.timer.dao.SceneTimerDao;
import com.shalong.zhangpai.rsc.timer.handler.SimpleHandler;

/**
 * @FileName: DefaultSceneHandler.java
 * @Package com.shalong.zhangpai.rsc.timer.handler.task
 * 
 * @author Huangyunjun
 * @created 2016年7月30日 下午10:30:39
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

public class DefaultSceneHandler extends TimerTask implements SimpleHandler {
	
	static Logger log = Logger.getLogger(OneTimeHandler.class);
	static SceneTimerDao dao = (SceneTimerDao) Main.context.getBean("sceneTimerDao");
	ExecutorService service = Executors.newFixedThreadPool(10);
	
	public boolean before() throws Exception {
		// TODO Auto-generated method stub
		log.debug("Scene Handler before.");
		return false;
	}

	public boolean working() throws Exception {
		// TODO Auto-generated method stub
		log.debug("Scene Handler working.");
		dao.setSceneRemindedMessage();
		return false;
	}

	public boolean release() {
		// TODO Auto-generated method stub
		log.debug("Scene Handler release.");
		return false;
	}

	public boolean after() {
		// TODO Auto-generated method stub
		log.debug("Scene Handler after.");
		return false;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		log.debug("Scene Handler run.");
		try {			 
				working();
				after();
			 

		} catch (Exception e) {
			log.error("拍场线程错误。", e);
		}
	}
	

}
