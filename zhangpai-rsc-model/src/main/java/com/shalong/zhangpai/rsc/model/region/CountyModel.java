/**
 * @FileName: RegionModel.java
 * @Package com.hyde.rsc.model
 * 
 * @author Huangyunjun
 * @created 2016年07月15日 下午5:51:50
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */
package com.shalong.zhangpai.rsc.model.region;

import com.shalong.zhangpai.rabbit.framework.base.model.BaseModel;
import com.shalong.zhangpai.rabbit.framework.data.database.Column;

/**
 * <p>TODO</p>
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


public class CountyModel extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7262188653289875746L;
	
	private int id; // 区域自动id
	private int cityId; // 城市id
	private String name; // 区域名称
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	@Column("Id")
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the cityId
	 */
	public int getCityId() {
		return cityId;
	}
	/**
	 * @param cityId the cityId to set
	 */
	@Column("CityId")
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	@Column("Name")
	public void setName(String name) {
		this.name = name;
	}

}
