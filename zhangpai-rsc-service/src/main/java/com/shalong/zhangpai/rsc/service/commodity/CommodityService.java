package com.shalong.zhangpai.rsc.service.commodity;
/**
 * 
 * @FileName: CommodityService.java
 * @Package com.shalong.zhangpai.rsc.service.commodity
 * 
 * @author Huangyunjun
 * @created 2016年7月23日 下午12:24:19
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

public interface CommodityService {
	
	 
	
	/**
	 * 
	 * 我的商品/拍品列表
	 * 参数：{'ownerId':'32','classify':'','pageSize':'10','page':'1'}
	 * 返回值：{'data':{'rows':总数量,'models':[{},{}]},'code':200,'msg':''}
	 * 
	 * @author Huangyunjun
	 * @created 2016年07月21日 下午14:54:02
	 *
	 * @param paramString
	 * @return
	 */
	public String searchCommodityList(String paramString);
	
 
	
	/**
	 * 获取用户订单的信息
	 * 参数：{'commId':'7','ownerId':'32'}
	 * 返回值：
	 * */
	public String getCommodityDetail(String paramString);

}
