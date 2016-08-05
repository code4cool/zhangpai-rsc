package com.shalong.zhangpai.rsc.provider.advert.collection;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shalong.zhangpai.rabbit.framework.cache.RedisCacheClient;
import com.shalong.zhangpai.rabbit.framework.core.Transcoder;
import com.shalong.zhangpai.rabbit.framework.core.config.GlobalConfig;
import com.shalong.zhangpai.rsc.provider.advert.dao.AdvertisementDAO;
import com.shalong.zhangpai.rsc.thrift.advertisement.Advertisement;
import com.shalong.zhangpai.rsc.thrift.consts.RedisKeyConstants;

import redis.clients.util.SafeEncoder;

/**
 * @FileName: AdvertisementDataCollection.java
 * @Package com.shalong.zhangpai.rsc.provider.advert.collection
 * 
 * @author Huangyunjun
 * @created 2016年7月20日 下午3:02:50
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

@Component("advertisementDataCollection")
public class AdvertisementDataCollection {
	
	 /**
     * reids客户端
     */
	private RedisCacheClient jedis = new RedisCacheClient(GlobalConfig.getReidsHostAndPort(),"doctor");
	
	@Autowired
	private AdvertisementDAO advertisementDAO;
	
	
	public AdvertisementDataCollection() {
			
	}
	
public Advertisement getAdvertisement(int advertId) throws Exception {
		
	    Advertisement advertisement = null;
	    String key = RedisKeyConstants.ADVERT_INFO_PREFIX + advertId;
		byte[] bkey = Transcoder.encodeString(key);
		byte[] bvalue = jedis.get(bkey);
		
		if(Arrays.equals(bvalue, RedisKeyConstants.NULL_VALUE.getBytes())) {
			return null;
		} else if(bvalue != null) {
			advertisement = Transcoder.decodeObject(bvalue, Advertisement.class);
			if(advertisement == null) {
				jedis.del(bkey);
				advertisement = getAdvertisement(advertId);
			}
		} else {
		 
			advertisement = advertisementDAO.getAdvertisementInfo(advertId, advertisement);
				 
			if(advertisement != null) {
					bvalue = Transcoder.encodeObject(advertisement);
					jedis.setex(bkey, GlobalConfig.one_hour(),bvalue);
				 
			} else {
				jedis.setex(bkey, GlobalConfig.one_hour(),RedisKeyConstants.NULL_VALUE.getBytes());
				advertisement = null;
			}
		}
		 
		return advertisement;
	}
	
	/**
	 * 获取全部的累计广告
	 *
	 * @author Huangyunjun
	 * @created 2016年07月20日 下午12:16:40
	 *
	 * @return
	 * @throws Exception 
	 */
	public int getAdvertisementCount() throws Exception {
		
		byte[] bkey = SafeEncoder.encode(RedisKeyConstants.ADVERT_TOTAL_COUNT);
		byte[] bvalue = jedis.get(bkey);
		
		int totalCount = 0;
		if(bvalue == null) {
			totalCount = advertisementDAO.queryAdvertCount();
			jedis.setex(bkey, GlobalConfig.one_hour(),SafeEncoder.encode(String.valueOf(totalCount)));
		} else {
			String stotalCount = SafeEncoder.encode(bvalue);
			totalCount = Integer.valueOf(stotalCount);
		}
		return totalCount;
	}
 
	

}
