package com.shalong.zhangpai.rsc.timer.handler.task;

import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.shalong.zhangpai.rabbit.framework.base.model.Combo2;
import com.shalong.zhangpai.rabbit.framework.core.util.Action;
import com.shalong.zhangpai.rabbit.framework.core.util.LazyQueue;
import com.shalong.zhangpai.rsc.timer.Main;
import com.shalong.zhangpai.rsc.timer.config.RabbitTimerConfig;
import com.shalong.zhangpai.rsc.timer.dao.RabbitTimerDao;
import com.shalong.zhangpai.rsc.timer.handler.SimpleHandler;
import com.shalong.zhangpai.rsc.timer.message.RabbitReport;

/**
 * @FileName: OneTimeHandler.java
 * @Package com.shalong.zhangpai.rsc.timer.handler.task
 * 
 * @author Huangyunjun
 * @created 2016年7月30日 下午8:52:26
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

public class OneTimeHandler extends TimerTask implements SimpleHandler {

	static Logger log = Logger.getLogger(OneTimeHandler.class);
	static RabbitTimerDao dao = (RabbitTimerDao) Main.context.getBean("rabbitTimerDao");
	ExecutorService service = Executors.newFixedThreadPool(10);
	static LazyQueue<RabbitReport> lazy = new LazyQueue<RabbitReport>(
			"DefaultDoctorHandler", 20, 20, new Action<List<RabbitReport>>() {

				public void run(List<RabbitReport> list) {
					if (list != null && !list.isEmpty()) {
						for (RabbitReport report : list) {
							log.info(String.format("RabbitReport."));
						}
					}
				}

			});
	
	static LazyQueue<Combo2<String,Integer>> updateDuttyId = new LazyQueue<Combo2<String,Integer>>(
			"updateDuttyId", 20, 20, new Action<List<Combo2<String,Integer>>>() {

				public void run(List<Combo2<String,Integer>> list) {
					if (list != null && !list.isEmpty()) {
						for (Combo2<String,Integer> combo2 : list) {
							try {
								//dao.updateDurryId(combo2.getV1(),combo2.getV2());
							} catch (Exception e) {
								log.error(e.getMessage(), e);
							}
						}
					}
				}

			});
	 
	public boolean before() throws Exception {
		// TODO Auto-generated method stub
		//RabbitLock.getCountLock().await();
		return RabbitTimerConfig.isOneTimeHandlerExecute();
	}

	public boolean working() throws Exception {
		// TODO Auto-generated method stub
		log.debug("One Time Handler working.");
		return false;
	}

	public boolean release() {
		// TODO Auto-generated method stub
		log.debug("One Time Handler release.");
		return false;
	}

	public boolean after() {
		// TODO Auto-generated method stub
		log.debug("One Time Handler after.");
		try {
			RabbitTimerConfig.setOneTime(1);
			return true;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			return false;
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		log.debug("One Time Handler run.");
		try {
			boolean isExecute = before();
			// 等待信息加载完毕。
			if(isExecute) {
				log.debug("One Time Handler Is Executed.");
			} else {
				working();
				after();
			}


		} catch (Exception e) {
			log.error("线程错误。", e);
		}
	}

	 
}