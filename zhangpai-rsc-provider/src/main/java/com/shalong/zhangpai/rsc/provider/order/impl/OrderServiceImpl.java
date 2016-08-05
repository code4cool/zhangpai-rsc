package com.shalong.zhangpai.rsc.provider.order.impl;
/**
 * @FileName: OrderServiceImpl.java
 * @Package com.shalong.zhangpai.rsc.provider.order.impl
 * 
 * @author Huangyunjun
 * @created 2016年7月21日 下午7:41:22
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
import com.shalong.zhangpai.rsc.model.order.OrderModel;
import com.shalong.zhangpai.rsc.provider.common.utils.BusinessUtils;
import com.shalong.zhangpai.rsc.provider.order.dao.OrderDAO;
import com.shalong.zhangpai.rsc.provider.order.dao.OrderTransDao;
import com.shalong.zhangpai.rsc.provider.order.utils.OrderUtils;
import com.shalong.zhangpai.rsc.service.order.OrderService;
import com.shalong.zhangpai.rsc.thrift.enums.ReturnCode;

/**
 * <p>
 * 订单服务实现类
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
@Service("orderService")
public class OrderServiceImpl implements OrderService {
	
	private static RabbitLogger LOGGER = RabbitLogger.getInstance(OrderServiceImpl.class);
	
	@Autowired
	private OrderDAO orderDao;
	@Autowired
	OrderTransDao orderTransDao;
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public String cancelOrder(String paramString) {
		String returnString = null;
		Map<String, Object> paramMap = JsonUtils.json2Object(paramString, Map.class);
		if (paramMap == null) {
			return BusinessUtils.errorJson2Object();
		}
		
		String orderNumber = null;
		int buyerId = 0;
		try {
			buyerId = Integer.valueOf(paramMap.get("buyerId").toString());
			orderNumber = paramMap.get("orderNumber").toString();
			//查找订单是否存在
			OrderModel orderModel = this.orderDao.getUserOrderModelByOrderNumber(buyerId, orderNumber);
			if(orderModel==null){
				//订单不存在
				returnString = BusinessUtils.returnString(ReturnCode.SERVER_BUSY.getValue(), "订单不存在!", "", "");
				LOGGER.error("取消订单,订单不存在， orderNumber=" + orderNumber, null);
			}else {
				//订单存在,取消操作
				
				//检测订单是否可以取消
				boolean isCancelFlag = OrderUtils.isCancel(orderModel.getCreateTime());
				if(isCancelFlag){
					//可以取消
					this.orderTransDao.cancelOrder(orderModel);
					returnString = BusinessUtils.returnString(ReturnCode.OK.getValue(), "成功取消订单.", "", "");
					
				}else{
					//不能取消
					returnString = BusinessUtils.returnString(ReturnCode.SERVER_BUSY.getValue(), "订单不能被取消,卖家已发货,联系卖家.", "", "");
				}
				
			}
			 
		}catch (DataAccessException e) {
			returnString = BusinessUtils.errorBusiness();
			LOGGER.error(e.getMessage(), e);
		} catch (Exception e) {
			returnString = BusinessUtils.returnString(ReturnCode.SERVER_ERROR.getValue(), "异常", "", "");
			LOGGER.error("cancelOrder>>参数：" + paramString, e);
		}
		
		if (orderNumber != null) {
			//this.orderDao.updateOrderInServiceOfStatus(OrderUtils.SORDER_STATUS_USERCANCEL, returnString, orderNumber);
			 
		}
		
		return returnString;
	}

 
	
	/**
	 * 
	 * 我的订单列表
	 * 
	 * @author Huangyunjun
	 * @created 2016年07月22日 下午4:54:02
	 * 
	 * @param paramString
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	@Override
	public String searchMyOrderList(String paramString) {
		String returnString = null;
		Map<String, Object> values = null;
		Map<String, Object> paramMap = JsonUtils.json2Object(paramString, Map.class);
		if (paramMap == null) {
			return BusinessUtils.errorJson2Object();
		}

		try {
			int buyerId = Integer.parseInt(paramMap.get("buyerId").toString());
			int status = Integer.parseInt(paramMap.get("status").toString());
			int pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
			int page = Integer.parseInt(paramMap.get("page").toString());
			values = new HashMap<String, Object>();
			List<OrderModel> models = this.orderDao.searchPageOrderByCondition(buyerId, status, pageSize, page);
			long rows = this.orderDao.searchPageOrderByConditionCount(buyerId, status);
			if (models != null) {
				
				values.put("models", models);
				values.put("rows", rows);
			} else {
				values.put("models", "");
				values.put("rows", 0);
			}
			//LOGGER.info("searchMyOrderList!test:" + values);
			returnString = BusinessUtils.returnString(ReturnCode.OK.getValue(), "OK", values);
			LOGGER.info("searchMyOrderList!test:" + returnString);
		} catch (DataAccessException e) {
			returnString = BusinessUtils.errorBusiness();
			LOGGER.error(e.getMessage(), e);
		} catch (NumberFormatException e) {
			returnString = BusinessUtils.returnString(ReturnCode.SERVER_ERROR.getValue(), "类型转换异常", "", "");
			LOGGER.error("searchMyOrderList>>参数：" + paramString, e);
		} catch (Exception e) {
			returnString = BusinessUtils.returnString(ReturnCode.SERVER_ERROR.getValue(), "获取缓存数据异常", "", "");
			LOGGER.error(e.getMessage(), e);
		}
		return returnString;
	}


	@Override
	public String orderMain(String paramString) {
		String returnString = null;
		 
		return returnString;
	}
	
	
	/**
	 * 
	 * 订单详情
	 * 
	 * @author Huangyunjun
	 * @created 2016年07月22日 下午5:54:02
	 * 
	 * @param buyerId  
	 * @param  orderNumber 订单号
	 * @param paramString
	 * @return
	 */
	
	@Override
	public String getOrderDetail(String paramString) {
		String returnString = null;
		
		Map<String, Object> paramMap = JsonUtils.json2Object(paramString, Map.class);
		if (paramMap == null) {
			return BusinessUtils.errorJson2Object();
		}
		String orderNumber = null;
		int buyerId = 0;
		try {
			buyerId = Integer.valueOf(paramMap.get("buyerId").toString());
			orderNumber = paramMap.get("orderNumber").toString();
			OrderModel orderModel = this.orderDao.getUserOrderModelByOrderNumber(buyerId,orderNumber);
			
			if(orderModel != null) {
			 
			//result
			Map<String,Object> values = new HashMap<String, Object>();
			values.put("models", orderModel);
			returnString = BusinessUtils.returnString(ReturnCode.OK.getValue(), "订单内容信息", values);
			} else {
				returnString = BusinessUtils.returnString(ReturnCode.OK.getValue(), "没有此订单号","","");
			}
			
			
		} catch(Exception e) {
			returnString = BusinessUtils.errorBusiness();
			LOGGER.error(e.getMessage(), e);
		}
		
		return returnString;
	}

}
