package com.shalong.zhangpai.rsc.timer.boot;

import java.util.TimerTask;

/**
 * @FileName: BootStrap.java
 * @Package com.shalong.zhangpai.rsc.timer.boot
 * 
 * @author Huangyunjun
 * @created 2016年7月30日 下午8:26:55
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

public abstract class BootStrap {

	TimerTask handler;
	
	public BootStrap(TimerTask handler) {
		this.handler = handler;
	}
	
	public abstract void execute();
}

