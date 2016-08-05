package com.shalong.zhangpai.rabbit.framework.core.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

import com.shalong.zhangpai.rabbit.framework.core.util.NumberUtils;
import com.shalong.zhangpai.rabbit.framework.core.util.StringUtil;


public class ConfigurationManager {

	private Properties properties;
	private FileOutputStream outputFile;
	private final static String TAG = ConfigurationManager.class.getSimpleName();
	String configPath = ""; // 配置文件路径
	
	/**
	 * 有参构造方法
	 * @param app
	 * @throws Exception
	 */
	public ConfigurationManager(String app) throws Exception {
		
		String appFile = app + ".properties";
		String enviPath = System.getenv("RABBIT_CONFIG_PATH"); // 获取环境变量配置的信息
		if (StringUtil.isEmpty(enviPath)) {
			configPath = System.getProperty("user.dir") + File.separator + appFile;
		} else {
			configPath = enviPath + File.separator + appFile;
		}
		properties = new Properties();
		if (new File(configPath).exists()) {
			FileInputStream inputStream = new FileInputStream(configPath);
			properties.load(inputStream);
		} else {
			InputStream inputStream = ConfigurationManager.class.getClassLoader().getResourceAsStream(appFile);
			properties.load(inputStream);
		}
	}
	
	public Properties getProperties() {
		return properties;
	}

	public void clearProperties() {
		properties.clear();
	}


	/**
	 * 
	 * 根据配置文件名获取该文件管理对象
	 *
	 * @author Huangyunjun
	 * @created 2016年07月16日 上午10:42:46
	 *
	 * @param app
	 * @return
	 * @throws Exception
	 */
	public static ConfigurationManager getManager(String app) throws Exception {
		return new ConfigurationManager(app);
	}

	/**
	 * 
	 * 根据属性名获取属性值
	 *
	 * @author Huangyunjun
	 * @created 2016年07月16日 上午10:40:37
	 *
	 * @param key
	 * @return
	 */
	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	/**
	 * 
	 * 设置属性值
	 *
	 * @author Huangyunjun
	 * @created 2016年07月16日 上午10:41:03
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public Object setProperty(String key, String value) {
		return properties.setProperty(key, value);
	}

	/**
	 * 
	 * 根据属性名获取int类型的属性值
	 *
	 * @author Huangyunjun
	 * @created 2016年07月16日 上午10:41:14
	 *
	 * @param key
	 * @return
	 */
	public int getInt(String key) {
		String val = properties.getProperty(key);
		if (NumberUtils.isNumber(val)) {
			return Integer.parseInt(val);
		}
		return 0;
	}

	public long getLong(String key) {
		String val = properties.getProperty(key);
		if (NumberUtils.isNumber(val)) {
			return Long.parseLong(val);
		}
		return 0;
	}
	
	public void saveFile(String fileName, String description) throws Exception {
		try {
			String appFile = fileName + ".properties";
			String enviPath = System.getenv("RABBIT_CONFIG_PATH"); // 获取环境变量配置的信息
			if (StringUtil.isEmpty(enviPath)) {
				configPath = System.getProperty("user.dir") + File.separator + appFile;
			} else {
				configPath = enviPath + File.separator + appFile;
			}
			if (new File(configPath).exists()) {
				outputFile = new FileOutputStream(configPath);
				properties.store(outputFile, description);
			} 
		} catch (FileNotFoundException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new Exception(TAG, ex);
		} finally {
			if (outputFile != null) {
				outputFile.close();
			}
		}
	}
	
}
