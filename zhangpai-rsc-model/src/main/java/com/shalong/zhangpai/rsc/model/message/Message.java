package com.shalong.zhangpai.rsc.model.message;

import com.shalong.zhangpai.rabbit.framework.base.model.BaseModel;

/**
 * @FileName: Message.java
 * @Package com.shalong.zhangpai.rsc.model.message
 * 
 * @author Huangyunjun
 * @created 2016年7月31日 上午7:50:25
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

public class Message extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1125726084058303996L;
	
	 private int id;
	 private String mobile;
	    private String content;
	    public int getId() {
	        return id;
	    }
	    public void setId(int id) {
	        this.id = id;
	    }
	    public String getContent() {
	        return content;
	    }
	    public void setContent(String content) {
	        this.content = content;
	    }
	    
	    public String getMobile() {
	        return mobile;
	    }
	    public void setMobile(String mobile) {
	        this.mobile = mobile;
	    }

}
