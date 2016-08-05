/**
 * @FileName: CloseException.java
 * @Package com.rabbit.framework.core.exception
 * 
 * @author Huangyunjun
 * @created 2016年07月18日 下午7:10:42
 * 
 
 */
package com.shalong.zhangpai.rabbit.framework.core.exception;

/**
 * <p>数据库连接关闭异常</p>
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
public class CloseException extends BaseException {

	private static final long serialVersionUID = 3003915267325733689L;
	
	/**
	 * 构造器
	 */
	public CloseException() {
		super();
	}

	/**
	 * 构造器
	 * 
	 * @param message	异常详细信息
	 * @param cause	异常原因
	 */
	public CloseException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 构造器
	 * 
	 * @param message	异常详细信息
	 */
	public CloseException(String message) {
		super(message);
	}

	/**
	 * 构造器
	 * 
	 * @param cause	异常原因
	 */
	public CloseException(Throwable cause) {
		super(cause);
	}

}
