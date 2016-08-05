package com.shalong.zhangpai.rsc.model.advert;

import com.shalong.zhangpai.rabbit.framework.base.model.BaseModel;

/**
 * @FileName: AdvertModel.java
 * @Package com.shalong.zhangpai.rsc.model.advert
 * 
 * @author Huangyunjun
 * @created 2016年7月20日 上午10:20:59
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

/* 
* <p>TODO</p>
* <PRE>
* <BR>	修改记录
* <BR>-----------------------------------------------
* <BR>	修改日期			修改人			修改内容
* 
* </PRE>
* 
* @author Huangyunjun
* @since 1.0
* @version 1.0
*/

public class AdvertModel extends BaseModel{
	private static final long serialVersionUID = -7262188651289875746L;
	
	private int id; // 自动id
	

	private String adTitle; //广告标题
	private String adDescription; //广告描述
	
	private String createTime; //创建时间
	private int adLocation; //广告位置
	private int ipSingleNum;  //单个IP人数
	private int ipMuchNum;    //共享ip人数
	private int onlineStatus;  //广告状态
	private int auditStatus;   //审核排序
	private String adImage;       //广告图片
	private String adLink;       //广告链接
	private String adContent;     //广告详情
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAdTitle() {
		return adTitle;
	}
	public void setAdTitle(String adTitle) {
		this.adTitle = adTitle;
	}
	public String getAdDescription() {
		return adDescription;
	}
	public void setAdDescription(String adDescription) {
		this.adDescription = adDescription;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getAdLocation() {
		return adLocation;
	}
	public void setAdLocation(int adLocation) {
		this.adLocation = adLocation;
	}
	public int getIpSingleNum() {
		return ipSingleNum;
	}
	public void setIpSingleNum(int ipSingleNum) {
		this.ipSingleNum = ipSingleNum;
	}
	public int getIpMuchNum() {
		return ipMuchNum;
	}
	public void setIpMuchNum(int ipMuchNum) {
		this.ipMuchNum = ipMuchNum;
	}
	public int getOnlineStatus() {
		return onlineStatus;
	}
	public void setOnlineStatus(int onlineStatus) {
		this.onlineStatus = onlineStatus;
	}
	public int getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(int auditStatus) {
		this.auditStatus = auditStatus;
	}
	public String getAdImage() {
		return adImage;
	}
	public void setAdImage(String adImage) {
		this.adImage = adImage;
	}
	public String getAdLink() {
		return adLink;
	}
	public void setAdLink(String adLink) {
		this.adLink = adLink;
	}
	public String getAdContent() {
		return adContent;
	}
	public void setAdContent(String adContent) {
		this.adContent = adContent;
	}
	


}
