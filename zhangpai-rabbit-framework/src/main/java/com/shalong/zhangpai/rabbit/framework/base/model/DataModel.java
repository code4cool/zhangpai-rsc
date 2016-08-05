package com.shalong.zhangpai.rabbit.framework.base.model;
/**
 * @FileName: DataModel.java
 * @Package com.shalong.zhangpai.rabbit.framework.base.model
 * 
 * @author Huangyunjun
 * @created 2016年7月16日 下午2:23:03
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.dubbo.common.json.JSON;
import com.shalong.zhangpai.rabbit.framework.core.exception.BusinessException;

/**
 * <p>TODO</p>
 * 
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 * 
 * @author Huangyunjun
 * @since 1.0
 * @version 1.0
 */
public class DataModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5551488000425413906L;
	
	/**
	 * 编码
	 */
	private int code = 0;
	
	/**
	 * 消息（错误消息）
	 */
	private String msg = "DataModel对象没有值";
	
	/**
	 * 成功消息数据
	 */
	private Map<String, Object> data = new HashMap<String, Object>();
	

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return the data
	 */
	public Map<String, Object> getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	/**
	 * 
	 * 向data中写入数据
	 *
	 * @author Huangyunjun
	 * @created 2016年07月16日 下午2:09:33
	 *
	 * @param key
	 * @param value
	 */
	public void putValue(String key, Object value) {
		data.put(key, value);
	}
	
	/**
	 * 
	 * 返回该对象json
	 *
	 * @author Huangyunjun
	 * @created 2016年07月16日 下午5:40:19
	 *
	 * @return
	 * @throws BusinessException
	 */
	public String json() throws BusinessException{
		String json = null;
        try {
			json = JSON.json(this);
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException("对象转换成json串时出现异常。", e);
		}
		return json;
	}

}
