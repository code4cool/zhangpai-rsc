package com.shalong.zhangpai.rsc.consumer.handler;

import java.util.List;

import org.apache.thrift.TException;

import com.shalong.zhangpai.rabbit.framework.base.model.Combo2;
import com.shalong.zhangpai.rabbit.framework.base.model.ModelMapper;
import com.shalong.zhangpai.rabbit.framework.core.log.RabbitLogger;
import com.shalong.zhangpai.rsc.consumer.main.SpringContext;
import com.shalong.zhangpai.rsc.model.region.CountyModel;
import com.shalong.zhangpai.rsc.service.region.RegionService;
import com.shalong.zhangpai.rsc.thrift.enums.ReturnCode;
import com.shalong.zhangpai.rsc.thrift.region.CountyOutput;
import com.shalong.zhangpai.rsc.thrift.region.GetCountyByCityIdInput;
import com.shalong.zhangpai.rsc.thrift.region.GetCountyByCityIdOutput;
import com.shalong.zhangpai.rsc.thrift.region.GetRedisCountyAllByCityIdInput;
import com.shalong.zhangpai.rsc.thrift.region.GetRedisCountyAllByCityIdOutput;
import com.shalong.zhangpai.rsc.thrift.region.RegionService.Iface;

/**
 * @FileName: RegionServiceHandler.java
 * @Package com.shalong.zhangpai.rsc.consumer.handler
 * 
 * @author Huangyunjun
 * @created 2016年7月20日 上午7:26:21
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

public class RegionServiceHandler implements Iface{
	
	private static RabbitLogger LOGGER = RabbitLogger.getInstance(RegionServiceHandler.class);
	
	/**
	 * 区域服务
	 */
	private RegionService regionService = SpringContext.getRegionService();
	
	/**
	 * model转换对象
	 */
	private ModelMapper modelMapper = new ModelMapper();
	
	
 
	@Override
	public GetCountyByCityIdOutput getCountyByCityId(GetCountyByCityIdInput input) throws TException {
		GetCountyByCityIdOutput output = new GetCountyByCityIdOutput();
		if (input == null) {
			output.setReturnCode(ReturnCode.MISSING_ARG.getValue());
			return output;
		}
		LOGGER.info("getCountyToFeatureHospital参数：" + input.getCityId() + "--" + input.getSize());
		Combo2<Integer, List<CountyModel>> combo2 = regionService.getCountyByCityId(input.getCityId(), input.getSize());
		if (combo2.getV1().intValue() == ReturnCode.OK.getValue()) {
			List<CountyModel> countyModels = combo2.getV2();
			List<CountyOutput> countyOutputs = null;
			if (countyModels != null && countyModels.size() > 0) {
				try {
					countyOutputs = modelMapper.mapperList(countyModels, CountyOutput.class);
				} catch (Exception e) {
					LOGGER.error(e.getMessage(), e);
					output.setReturnCode(ReturnCode.C_EXCEPTION.getValue());
				}
				output.setValues(countyOutputs);
				output.setReturnCode(ReturnCode.OK.getValue());
			} else {
				output.setReturnCode(ReturnCode.C_DATANULL.getValue());
			}
		} else {
			output.setReturnCode(combo2.getV1().intValue());
		}
		return output;
	}


	@Override
	public GetRedisCountyAllByCityIdOutput getRedisCountyAllByCityId(GetRedisCountyAllByCityIdInput input) throws TException {
		GetRedisCountyAllByCityIdOutput output = new GetRedisCountyAllByCityIdOutput();
		if (input == null) {
			output.setReturnCode(ReturnCode.MISSING_ARG.getValue());
			return output;
		}
		LOGGER.info("getRedisCountyAllByCityId参数：" + input.getCityId());
		String resCountyJson = "";
		Combo2<Integer, String> combo2 = regionService.getCountyByCityId(input.getCityId());
		if (combo2.getV1().intValue() == ReturnCode.OK.getValue()) {
			resCountyJson = combo2.getV2();
			output.setJson(resCountyJson);
			output.setReturnCode(ReturnCode.OK.getValue());
		} else {
			output.setJson(resCountyJson);
			output.setReturnCode(combo2.getV1().intValue());
		}
		return output;
	}
	
	

}