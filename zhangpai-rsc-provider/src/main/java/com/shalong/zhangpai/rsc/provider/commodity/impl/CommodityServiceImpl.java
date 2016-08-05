package com.shalong.zhangpai.rsc.provider.commodity.impl;
/**
 * @FileName: CommodityServiceImpl.java
 * @Package com.shalong.zhangpai.rsc.provider.commodity.impl
 * 
 * @author Huangyunjun
 * @created 2016年7月23日 下午12:35:47
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shalong.zhangpai.rabbit.framework.core.exception.DataAccessException;
import com.shalong.zhangpai.rabbit.framework.core.log.RabbitLogger;
import com.shalong.zhangpai.rabbit.framework.core.util.JsonUtils;
import com.shalong.zhangpai.rsc.model.commodity.CommodityModel;
import com.shalong.zhangpai.rsc.provider.commodity.dao.CommodityDAO;
import com.shalong.zhangpai.rsc.provider.common.utils.BusinessUtils;
import com.shalong.zhangpai.rsc.service.commodity.CommodityService;
import com.shalong.zhangpai.rsc.thrift.enums.ReturnCode;

/**
 * <p>
 * 商品/拍品服务实现类
 * </p>
 * 
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 * 
 * @author Huangyunjun
 * @since 1.0
 * @version 1.0
 */
@Service("commodityService")
public class CommodityServiceImpl implements CommodityService {
	
private static RabbitLogger LOGGER = RabbitLogger.getInstance(CommodityServiceImpl.class);
	
	@Autowired
	private CommodityDAO commodityDAO;
	
	/**
	 * 
	 * 我的商品/拍品列表
	 * 参数：{'ownerId':'32','classify':'','pageSize':'10','page':'1'}
	 * 返回值：{'data':{'rows':总数量,'models':[{},{}]},'code':200,'msg':''}
	 * 
	 * @author Huangyunjun
	 * @created 2016年07月23日 下午14:54:02
	 *
	 * @param paramString
	 * @return
	 */
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public String searchCommodityList(String paramString) {
		String returnString = null;
		Map<String, Object> values = null;
		Map<String, Object> paramMap = JsonUtils.json2Object(paramString, Map.class);
		if (paramMap == null) {
			return BusinessUtils.errorJson2Object();
		}
		try {
			
			int ownerId = Integer.parseInt(paramMap.get("ownerId").toString());
			int classify = Integer.parseInt(paramMap.get("classify").toString());
			int pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
			int page = Integer.parseInt(paramMap.get("page").toString());
			values = new HashMap<String, Object>();
			List<CommodityModel> models = this.commodityDAO.searchPageCommodityByList(ownerId, classify, pageSize, page);
			long rows = this.commodityDAO.searchPageCommodityByCount(ownerId, classify);
			if (models != null) {
				
				values.put("models", models);
				values.put("rows", rows);
			} else {
				values.put("models", "");
				values.put("rows", 0);
			}
			
			returnString = BusinessUtils.returnString(ReturnCode.OK.getValue(), "OK", values);
			LOGGER.info("searchMyOrderList!test:" + returnString);
		}catch (DataAccessException e) {
			returnString = BusinessUtils.errorBusiness();
			LOGGER.error(e.getMessage(), e);
		} catch (NumberFormatException e) {
			returnString = BusinessUtils.returnString(ReturnCode.SERVER_ERROR.getValue(), "类型转换异常", "", "");
			LOGGER.error("searchCommodityList>>参数：" + paramString, e);
		} catch (Exception e) {
			returnString = BusinessUtils.returnString(ReturnCode.SERVER_ERROR.getValue(), "获取缓存数据异常", "", "");
			LOGGER.error(e.getMessage(), e);
		}
		
		return returnString;
	}

	/**
	 * 
	 * 商品详情
	 * 
	 * @author Huangyunjun
	 * @created 2016年07月22日 下午8:54:02
	 * 
	 * @param ownerId  
	 * @param  commId 商品ID
	 * @param paramString
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	@Override
	public String getCommodityDetail(String paramString) {
		String returnString = null;
		
		Map<String, Object> paramMap = JsonUtils.json2Object(paramString, Map.class);
		if (paramMap == null) {
			return BusinessUtils.errorJson2Object();
		}
		int ownerId = 0;
		int commId = 0;
		try {
			commId = Integer.valueOf(paramMap.get("commId").toString());
			ownerId = Integer.valueOf(paramMap.get("ownerId").toString());
			CommodityModel commodityModel = this.commodityDAO.getCommodityModelByCommId(commId, ownerId);
			
			if(commodityModel != null) {
			 
			//result
			Map<String,Object> values = new HashMap<String, Object>();
			values.put("models", commodityModel);
			returnString = BusinessUtils.returnString(ReturnCode.OK.getValue(), "商品内容信息", values);
			} else {
				returnString = BusinessUtils.returnString(ReturnCode.OK.getValue(), "没有此商品号","","");
			}
			
			
		} catch(Exception e) {
			returnString = BusinessUtils.errorBusiness();
			LOGGER.error(e.getMessage(), e);
		}
		
		return returnString;
	}
	

}
