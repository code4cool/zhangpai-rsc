/**
 * @FileName: BaseException.java
 * @Package com.rabbit.framework.core.exception
 * 
 * @author Huangyunjun
 * @created 2016年07月18日 下午7:20:40
 * 
 * Copyright 2011-2015 rabbit
 */
package com.shalong.zhangpai.rabbit.framework.core.exception;

/**
 * <p>数据访问异常基类</p>
 * 
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * <PRE>
 * 
 * @author Huangyunjun
 * @since 1.0
 * @version 1.0
 */
public class DataAccessException extends BaseException {

	private static final long serialVersionUID = 3003915267325733689L;
	
	/**
	 * 构造器
	 */
	public DataAccessException() {
		super();
	}

	/**
	 * 构造器
	 * 
	 * @param message	异常详细信息
	 * @param cause	异常原因
	 */
	public DataAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 构造器
	 * 
	 * @param message	异常详细信息
	 */
	public DataAccessException(String message) {
		super(message);
	}

	/**
	 * 构造器
	 * 
	 * @param cause	异常原因
	 */
	public DataAccessException(Throwable cause) {
		super(cause);
	}

}
