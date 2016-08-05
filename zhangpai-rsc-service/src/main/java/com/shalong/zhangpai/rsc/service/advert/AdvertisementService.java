package com.shalong.zhangpai.rsc.service.advert;

import com.shalong.zhangpai.rsc.thrift.advertisement.GetAdvertisementInput;
import com.shalong.zhangpai.rsc.thrift.advertisement.GetAdvertisementOutput;

/**
 * @FileName: AdvertisementService.java
 * @Package com.shalong.zhangpai.rsc.service.advert
 * 
 * @author Huangyunjun
 * @created 2016年7月20日 上午10:35:11
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */



public interface AdvertisementService {
	
	/**
	 * @author Huangyunjun
	 * @created 2016年07月20日 下午3:29:59
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	public GetAdvertisementOutput getAdvertisementInfo(GetAdvertisementInput input) throws Exception;

}
