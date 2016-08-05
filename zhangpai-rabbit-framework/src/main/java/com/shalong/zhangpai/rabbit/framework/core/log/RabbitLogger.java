/**
 * @FileName: SfbestgetLogger().java
 * @Package com.rabbit.framework.core.log
 * 
 * Huangyunjun
 * @created 2016年07月18日 下午8:00:42
 * 
  
 */
package com.shalong.zhangpai.rabbit.framework.core.log;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * <p>
 * 日志输出类
 * </p>
 * 
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>
 * 修改日期			修改人			修改内容
 * 
 * <PRE>
 * 
 * @author Huangyunjun
 * @since 1.0
 * @version 1.0
 */
public class RabbitLogger {

	/**
	 * 单例SfbestLogger
	 */
	private static RabbitLogger bsLogger = new RabbitLogger();

	private static Logger logger;

	private static final String FQCN = RabbitLogger.class.getName();

	/**
	 * 私有构造器，初始化时设置日志适配器为Log4j
	 */
	private RabbitLogger() {
		// LoggerFactory.setLoggerAdapter(new Log4jLoggerAdapter());
	}

	/**
	 * 
	 * 得到SfbestLogger单例对象
	 * 
	 * @author Huangyunjun
	 * @created 2016年07月16日 下午8:05:48
	 * 
	 * @param clazz
	 * @return
	 */
	public static RabbitLogger getInstance(Class<?> clazz) {
		logger = Logger.getLogger(clazz.getName());
		return bsLogger;
	}

	private Logger getLogger() {
		return logger;
	}

	/**
	 * 
	 * debug日志
	 * 
	 * @author Huangyunjun
	 * @created 2016年07日16日 下午8:06:11
	 * 
	 * @param message
	 *            日志详细信息
	 * @param t
	 *            异常
	 */
	public void debug(String message, Throwable t) {
		getLogger().log(FQCN, Level.DEBUG, message, t);
	}

	/**
	 * 
	 * info日志
	 * 
	 * @author Huangyunjun
	 * @created 2016年07日16日 下午1:33:13
	 * 
	 * @param message
	 *            日志详细信息
	 */
	public void info(String message) {
		getLogger().log(FQCN, Level.INFO, message, null);
	}

	/**
	 * 
	 * info日志
	 * 
	 * @author Huangyunjun
	 * @created 2016年07月16日 下午8:06:24
	 * 
	 * @param message
	 *            日志详细信息
	 * @param t
	 *            异常
	 */
	public void info(String message, Throwable t) {
		getLogger().log(FQCN, Level.INFO, message, t);
	}

	/**
	 * 
	 * warn日志
	 * 
	 * @author Huangyunjun
	 * @created 2016年07月16日 下午8:06:32
	 * 
	 * @param message
	 *            日志详细信息
	 * @param t
	 *            异常
	 */
	public void warn(String message, Throwable t) {
		getLogger().log(FQCN, Level.WARN, message, t);
	}

	/**
	 * 
	 * error日志
	 * 
	 * @author Huangyunjun
     * @created 2016年07月18日 下午8:06:40
	 * 
	 * @param message
	 *            日志详细信息
	 * @param t
	 *            异常
	 */
	public void error(String message, Throwable t) {
		getLogger().log(FQCN, Level.ERROR, message, t);
	}
}