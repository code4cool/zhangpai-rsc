package com.shalong.zhangpai.rabbit.framework.cache;

/**
 * 缓存客户端的基类
 * 
 * @author Huangyunjun
 */
public abstract class CacheClientBase {

	//private String prefix;
	public CacheClientBase(String prefix) {
		//this.prefix = prefix;
	}
	
	/**
	 * 提供缓存的解析
	 * @param key
	 * @return
	 */
	protected String resolveKey(String key) {
		// TODO:为了解决在不同服务之间访问缓存，暂时取消使用app名称作为前缀
		//return prefix + "_" + key;
		return key;
	}
}
