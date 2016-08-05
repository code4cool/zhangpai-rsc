package com.shalong.zhangpai.rsc.provider.region.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.json.JSON;
import com.shalong.zhangpai.rabbit.framework.base.model.Combo2;
import com.shalong.zhangpai.rabbit.framework.cache.RedisCacheClient;
import com.shalong.zhangpai.rabbit.framework.core.config.GlobalConfig;
import com.shalong.zhangpai.rabbit.framework.core.log.RabbitLogger;
import com.shalong.zhangpai.rabbit.framework.core.util.StringUtil;
import com.shalong.zhangpai.rsc.model.region.CountyModel;
import com.shalong.zhangpai.rsc.provider.region.dao.RegionDao;
import com.shalong.zhangpai.rsc.service.region.RegionService;
import com.shalong.zhangpai.rsc.thrift.consts.RedisKeyConstants;
import com.shalong.zhangpai.rsc.thrift.enums.ReturnCode;

/**
 * @FileName: RegionServiceImpl.java
 * @Package com.shalong.zhangpai.rsc.provider.region.impl
 * 
 * @author Huangyunjun
 * @created 2016年7月20日 上午5:46:48
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

@Service("regionService")
public class RegionServiceImpl implements RegionService {
	
	
	private static RabbitLogger LOGGER = RabbitLogger.getInstance(RegionServiceImpl.class);
	
	/**
     * reids客户端
     */
	private RedisCacheClient jedis = new RedisCacheClient(GlobalConfig.getReidsHostAndPort(),"region");
	
	@Autowired
	private RegionDao regionDao;
	


	@Override
	public Combo2<Integer, String> getCountyByCityId(int cityId) {
		List<CountyModel> countyModels = null;
		Combo2<Integer, String> combo2 = null;
		String resCountyJson = jedis.get(RedisKeyConstants.RSCCOUNTY); // 缓存中获取数据
		if (StringUtil.isNotEmpty(resCountyJson)) {
			combo2 = new Combo2<Integer, String>(ReturnCode.OK.getValue(), resCountyJson);
			return combo2;
		}
		try {
			countyModels = regionDao.getCountyByCityId(cityId, 0, true);
			if (countyModels != null && countyModels.size() > 0) {
				resCountyJson = JSON.json(countyModels);
				jedis.set(RedisKeyConstants.RSCCOUNTY, resCountyJson); // 放入缓存
			}
			combo2 = new Combo2<Integer, String>(ReturnCode.OK.getValue(), resCountyJson);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			combo2 = new Combo2<Integer, String>(ReturnCode.P_EXCEPTION.getValue(), resCountyJson);
		}
		return combo2;
	}
	
	/**
	 * 
	 * 根据城市id获取区域信息
	 *
	 * @author Huangyunjun
	 * @created 2016年07月20日 下午8:12:54
	 *
	 * @param cityId 城市id
	 * @param size 返回区域数据数量
	 * @return
	 */
	public Combo2<Integer, List<CountyModel>> getCountyByCityId(int cityId, int size) {
		List<CountyModel> countyModels = null;
		Combo2<Integer, List<CountyModel>> combo2 = null;
		try {
			countyModels = regionDao.getCountyByCityId(cityId, size, false);
			combo2 = new Combo2<Integer, List<CountyModel>>(ReturnCode.OK.getValue(), countyModels);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			combo2 = new Combo2<Integer, List<CountyModel>>(ReturnCode.P_EXCEPTION.getValue(), countyModels);
		}
		return combo2;
	}

}