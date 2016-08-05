package com.shalong.zhangpai.rsc.provider.advert.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.alibaba.druid.pool.DruidDataSource;
import com.shalong.zhangpai.rabbit.framework.data.database.DataContext;
import com.shalong.zhangpai.rsc.model.advert.AdvertModel;
import com.shalong.zhangpai.rsc.thrift.advertisement.Advertisement;

/**
 * @FileName: AdvertisementDAO.java
 * @Package com.shalong.zhangpai.rsc.provider.advert.dao
 * 
 * @author Huangyunjun
 * @created 2016年7月20日 下午12:25:01
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

@Repository("advertisementDAO")
public class AdvertisementDAO extends DataContext{
	/**
	 * @param dataSource
	 */
	@Autowired
	public AdvertisementDAO(@Qualifier("SLZP_MT_M")DruidDataSource masterDataSource,
			@Qualifier("SLZP_MT_S")DruidDataSource slaveDataSource) {
		super(masterDataSource, slaveDataSource);
	}
	
	/**
	 * 查询广告全量信息
	 *
	 * @author huangyunjun
	 * @created 2016年07月20  上午9:40:36
	 *
	 * @param adId
	 * @return
	 * @throws Exception
	 */
	
	public AdvertModel queryAdvertisementModel(String adId) throws Exception {
		String sql = "SELECT Id,AdTitle,AdDescription,CreateTime,AdLocation,IpSingleNum,IpMuchNum,OnlineStatus,AuditStatus,AdImage,AdLink,AdContent FROM MT_Advertisement WHERE Id=?";
		Object[] values = new Object[]{adId};
		AdvertModel model = this.executeQuery(sql, AdvertModel.class,values);
		return model;
		
	}
	public Advertisement getAdvertisementInfo(int avertId, Advertisement advertisement) throws Exception {
		
		String sql = "SELECT Id,AdTitle,AdDescription,CreateTime,AdLocation,IpSingleNum,IpMuchNum,OnlineStatus,AuditStatus,AdImage,AdLink,AdContent " +
			     "FROM mt_advertisement " +
			     "WHERE Id=? ";
		 
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try {
			conn = this.getSlavelConnection();
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, avertId);
			rs = pstat.executeQuery();
			if(rs.next()) {
				advertisement.setId(rs.getInt("Id"));
				advertisement.setAdTitle(rs.getString("AdTitle"));
				advertisement.setAdDescription(rs.getString("AdDescription"));
				advertisement.setCreateTime(rs.getString("CreateTime"));
				advertisement.setAdLocation(rs.getInt("AdLocation"));
				advertisement.setIpSingleNum(rs.getInt("IpSingleNum"));
				advertisement.setIpMuchNum(rs.getInt("IpMuchNum"));
				advertisement.setOnlineStatus(rs.getInt("OnlineStatus"));
				advertisement.setAuditStatus(rs.getInt("AuditStatus"));
				advertisement.setAdImage(rs.getString("AdImage"));
				advertisement.setAdLink(rs.getString("AdLink"));
				advertisement.setAdContent(rs.getString("AdContent"));
 
				
			}else {
				return null;
			}
		}catch(Exception e) {
			throw e;
		}finally {
			this.attemptClose(rs);
			this.attemptClose(pstat);
			this.attemptClose(conn);
		}

		return advertisement;
	}
	
	
	/**
	 * 查询全部广告数量
	 *
	 * @author Huangyunjun
	 * @created 2016年07月20日 下午12:17:38
	 *
	 * @return
	 * @throws Exception 
	 */
	public int queryAdvertCount() throws Exception {
		String sql = "SELECT COUNT(1) FROM MT_Advertisement";
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = this.getSlavelConnection();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(Exception e) {
			throw e;
		}finally {
			attemptClose(rs);
			attemptClose(pstat);
			attemptClose(conn);
		}
		return count;
	}
	
	
	
}
