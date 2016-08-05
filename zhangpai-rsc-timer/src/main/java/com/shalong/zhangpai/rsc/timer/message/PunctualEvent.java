package com.shalong.zhangpai.rsc.timer.message;

import java.util.EventObject;

/**
 * @FileName: MessageEvent.java
 * @Package com.shalong.zhangpai.rsc.timer.message
 * 
 * @author Huangyunjun
 * @created 2016年7月31日 上午10:17:51
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

public class PunctualEvent extends EventObject {

	private static final long serialVersionUID = -89998988988989891L;

	Object obj;
	
	public PunctualEvent(Object source) {
		super(source);
		obj = source;
	}
	public Object getSource() {
		return obj;
	}
	

}
