package com.shalong.zhangpai.rsc.service.order;
/**
 * @FileName: OrderService.java
 * @Package com.shalong.zhangpai.rsc.service.order
 * 
 * @author Huangyunjun
 * @created 2016年7月21日 下午5:07:34
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

/**
 * <p>订单服务</p>
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

public interface OrderService {
	
	/**
	 * 
	 * 取消订单
	 * 改变状态，记录订单流水表
	 * 参数：{'buyerId':'9999','orderNumber':'123243252342342343'}
	 * 返回值:{'data':'','code':200,'msg':'ok'}
	 *
	 * @author Huangyunjun
	 * @created 2016年07月21日 上午10:16:40
	 *
	 * @param paramString
	 * @return
	 */
	public String cancelOrder(String paramString);
	
	/**
	 * 
	 * 我的订单列表
	 * 参数：{'buyerId':'','status':'','pageSize':'','page':''}
	 * 返回值：{'data':{'rows':总数量,'models':[{},{}]},'code':200,'msg':''}
	 * 
	 * @author Huangyunjun
	 * @created 2016年07月21日 下午14:54:02
	 *
	 * @param paramString
	 * @return
	 */
	public String searchMyOrderList(String paramString);
	
	
	/**
	 * 
	 * 提交订单主方法
	 * 参数：{'mapWebsiteHospitalId':'','mapWebsiteDepartmentId':'','mapWebsiteDoctorId':'','mapHospitalId':'','mapDepartmentId':'','mapDoctorId':'','userId':'','userRegistionIdentifyID':'' ,'doctorDutySourceId':'' ,'fzCode':'','serviceChannelId':'','sourceType':'','registeredDateString':'','registeredInterval':'','registeredCode':''}
	 * 返回值：{'code':'200','msg':'','data':{'orderNumber':'123456789874510006598746','busLines':'无', 'mobile':'110110'}}
	 *
	 * @author Huangyunjun
	 * @created 2016年07月22日 下午4:33:18
	 *
	 * @param paramString
	 * @return
	 */
	public String orderMain(String paramString);
	
	/**
	 * 获取用户订单的信息
	 * 参数：{'buyerId':'9999','orderNumber':'123243252342342343'}
	 * 返回值：
	 * */
	public String getOrderDetail(String paramString);
	
}
