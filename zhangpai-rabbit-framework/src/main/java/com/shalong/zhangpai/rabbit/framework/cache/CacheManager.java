package com.shalong.zhangpai.rabbit.framework.cache;

/**
 * 缓存客户端工厂
 * 
 * @author Huangyunjun
 */
public class CacheManager {
	
	private static CacheClient client;
	
	/**
	 * 初始化缓存客户端
	 * @param app
	 */
	public static void setup(String servers, String app) {
		client = new RedisCacheClient(servers, app);
	}
	
	/**
	 * 清理缓存客户端
	 */
	public static void cleanup() {
		client.cleanup();
	}
	
	/**
	 * 获取客户端实例
	 * @return
	 */
	public static CacheClient getClient() {
		return client;
	}
	/*获取指定配置的CacheClient*/
	public static CacheClient getClient(String servers , String app) {
	    return new RedisCacheClient(servers, app);
	}
}
