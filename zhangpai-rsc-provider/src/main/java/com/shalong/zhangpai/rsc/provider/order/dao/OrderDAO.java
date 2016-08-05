package com.shalong.zhangpai.rsc.provider.order.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.alibaba.druid.pool.DruidDataSource;
import com.shalong.zhangpai.rabbit.framework.core.exception.DataAccessException;
import com.shalong.zhangpai.rabbit.framework.data.database.DataContext;
import com.shalong.zhangpai.rsc.model.order.OrderModel;
import com.shalong.zhangpai.rsc.provider.common.utils.BusinessUtils;
/**
 * <p>订单相关数据库操作</p>
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

@Repository("orderDao")
public class OrderDAO extends DataContext{
	

	/**
	 * @param masterDataSource
	 * @param slaveDataSource
	 * @throws SQLException 
	 */
	@Autowired
	public OrderDAO(@Qualifier("SLZP_ORDER_M") DruidDataSource masterDataSource,
	         @Qualifier("SLZP_ORDER_S")DruidDataSource slaveDataSource) throws SQLException {
			super(masterDataSource, slaveDataSource);
	}
	
	/**
	 * 
	 * 根据订单号查询订单信息
	 *
	 * @author Huangyunjun
	 * @created 2016年07月22日 下午5:02:58
	 *
	 * @param orderNumber
	 * @return
	 * @throws DataAccessException
	 */
	public OrderModel getUserOrderModelByOrderNumber(int buyerId,String orderNumber) throws DataAccessException {
		String sql = "SELECT Id,ServiceChannelId,OrderNo,BuyerId,BusinessId,Type,RequestNo,OrderAmount," +
					 "RedPacketAmount,Freight,RealAmount,PoundageRate,Poundage,PayType,PayStatus," + 
					 "DATE_FORMAT(PayTime,'%Y-%m-%d') AS PayTime,Receiver,Address,Tel,PostCode,MailStatus,Express," + 
					 "ExpressNo,Description,Remark,Extand,DATE_FORMAT(LoseTime,'%Y-%m-%d') AS LoseTime," + 
					 "DATE_FORMAT(CreateTime,'%Y-%m-%d') AS CreateTime,IsComment,Status,Version," + 
					 "IsCredits,DATE_FORMAT(CreditsTime,'%Y-%m-%d') AS CreditsTime " + 
					 "FROM Orders " +
					 "WHERE OrderNo = ? AND BuyerId=?";
		Object[] values = new Object[]{orderNumber,buyerId};
		OrderModel model = null;
		 
		try {
			model = this.executeQuery(sql.toString(), OrderModel.class, values);
		} catch (Exception e) {
			throw new DataAccessException(BusinessUtils.DB_ERROR_SEARCH + e.getMessage(), e);
		}
		return model;
	}
 
	/**
	 * 
	 * 更新订单状态
	 *
	 * @author Huangyunjun
	 * @created 2016年07月22日 上午10:28:55
	 *
	 * @param buyerId
	 * @param orderNo
	 * @return
	 */
	public int updateOrderStatus(int status, String orderNo) throws DataAccessException {
		String sqlText = "UPDATE Orders SET `Status`=? WHERE `OrderNo`=?;";
		Object[] values = new Object[]{status, orderNo};
		int count = 0;
		try {
			count = this.executeNonQuery(sqlText, values);
		} catch (SQLException e) {
			throw new DataAccessException(BusinessUtils.DB_ERROR_EXECUTE + e.getMessage(), e);
		}
		return count;
	}
	
	/**
	 * 
	 * 分页查询总数量
	 *
	 * @author Huangyunjun
	 * @created 2016年07月22日  下午4:34:04
	 *
	 * @param buyerId
	 * @param status
	 * @return
	 */
	public long searchPageOrderByConditionCount(int buyerId, int status) throws DataAccessException {
		StringBuffer sql = new StringBuffer();
		Object[] values = null;
		if (status == 0) {
			sql.append("SELECT COUNT(`Id`) FROM Orders AS ua WHERE ua.`BuyerId`=?;");
			values = new Object[]{buyerId};
		} else {
			sql.append("SELECT COUNT(`Id`) FROM Orders AS ua WHERE ua.`BuyerId`=? AND ua.`PayStatus`=?;");
			values = new Object[]{buyerId, status};
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
	 * @created 2016年07月22日 下午4:34:04
	 *
	 * @param buyerId
	 * @param status
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public List<OrderModel> searchPageOrderByCondition(int buyerId, int status, int pageSize, int page) throws DataAccessException {
		pageSize = pageSize <= 0 ? 10 : pageSize;
		page = page <= 0 ? 1 : page;
		StringBuffer sql = new StringBuffer();
		Object[] values = null;
		if (status == 0) {
			sql.append("SELECT Id,ServiceChannelId,OrderNo,BuyerId,BusinessId,Type,RequestNo,OrderAmount, ");
			sql.append("RedPacketAmount,Freight,RealAmount,PoundageRate,Poundage,PayType,PayStatus, ");
			sql.append("DATE_FORMAT(PayTime,'%Y-%m-%d') AS PayTime,Receiver,Address,Tel,PostCode,MailStatus,Express, ");
			sql.append("ExpressNo,Description,Remark,Extand,DATE_FORMAT(LoseTime,'%Y-%m-%d') AS LoseTime, ");
			sql.append("DATE_FORMAT(CreateTime,'%Y-%m-%d') AS CreateTime,IsComment,Status,Version, ");
			sql.append("IsCredits,DATE_FORMAT(CreditsTime,'%Y-%m-%d') AS CreditsTime ");
			sql.append("FROM Orders ");
			sql.append("WHERE buyerId=? ORDER BY CreateTime DESC LIMIT ?,?; ");
			values = new Object[]{buyerId,(page-1)*pageSize, pageSize};
		} else {
			sql.append("SELECT Id,ServiceChannelId,OrderNo,BuyerId,BusinessId,Type,RequestNo,OrderAmount, ");
			sql.append("RedPacketAmount,Freight,RealAmount,PoundageRate,Poundage,PayType,PayStatus,");
			sql.append("DATE_FORMAT(PayTime,'%Y-%m-%d') AS PayTime,Receiver,Address,Tel,PostCode,MailStatus,Express, ");
			sql.append("ExpressNo,Description,Remark,Extand,DATE_FORMAT(LoseTime,'%Y-%m-%d') AS LoseTime, ");
			sql.append("DATE_FORMAT(CreateTime,'%Y-%m-%d') AS CreateTime,IsComment,Status,Version, ");
			sql.append("IsCredits,DATE_FORMAT(CreditsTime,'%Y-%m-%d') AS CreditsTime ");
			sql.append("FROM Orders ");
			sql.append("WHERE buyerId=? AND PayStatus=? ORDER BY CreateTime DESC LIMIT ?,?; ");
			values = new Object[]{buyerId, status, (page-1)*pageSize, pageSize};
		}
	
 
		List<OrderModel> models = null;
		try {
			models = this.executeListQuery(sql.toString(), OrderModel.class, values);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataAccessException(BusinessUtils.DB_ERROR_SEARCH + e.getMessage(), e);
		}
		return models;
	}
	
	

}
