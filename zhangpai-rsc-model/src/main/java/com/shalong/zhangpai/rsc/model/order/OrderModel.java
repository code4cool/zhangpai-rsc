package com.shalong.zhangpai.rsc.model.order;



import java.util.Map;

/**
 * @FileName: OrderModel.java
 * @Package com.shalong.zhangpai.rsc.model.order
 * 
 * @author Huangyunjun
 * @created 2016年7月21日 下午4:57:26
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

import com.shalong.zhangpai.rabbit.framework.base.model.BaseModel;
import com.shalong.zhangpai.rabbit.framework.core.log.RabbitLogger;
import com.shalong.zhangpai.rabbit.framework.data.database.Column;
/**
 * <p>订单持久化表对象</p>
 * 
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 * 
 * @author Huangyunjn
 * @since 1.0
 * @version 1.0
 */

public class OrderModel extends BaseModel{
	private static RabbitLogger LOGGER = RabbitLogger.getInstance(OrderModel.class);
	/**
	 * 序列号
	*/
	private static final long serialVersionUID = -4125716084058303996L;
	
	//-------------------数据库对应字段-------------------
	private int id;
	private int serviceChannelId;  //订单来源
	private String orderNo;  //订单编号
	private int buyerId;  //买家id'
	private int businessId;  //商家id
	private int type;  //订单类型 1 拍品订单 2 精品订单 3 订制订单 4 vip订单
	private String requestNo;  //交易请求号
	private int orderAmount;  //订单原价
	private String redPacketAmount;  //红包金额
	private String freight;  //运费
	private String realAmount;  //实际支付金额
	private String poundageRate;  //佣金率
	private String poundage;  //佣金
	private String payType;  //（ALIPAY,FREE，WEIXIN）
	private int payStatus;  //交易状态 0 未付款 1 已付款 2 已完成 3 已关闭 4 删除
	private String payTime;  //付款时间'
	private String receiver;  //收货人姓名
	private String address;  //收货地址
	private String tel;  //收货人电话
	private String postCode;  //postCode
	private int mailStatus;  //发货状态 1 未发货 2 已发货
	private String express;  //快递公司
	private String expressNo;  //运单号
	private String description;  //备注 前台留言
	private String remark;  //后台客服备注
	private int extand;  //是否延期（自动确认收货用) 0 未延期 1 已延期
	private String loseTime;  //订单自动确认收货时间
	private String createTime;  //创建时间
	private int isComment;  //是否评论 1 未评论 2 已评论
	private int status;  //0 正常 1 无效
	private int version;  //乐观锁 版本号
	private int isCredits;  //是否结算 0 未结算 1 已结算
	private String creditsTime;  //结算时间
	
	/**
	 * 
	 * 根据map值完善该对象
	 *
	 * @author Huangyunjun
	 * @created 2016年07月22日 上午8:34:31
	 *
	 * @param map
	 */
	public void mapValues(Map<String, Object> map) {
	    this.serviceChannelId = Integer.parseInt(map.get("serviceChannelId").toString());
		LOGGER.info("mapValues method");
	}
 
	public int getId() {
		return id;
	}
	@Column("Id")
	public void setId(int id) {
		this.id = id;
	}
	public int getServiceChannelId() {
		return serviceChannelId;
	}
	@Column("ServiceChannelId")
	public void setServiceChannelId(int serviceChannelId) {
		this.serviceChannelId = serviceChannelId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	@Column("OrderNo")
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public int getBuyerId() {
		return buyerId;
	}
	@Column("BuyerId")
	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}
	public int getBusinessId() {
		return businessId;
	}
	@Column("BusinessId")
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}
	public int getType() {
		return type;
	}
	@Column("Type")
	public void setType(int type) {
		this.type = type;
	}
	public String getRequestNo() {
		return requestNo;
	}
	@Column("RequestNo")
	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}
	public int getOrderAmount() {
		return orderAmount;
	}
	@Column("OrderAmount")
	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}
	public String getRedPacketAmount() {
		return redPacketAmount;
	}
	@Column("RedPacketAmount")
	public void setRedPacketAmount(String redPacketAmount) {
		this.redPacketAmount = redPacketAmount;
	}
	public String getFreight() {
		return freight;
	}
	@Column("Freight")
	public void setFreight(String freight) {
		this.freight = freight;
	}
	public String getRealAmount() {
		return realAmount;
	}
	@Column("RealAmount")
	public void setRealAmount(String realAmount) {
		this.realAmount = realAmount;
	}
	public String getPoundageRate() {
		return poundageRate;
	}
	@Column("PoundageRate")
	public void setPoundageRate(String poundageRate) {
		this.poundageRate = poundageRate;
	}
	public String getPoundage() {
		return poundage;
	}
	@Column("Poundage")
	public void setPoundage(String poundage) {
		this.poundage = poundage;
	}
	public String getPayType() {
		return payType;
	}
	@Column("PayType")
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public int getPayStatus() {
		return payStatus;
	}
	@Column("PayStatus")
	public void setPayStatus(int payStatus) {
		this.payStatus = payStatus;
	}
	public String getPayTime() {
		return payTime;
	}
	@Column("PayTime")
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	public String getReceiver() {
		return receiver;
	}
	@Column("Receiver")
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getAddress() {
		return address;
	}
	@Column("Address")
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	@Column("Tel")
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPostCode() {
		return postCode;
	}
	@Column("PostCode")
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public int getMailStatus() {
		return mailStatus;
	}
	@Column("MailStatus")
	public void setMailStatus(int mailStatus) {
		this.mailStatus = mailStatus;
	}
	public String getExpress() {
		return express;
	}
	@Column("Express")
	public void setExpress(String express) {
		this.express = express;
	}
	public String getExpressNo() {
		return expressNo;
	}
	@Column("ExpressNo")
	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}
	public String getDescription() {
		return description;
	}
	@Column("Description")
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRemark() {
		return remark;
	}
	@Column("Remark")
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getExtand() {
		return extand;
	}
	@Column("Extand")
	public void setExtand(int extand) {
		this.extand = extand;
	}
	public String getLoseTime() {
		return loseTime;
	}
	@Column("LoseTime")
	public void setLoseTime(String loseTime) {
		this.loseTime = loseTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	@Column("CreateTime")
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getIsComment() {
		return isComment;
	}
	@Column("IsComment")
	public void setIsComment(int isComment) {
		this.isComment = isComment;
	}
	public int getStatus() {
		return status;
	}
	@Column("Status")
	public void setStatus(int status) {
		this.status = status;
	}
	public int getVersion() {
		return version;
	}
	@Column("Version")
	public void setVersion(int version) {
		this.version = version;
	}
	public int getIsCredits() {
		return isCredits;
	}
	@Column("IsCredits")
	public void setIsCredits(int isCredits) {
		this.isCredits = isCredits;
	}
	public String getCreditsTime() {
		return creditsTime;
	}
	@Column("CreditsTime")
	public void setCreditsTime(String creditsTime) {
		this.creditsTime = creditsTime;
	}
 
 
}
