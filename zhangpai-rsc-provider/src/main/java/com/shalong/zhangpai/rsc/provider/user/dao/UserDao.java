package com.shalong.zhangpai.rsc.provider.user.dao;
/**
 * @FileName: UserDao.java
 * @Package com.shalong.zhangpai.rsc.provider.user
 * 
 * @author Huangyunjun
 * @created 2016年7月16日 下午5:30:52
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.alibaba.druid.pool.DruidDataSource;
import com.shalong.zhangpai.rabbit.framework.core.exception.DataAccessException;
import com.shalong.zhangpai.rabbit.framework.data.database.DataContext;
import com.shalong.zhangpai.rsc.model.user.UserModel;
import com.shalong.zhangpai.rsc.provider.common.utils.BusinessUtils;

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
@Repository("userDao")
public class UserDao extends DataContext{

	/**
	 * @param masterDataSource
	 * @param slaveDataSource
	 * @throws SQLException 
	 */
	@Autowired
	public UserDao(@Qualifier("SLZP_DUser_M") DruidDataSource masterDataSource,
			         @Qualifier("SLZP_DUser_S")DruidDataSource slaveDataSource) throws SQLException {
		super(masterDataSource, slaveDataSource);
	}
	
	/**
	 * 
	 * 给用户绑定手机号
	 *
	 * @author Huangyunjun
	 * @created 2016年07月16日 下午3:23:50
	 *
	 * @param model
	 * @return
	 * @throws DataAccessException
	 */
	public int bindMobile(UserModel model) throws DataAccessException {
		String sqlText = "UPDATE DM_Member SET `Mobile` = ? WHERE `UserId` = ?;";
		Object[] values = new Object[] { model.getMobile(), model.getUserId() };
		int count;
		try {
			count = this.executeNonQuery(sqlText, values);
		} catch (SQLException e) {
			throw new DataAccessException(BusinessUtils.DB_ERROR_EXECUTE, e);
		}
		return count;
	}
	
	/**
	 * 
	 * 根据手机号查询用户个数， 手机号不能重复
	 *
	 * @author Huangyunjun
	 * @created 2016年07月16日 下午3:39:41
	 *
	 * @param mobile
	 * @return
	 * @throws DataAccessException
	 */
	public long getUserCountByMobile(String mobile) throws DataAccessException {
		String sqlText = "SELECT count(UserId) FROM `DM_Member` WHERE `Mobile`=?";
		Object[] values = new Object[] { mobile };
		long count = 0;
		try {
			count = this.executeSingleQuery(sqlText, Long.class, values);
		} catch (Exception e) {
			throw new DataAccessException(BusinessUtils.DB_ERROR_SEARCH, e);
		}
		return count;
	}
	
	/**
	 * 
	 * 根据用户id和手机号查询用户数量
	 *
	 * @author Huangyunjun
	 * @created 2016年07月16日 下午3:39:41
	 *
	 * @param mobile
	 * @return
	 * @throws DataAccessException
	 */
	public long getUserCountByMobileAndUserId(int userId, String mobile) throws DataAccessException {
		String sqlText = "SELECT count(UserId) FROM `DM_Member` WHERE `UserId`=? AND `Mobile`=?";
		Object[] values = new Object[] { userId, mobile };
		long count = 0;
		try {
			count = this.executeSingleQuery(sqlText, Long.class, values);
		} catch (Exception e) {
			throw new DataAccessException(BusinessUtils.DB_ERROR_SEARCH, e);
		}
		return count;
	}

	public UserModel getUser(int userId) throws Exception {
		String sqlText = "SELECT `UserId`, `ShardId`, `UserName`, `EMail`, `Mobile`, `CardHolderName`, `IDNumber`, `Password`, `PassSalt`, `Gender`, `UserType`, `Status`,CreateDate FROM `DM_Member` WHERE `UserId`=?";
		Object[] values = new Object[] { userId };
		UserModel t = executeQuery(sqlText, UserModel.class, values);
		return t;
	}

	public UserModel getUser(String userName) throws Exception {
		String sqlText = "SELECT `UserId`, `ShardId`, `UserName`, `EMail`, `Mobile`, `CardHolderName`, `IDNumber`, `Password`, `PassSalt`, `Gender`, `UserType`, `Status`,`CreateDate`,`LastUpdateDate`,`LastLoginDate`,`Portrait` FROM `DM_Member` WHERE `UserName`=?";
		Object[] values = new Object[] { userName };
		UserModel t = executeQuery(sqlText, UserModel.class, values);
		return t;
	}

	public UserModel getUserByMobile(String mobile) throws Exception {
		String sqlText = "SELECT `UserId`, `ShardId`, `UserName`, `EMail`, `Mobile`, `CardHolderName`, `IDNumber`, `Password`, `PassSalt`, `Gender`, `UserType`, `Status` FROM `DM_Member` WHERE `Mobile`=?";
		Object[] values = new Object[] { mobile };
		UserModel t = executeQuery(sqlText, UserModel.class, values);
		return t;
	}

	public int add(UserModel t) throws Exception {
		return 0;
	}

	public int updatePassword(int userId, String password, String passSalt) throws Exception {
		String sqlText = "UPDATE `DM_Member` SET `Password`=?, `PassSalt`=? WHERE `UserId`=?";
		Object[] values = new Object[] { password, passSalt, userId };
		int ret = executeNonQuery(sqlText, values);
		return ret;
	}
}