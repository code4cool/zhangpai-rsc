package com.shalong.zhangpai.rsc.provider.region.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.alibaba.druid.pool.DruidDataSource;
import com.shalong.zhangpai.rabbit.framework.data.database.DataContext;
import com.shalong.zhangpai.rsc.model.region.CountyModel;

/**
 * @FileName: RegionDao.java
 * @Package com.shalong.zhangpai.rsc.provider.region.dao
 * 
 * @author Huangyunjun
 * @created 2016年7月20日 上午5:44:47
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

@Repository("regionDao")
public class RegionDao extends DataContext{

	/**
	 * @param masterDataSource
	 * @param slaveDataSource
	 */
	@Autowired
	public RegionDao(@Qualifier("SLZP_DUser_M")DruidDataSource masterDataSource,
			         @Qualifier("SLZP_DUser_S")DruidDataSource slaveDataSource) {
		super(masterDataSource, slaveDataSource);
	}
	
	/**
	 * 
	 * 根据城市id获取区域信息
	 *
	 * @author Huangyunjun
	 * @created 2016年07月20日 下午2:45:31
	 *
	 * @param cityId
	 * @param size
	 * @param isAll
	 * @return
	 * @throws Exception
	 */
	public List<CountyModel> getCountyByCityId(int cityId, int size, boolean isAll) throws Exception {
		String sqlText = "";
		Object[] values = null;
		
		if (isAll) {
			sqlText = "SELECT `Id`, `CityId`, `Name` FROM `DH_County` WHERE `CityId`=?";
			values = new Object[] { cityId };
		} else {
			sqlText = "SELECT `Id`, `CityId`, `Name` FROM `DH_County` WHERE `CityId`=? LIMIT 0,?";
			values = new Object[] { cityId, size };
		}
		return executeListQuery(sqlText, CountyModel.class, values);
	}
	
}

