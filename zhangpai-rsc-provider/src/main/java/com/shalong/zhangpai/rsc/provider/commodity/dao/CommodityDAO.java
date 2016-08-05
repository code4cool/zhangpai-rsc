package com.shalong.zhangpai.rsc.provider.commodity.dao;


import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.alibaba.druid.pool.DruidDataSource;
import com.shalong.zhangpai.rabbit.framework.core.exception.DataAccessException;
/**
 * @FileName: CommodityDAO.java
 * @Package com.shalong.zhangpai.rsc.provider.commodity.dao
 * 
 * @author Huangyunjun
 * @created 2016年7月23日 下午12:36:02
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */
import com.shalong.zhangpai.rabbit.framework.data.database.DataContext;
import com.shalong.zhangpai.rsc.model.commodity.CommodityModel;
import com.shalong.zhangpai.rsc.provider.common.utils.BusinessUtils;


/**
 * <p>商品/拍品相关数据库操作</p>
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
@Repository("commodityDAO")
public class CommodityDAO extends DataContext{
	/**
	 * @param masterDataSource
	 * @param slaveDataSource
	 * @throws SQLException 
	 */
	@Autowired
	public CommodityDAO(@Qualifier("SLZP_MT_M") DruidDataSource masterDataSource,
	         @Qualifier("SLZP_MT_S")DruidDataSource slaveDataSource) throws SQLException {
			super(masterDataSource, slaveDataSource);

	}
	
	/**
	 * 
	 * 分页查询总数量
	 *
	 * @author Huangyunjun
	 * @created 2016年07月23日  下午2:22:04
	 *
	 * @param ownerId  所属用户ID
	 * @param classify  分类ID 商品类型，类型表外键
	 * @return
	 */
	public long searchPageCommodityByCount(int ownerId, int classify) throws DataAccessException {
		StringBuffer sql = new StringBuffer();
		Object[] values = null;
		if (classify == 0) {
			sql.append("SELECT COUNT(`CommId`) FROM Mt_commodity AS mtco WHERE mtco.`OwnerId`=?;");
			values = new Object[]{ownerId};
		} else {
			sql.append("SELECT COUNT(`CommId`) FROM Mt_commodity AS ua WHERE mtco.`OwnerId`=? AND mtco.`Classify`=?;");
			values = new Object[]{ownerId, classify};
		}
		
		Long rows = null;
		try {
			rows = this.executeSingleQuery(sql.toString(), Long.class, values);
			rows = (rows == null) ? 0:rows;
		} catch (Exception e) {
			throw new DataAccessException(BusinessUtils.DB_ERROR_SEARCH + e.getMessage(), e);
		}
		return rows;
	}
	
	/**
	 * 
	 * 分页查询订单信息
	 *
	 * @author Huangyunjun
	 * @created 2016年07月23日 下午2:44:04
	 *
	 * @param ownerId  所属用户ID
	 * @param classify  分类ID 商品类型，类型表外键
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public List<CommodityModel> searchPageCommodityByList(int ownerId, int classify, int pageSize, int page) throws DataAccessException {
		pageSize = pageSize <= 0 ? 10 : pageSize;
		page = page <= 0 ? 1 : page;
		StringBuffer sql = new StringBuffer();
		Object[] values = null;
		if (classify == 0) {
			sql.append("SELECT CommId,CommName,Classify,CommDes,SellNum,Weight,Size,Texture, ");
			sql.append("Subjects,Skill,Font,CreateYears,Types,PlaceOfProduction,Uses,CommDetailImg, ");
			sql.append("CommImg,StartingPrice,Freight,MarketEvaluation,Price,AddPrice,OwnerId,Province, ");
			sql.append("City,DATE_FORMAT(ReleaseTime,'%Y-%m-%d') AS ReleaseTime,Repertory,SharTimes, ");
			sql.append("DATE_FORMAT(UpdateTime,'%Y-%m-%d') AS UpdateTime,Status,Version ");
			sql.append("FROM Mt_commodity ");
			sql.append("WHERE OwnerId=? ORDER BY ReleaseTime DESC LIMIT ?,?; ");
			values = new Object[]{ownerId,(page-1)*pageSize, pageSize};
		} else {
			sql.append("SELECT CommId,CommName,Classify,CommDes,SellNum,Weight,Size,Texture, ");
			sql.append("Subjects,Skill,Font,CreateYears,Types,PlaceOfProduction,Uses,CommDetailImg, ");
			sql.append("CommImg,StartingPrice,Freight,MarketEvaluation,Price,AddPrice,OwnerId,Province, ");
			sql.append("City,DATE_FORMAT(ReleaseTime,'%Y-%m-%d') AS ReleaseTime,Repertory,SharTimes, ");
			sql.append("DATE_FORMAT(UpdateTime,'%Y-%m-%d') AS UpdateTime,Status,Version ");
			sql.append("FROM Mt_commodity ");
			sql.append("WHERE OwnerId=? AND Classify=? ORDER BY CreateTime DESC LIMIT ?,?; ");
			values = new Object[]{ownerId, classify, (page-1)*pageSize, pageSize};
		}
	
 
		List<CommodityModel> models = null;
		try {
			models = this.executeListQuery(sql.toString(), CommodityModel.class, values);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataAccessException(BusinessUtils.DB_ERROR_SEARCH + e.getMessage(), e);
		}
		return models;
	}
	
	/**
	 * 
	 * 根据商品号查询商品信息
	 *
	 * @author Huangyunjun
	 * @created 2016年07月23日 下午5:02:58
	 *
	 * @param commId   商品ID
	 * @param ownerId  用户ID
	 * @return
	 * @throws DataAccessException
	 */
	public CommodityModel getCommodityModelByCommId(int commId,int ownerId) throws DataAccessException {
		String sql = null;
		Object[] values = null;
		if(ownerId==0){
		    sql = "SELECT CommId,CommName,Classify,CommDes,SellNum,Weight,Size,Texture,Subjects,Skill,Font,CreateYears," +
					 "Types,PlaceOfProduction,Uses,CommDetailImg,CommImg,StartingPrice,Freight,MarketEvaluation," + 
					 "Price,AddPrice,OwnerId,Province,City,DATE_FORMAT(ReleaseTime,'%Y-%m-%d') AS ReleaseTime," + 
					 "Repertory,SharTimes,DATE_FORMAT(UpdateTime,'%Y-%m-%d') AS UpdateTime,Status,Version " + 
					 "FROM Mt_commodity " +
					 "WHERE CommId = ?";
		     values = new Object[]{commId};
		}else{
			sql = "SELECT CommId,CommName,Classify,CommDes,SellNum,Weight,Size,Texture,Subjects,Skill,Font,CreateYears," +
					 "Types,PlaceOfProduction,Uses,CommDetailImg,CommImg,StartingPrice,Freight,MarketEvaluation," + 
					 "Price,AddPrice,OwnerId,Province,City,DATE_FORMAT(ReleaseTime,'%Y-%m-%d') AS ReleaseTime," + 
					 "Repertory,SharTimes,DATE_FORMAT(UpdateTime,'%Y-%m-%d') AS UpdateTime,Status,Version " + 
					 "FROM Mt_commodity " +
					 "WHERE CommId = ? AND OwnerId=?";
		      values = new Object[]{commId,ownerId};
		}
		
		
		CommodityModel model = null;
		 
		try {
			model = this.executeQuery(sql.toString(), CommodityModel.class, values);
		} catch (Exception e) {
			throw new DataAccessException(BusinessUtils.DB_ERROR_SEARCH + e.getMessage(), e);
		}
		return model;
	}
	
}
