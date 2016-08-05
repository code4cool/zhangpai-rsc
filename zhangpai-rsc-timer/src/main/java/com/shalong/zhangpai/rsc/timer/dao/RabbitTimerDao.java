package com.shalong.zhangpai.rsc.timer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.alibaba.druid.pool.DruidDataSource;
import com.shalong.zhangpai.rabbit.framework.cache.RedisCacheClient;
import com.shalong.zhangpai.rabbit.framework.core.config.GlobalConfig;
import com.shalong.zhangpai.rabbit.framework.data.database.DataContext;
import com.shalong.zhangpai.rsc.timer.message.TimerConfig;

/**
 * @FileName: RabbitTimerDao.java
 * @Package com.shalong.zhangpai.rsc.timer.dao
 * 
 * @author Huangyunjun
 * @created 2016年7月30日 下午8:33:22
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */
@Repository("rabbitTimerDao")
public class RabbitTimerDao extends DataContext {

	Logger log = Logger.getLogger(RabbitTimerDao.class);
	
	 /**
     * reids客户端
     */
	private RedisCacheClient jedis = new RedisCacheClient(GlobalConfig.getReidsHostAndPort(),"doctor");
	
	
	@Autowired
	public RabbitTimerDao(@Qualifier("SLZP_MT_M") DruidDataSource masterDataSource,
            			@Qualifier("SLZP_MT_S")DruidDataSource slaveDataSource) {
		super(masterDataSource, slaveDataSource);
	}
	
	public List<TimerConfig> queryTimerConfig() throws Exception {
		
		String sql = "SELECT Id,TimerTaskName,TimerType,TimerDelay,TimerPeriod,IsExecute,`Status` FROM MT_TimerConfig WHERE `Status`=1";
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		List<TimerConfig> list = new ArrayList<TimerConfig>();
		try {
			conn = this.getSlavelConnection();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while(rs.next()) {
				TimerConfig config = new TimerConfig();
				config.id = rs.getInt("Id");
				config.timerTaskName = rs.getString("TimerTaskName");
				config.timerType = rs.getInt("TimerType");
				config.timerDelay = rs.getLong("TimerDelay") * 1000;
				config.timerPeriod = rs.getLong("TimerPeriod") * 1000;
				config.isExecute = rs.getInt("IsExecute");
				config.status = rs.getInt("Status");
				
				list.add(config);
			}
		}catch(Exception e) {
			throw e;
		}finally {
			attemptClose(conn);
			attemptClose(pstat);
			attemptClose(rs);
		}
		
		if(list.isEmpty()) return null;
		
		return list;
	}

	/**
	 * 
	 * 更新定时任务状态
	 *
	 * @author Huangyunjun
	 * @created 2016年07月30日  下午2:22:04
	 *
	 * @param config  定时任务配置信息
	 * @return
	 */
	
	public void updateTimerConfig(TimerConfig config) throws Exception {

		String sql = "UPDATE MT_TimerConfig SET IsExecute=?,LastUpdateTime=NOW() WHERE Id=?";
		Connection conn = null;
		PreparedStatement pstat = null;
		try {
			conn = this.getConnection();
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, config.isExecute);
			pstat.setInt(2, config.id);
			pstat.executeUpdate();//execute update
		}catch(Exception e) {
			throw e;
		}finally {
			attemptClose(conn);
			attemptClose(pstat);
		}
	}

}
