package com.shalong.zhangpai.rsc.provider.order.dao;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.alibaba.druid.pool.DruidDataSource;
import com.shalong.zhangpai.rabbit.framework.core.exception.DataAccessException;
import com.shalong.zhangpai.rabbit.framework.data.database.DataContextTransaction;
import com.shalong.zhangpai.rsc.model.order.OrderModel;
import com.shalong.zhangpai.rsc.provider.common.utils.BusinessUtils;

/**
 * @FileName: OrderTransDao.java
 * @Package com.shalong.zhangpai.rsc.provider.order.dao
 * 
 * @author Huangyunjun
 * @created 2016年7月21日 下午8:38:02
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

/**
 * <p>
 * 持久化订单数据到数据库操作
 * </p>
 */
@Repository("orderTransDao")
public class OrderTransDao extends DataContextTransaction {

	/**
	 * @param masterDataSource
	 * @param slaveDataSource
	 * @throws SQLException
	 */
	@Autowired
	public OrderTransDao(
			@Qualifier("SLZP_ORDER_M") DruidDataSource masterDataSource,
			@Qualifier("SLZP_ORDER_S") DruidDataSource slaveDataSource)
			throws SQLException {
		super(masterDataSource, slaveDataSource);
	}
	/**
	 * 
	 * 取消订单
	 * 
	 * @author Huangyunjun
	 * @created 2016年07月21日 下午2:59:33
	 * 
	 * @param model
	 *            订单信息
	 * @param rsModel
	 *            不为null数据放入DR_RuleSetting表， 为null 不需要放入
	 */
	public void cancelOrder(OrderModel model) throws DataAccessException {

		try {
			String sqlText1 = "UPDATE Orders SET `PayStatus`=? WHERE `OrderNo`=?;";
			Object[] values1 = new Object[] { 3,model.getOrderNo() };
			
			  
			String[] sqlTexts = {sqlText1};
			Object[][] values = {values1};
			
			this.executeNonQuery(sqlTexts, values);
		} catch (SQLException e) {
			
			throw new DataAccessException(BusinessUtils.DB_ERROR_EXECUTE, e);
		}

	}
	/**
	 * 
	 * 更新订单状态
	 * 
	 * @author Huangyunjun
	 * @created 2016年07月22日 下午3:42:57
	 * 
	 * @param status
	 * @param orderNumber
	 * @return
	 * @throws SQLException
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unused")
	private int updateOrderStatus(int status, String orderNumber) throws SQLException {
		String sqlText = "UPDATE Orders SET `PayStatus`=? WHERE `OrderNo`=?;";
		Object[] values = new Object[] { status, orderNumber };
		return this.executeNonQuery(sqlText, values);
	}
	
	/**
	 * 事务提交
	 * 
	 * @throws SQLException
	 * */
	public void commit() throws SQLException {
		super.commit();
	}

	/**
	 * 事务回滚
	 * */
	public void rollback() throws DataAccessException {
		try {
			super.rollback();
		} catch (SQLException e) {
			throw new DataAccessException(BusinessUtils.DB_ERROR_ROLLBACK, e);
		}
	}

}
