package com.shalong.zhangpai.rsc.timer.handler;
/**
 * @FileName: SimpleHandler.java
 * @Package com.shalong.zhangpai.rsc.timer.handler
 * 
 * @author Huangyunjun
 * @created 2016年7月30日 下午8:32:45
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

public interface SimpleHandler {

	public boolean before() throws Exception;
	
	public boolean working() throws Exception;
	
	public boolean release();
	
	public boolean after();
}

