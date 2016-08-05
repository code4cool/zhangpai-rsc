package com.shalong.zhangpai.rsc.timer.handler.task;

import java.util.Map.Entry;
import java.util.Set;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.shalong.zhangpai.rsc.model.message.Message;
import com.shalong.zhangpai.rsc.timer.cache.LocalCache;
import com.shalong.zhangpai.rsc.timer.handler.task.helper.PunctualEventListener;
import com.shalong.zhangpai.rsc.timer.message.PunctualEvent;

/**
 * @FileName: MessageListenerHandler.java
 * @Package com.shalong.zhangpai.rsc.timer.handler.task
 * 
 * @author Huangyunjun
 * @created 2016年7月31日 上午10:26:00
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

public class PunctualListenerHandler extends TimerTask {
	
	static Logger log = Logger.getLogger(PunctualListenerHandler.class);
	ExecutorService service = Executors.newFixedThreadPool(10);
	 
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Set<Entry<String, Message>> set = LocalCache.messages.entrySet();
			CountDownLatch latch = new CountDownLatch(set.size());
			for (Entry<String, Message> e : set) {
				service.execute(new SimpleThread(latch, e.getValue()));
			}
			log.info("MessageListenerHandler is running。");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
	}


	static class SimpleThread implements Runnable {

		CountDownLatch latch;
		Message message;

		public SimpleThread(CountDownLatch latch,Message message) {
			this.latch = latch;
			this.message = message;
		}

		public void run() {
			try {
				
				PunctualEventListener listener = new PunctualEventListener();
				PunctualEvent e = new PunctualEvent(message);
				listener.message(e);
				
			} catch (Exception e) {
				// error log 处理错误需要落地，等待下次轮训
				log.error(e.getMessage(), e);
			} finally {
				latch.countDown();
			}
		}
	}

	
}
