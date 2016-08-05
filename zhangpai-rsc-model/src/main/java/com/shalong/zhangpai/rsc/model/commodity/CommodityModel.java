package com.shalong.zhangpai.rsc.model.commodity;

import com.shalong.zhangpai.rabbit.framework.base.model.BaseModel;
import com.shalong.zhangpai.rabbit.framework.core.log.RabbitLogger;
import com.shalong.zhangpai.rabbit.framework.data.database.Column;
import com.shalong.zhangpai.rsc.model.order.OrderModel;

/**
 * @FileName: CommodityModel.java
 * @Package com.shalong.zhangpai.rsc.model.commodity
 * 
 * @author Huangyunjun
 * @created 2016年7月23日 下午12:07:05
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

public class CommodityModel extends BaseModel{
	private static RabbitLogger LOGGER = RabbitLogger.getInstance(OrderModel.class);
	/**
	 * 序列号
	*/
	private static final long serialVersionUID = -7125716084058303996L;
	
	//-------------------数据库对应字段-------------------
	private int CommId;               //商品ID
	private String CommName;               //商品名称
	private int Classify;               //商品类型，类型表外键
	private String CommDes;               //商品描述
	private int SellNum;               //销量
	private String Weight;               //商品重量
	private String Size;               //商品尺寸
	private String Texture;               //商品材质
	private String Subjects;               //题材
	private String Skill;               //技法
	private String Font;               //字体
	private String CreateYears;               //创建年代
	private String Types;               //类型
	private String PlaceOfProduction;               //产地
	private String Uses;               //用途
	private String CommDetailImg;               //商品详情图片，多个使用 ，号隔开
	private String CommImg;               //商品图片，多个使用，号隔开
	private double StartingPrice;               //起拍价
	private double Freight;               //运费
	private double MarketEvaluation;               //市场估价
	private double Price;               //市场价
	private double AddPrice;               //加价幅度
	private int OwnerId;               //商品属主ID
	private String Province;               //省份
	private String City;               //城市
	private String ReleaseTime;               //发布时间
	private int Repertory;               //库存
	private int SharTimes;               //分享次数
	private String UpdateTimev;          //更新时间
	private int Status;               //1:正常 2：已删除
	private int Version;
	
	
	
	public int getCommId() {
		return CommId;
	}
	@Column("CommId")
	public void setCommId(int commId) {
		CommId = commId;
	}
	public String getCommName() {
		return CommName;
	}
	@Column("CommName")
	public void setCommName(String commName) {
		CommName = commName;
	}
	 
	public int getClassify() {
		return Classify;
	}
	@Column("Classify")
	public void setClassify(int classify) {
		Classify = classify;
	}
	public String getCommDes() {
		return CommDes;
	}
	@Column("CommDes")
	public void setCommDes(String commDes) {
		CommDes = commDes;
	}
	public int getSellNum() {
		return SellNum;
	}
	@Column("SellNum")
	public void setSellNum(int sellNum) {
		SellNum = sellNum;
	}
	public String getWeight() {
		return Weight;
	}
	@Column("Weight")
	public void setWeight(String weight) {
		Weight = weight;
	}
	public String getSize() {
		return Size;
	}
	@Column("Size")
	public void setSize(String size) {
		Size = size;
	}
	public String getTexture() {
		return Texture;
	}
	@Column("Texture")
	public void setTexture(String texture) {
		Texture = texture;
	}
	public String getSubjects() {
		return Subjects;
	}
	@Column("Subjects")
	public void setSubjects(String subjects) {
		Subjects = subjects;
	}
	public String getSkill() {
		return Skill;
	}
	@Column("Skill")
	public void setSkill(String skill) {
		Skill = skill;
	}
	public String getFont() {
		return Font;
	}
	@Column("Font")
	public void setFont(String font) {
		Font = font;
	}
	public String getCreateYears() {
		return CreateYears;
	}
	@Column("CreateYears")
	public void setCreateYears(String createYears) {
		CreateYears = createYears;
	}
	public String getTypes() {
		return Types;
	}
	@Column("Types")
	public void setTypes(String types) {
		Types = types;
	}
	public String getPlaceOfProduction() {
		return PlaceOfProduction;
	}
	@Column("PlaceOfProduction")
	public void setPlaceOfProduction(String placeOfProduction) {
		PlaceOfProduction = placeOfProduction;
	}
	public String getUses() {
		return Uses;
	}
	@Column("Uses")
	public void setUses(String uses) {
		Uses = uses;
	}
	public String getCommDetailImg() {
		return CommDetailImg;
	}
	@Column("CommDetailImg")
	public void setCommDetailImg(String commDetailImg) {
		CommDetailImg = commDetailImg;
	}
	public String getCommImg() {
		return CommImg;
	}
	@Column("CommImg")
	public void setCommImg(String commImg) {
		CommImg = commImg;
	}
	public double getStartingPrice() {
		return StartingPrice;
	}
	@Column("StartingPrice")
	public void setStartingPrice(double startingPrice) {
		StartingPrice = startingPrice;
	}
	public double getFreight() {
		return Freight;
	}
	@Column("Freight")
	public void setFreight(double freight) {
		Freight = freight;
	}
	public double getMarketEvaluation() {
		return MarketEvaluation;
	}
	@Column("MarketEvaluation")
	public void setMarketEvaluation(double marketEvaluation) {
		MarketEvaluation = marketEvaluation;
	}
	public double getPrice() {
		return Price;
	}
	@Column("Price")
	public void setPrice(double price) {
		Price = price;
	}
	public double getAddPrice() {
		return AddPrice;
	}
	@Column("OwnerId")
	public void setAddPrice(double addPrice) {
		AddPrice = addPrice;
	}
	public int getOwnerId() {
		return OwnerId;
	}
	@Column("OwnerId")
	public void setOwnerId(int ownerId) {
		OwnerId = ownerId;
	}
	public String getProvince() {
		return Province;
	}
	@Column("Province")
	public void setProvince(String province) {
		Province = province;
	}
	public String getCity() {
		return City;
	}
	@Column("City")
	public void setCity(String city) {
		City = city;
	}
	public String getReleaseTime() {
		return ReleaseTime;
	}
	@Column("ReleaseTime")
	public void setReleaseTime(String releaseTime) {
		ReleaseTime = releaseTime;
	}
	public int getRepertory() {
		return Repertory;
	}
	@Column("Repertory")
	public void setRepertory(int repertory) {
		Repertory = repertory;
	}
	public int getSharTimes() {
		return SharTimes;
	}
	@Column("SharTimes")
	public void setSharTimes(int sharTimes) {
		SharTimes = sharTimes;
	}
	public String getUpdateTimev() {
		return UpdateTimev;
	}
	@Column("UpdateTimev")
	public void setUpdateTimev(String updateTimev) {
		UpdateTimev = updateTimev;
	}
	public int getStatus() {
		return Status;
	}
	@Column("Status")
	public void setStatus(int status) {
		Status = status;
	}
	public int getVersion() {
		return Version;
	}
	@Column("Version")
	public void setVersion(int version) {
		Version = version;
	}
 
	
	
	

}
