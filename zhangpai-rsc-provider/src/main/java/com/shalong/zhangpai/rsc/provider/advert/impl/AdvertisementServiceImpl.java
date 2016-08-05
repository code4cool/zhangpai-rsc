package com.shalong.zhangpai.rsc.provider.advert.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shalong.zhangpai.rabbit.framework.core.log.RabbitLogger;
import com.shalong.zhangpai.rsc.provider.advert.collection.AdvertisementDataCollection;
import com.shalong.zhangpai.rsc.service.advert.AdvertisementService;
import com.shalong.zhangpai.rsc.thrift.advertisement.Advertisement;
import com.shalong.zhangpai.rsc.thrift.advertisement.GetAdvertisementInput;
import com.shalong.zhangpai.rsc.thrift.advertisement.GetAdvertisementOutput;
import com.shalong.zhangpai.rsc.thrift.enums.ReturnCode;

/**
 * @FileName: AdvertisementServiceImpl.java
 * @Package com.shalong.zhangpai.src.provider.advert.impl
 * 
 * @author Huangyunjun
 * @created 2016年7月20日 下午12:23:18
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

@Service("advertisementService")
public class AdvertisementServiceImpl implements AdvertisementService {
	private static RabbitLogger LOGGER = RabbitLogger.getInstance(AdvertisementServiceImpl.class);
	
	@Autowired
	AdvertisementDataCollection  dataCollection;
	
	 
	@Override
	public GetAdvertisementOutput getAdvertisementInfo(GetAdvertisementInput input) throws Exception {
		// TODO Auto-generated method stub
		GetAdvertisementOutput output = new GetAdvertisementOutput();
		output.setReturnCode(ReturnCode.OK.getValue());
		try {
			Advertisement advertisement = dataCollection.getAdvertisement(input.getId());
			output.setAdvertisement(advertisement);
		}catch (Exception e) {
			output.setReturnCode(ReturnCode.SERVER_ERROR.getValue());
			LOGGER.error(e.getMessage(), e);
		}
		return output;
	}
	
	


}
