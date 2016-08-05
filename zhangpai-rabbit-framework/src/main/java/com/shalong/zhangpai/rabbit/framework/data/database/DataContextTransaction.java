package com.shalong.zhangpai.rabbit.framework.data.database;

import java.security.InvalidParameterException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.alibaba.druid.pool.DruidDataSource;

public class DataContextTransaction implements IDataContext {

	private Connection connection;
	private DataMapper dataMapper;
	private DruidDataSource slaveDruidDataSource;
	private DruidDataSource masterDataSource;

	public DataContextTransaction(DruidDataSource dataSource) throws SQLException {
		if (dataSource == null) {
			throw new InvalidParameterException("DruidDataSource is not null.");
		}
		dataMapper = new DataMapper();
		connection = dataSource.getConnection();
		connection.setAutoCommit(false);
	}

	public DataContextTransaction(DruidDataSource masterDataSource, DruidDataSource slaveDataSource) throws SQLException {
		if (masterDataSource == null || slaveDataSource == null) {
			throw new InvalidParameterException("DruidDataSource is not null.");
		}
		dataMapper = new DataMapper();
		connection = masterDataSource.getConnection();
		connection.setAutoCommit(false);
		slaveDruidDataSource = slaveDataSource;
		this.masterDataSource = masterDataSource;
	}

	public Connection getSlaveConnection() throws Exception {
		return slaveDruidDataSource.getConnection();
	}

	@Override
	public int executeNonQuery(String sqlText) throws SQLException {
		Statement st = null;
		try {
			st = connection.createStatement();
			return st.executeUpdate(sqlText);
		} catch (SQLException e) {
			throw e;
		} finally {
			if (st != null)
				st.close();
		}
	}

	@Override
	public int executeNonQuery(String sqlText, Object... values) throws SQLException {
		PreparedStatement st = null;
		try {
			if(connection  == null || connection.isClosed()) {
				connection = masterDataSource.getConnection();
				connection.setAutoCommit(false);
			}
			st = connection.prepareStatement(sqlText);
			for (int i = 0; i < values.length; i++) {
				st.setObject(i + 1, values[i]);
			}
			return st.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			if (st != null)
				st.close();
		}
	}
	
	/**
	 * 事务提交方法
	 * sqlTexts sql语句集合
	 * values 与sqlTexts下标对齐，如果没有参数用null代替
	 */
	@Override
	public int executeNonQuery(String[] sqlTexts, Object[][] values) throws SQLException {
		Connection conn = masterDataSource.getConnection();
		//set auto commit to false
		conn.setAutoCommit(false);
		PreparedStatement st = null;
		int num = 0;
		try {
			for(int i = 0;i < sqlTexts.length;i++) {
				String sqlText = sqlTexts[i];
				st = conn.prepareStatement(sqlText);
				Object[] value = values[i];
				if(value != null) {
					for (int j = 0; j < value.length; j++) {
						st.setObject(j + 1, value[j]);
					}
				}
				num += st.executeUpdate();
			}
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			throw e;
		} finally {
			if (st != null) st.close();
			if(conn != null) conn.close();
		}
		return num;
	}

	@Override
	public int executeNonQuery(String sqlText, String[] params, Object... values) throws SQLException {
		CallableStatement st = null;
		try {
			sqlText = DataContextHelper.parseCallSqlText(sqlText, params.length);
			
			if(connection  == null || connection.isClosed()) {
				connection = masterDataSource.getConnection();
				connection.setAutoCommit(false);
			}
			
			st = connection.prepareCall(sqlText);
			DataContextHelper.parseStatement(st, params, values);
			return st.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			if (st != null)
				st.close();
		}
	}

	@Override
	public <T> T executeQuery(String sqlText, Class<T> clazz) throws Exception {
		Connection conn = null;
		Statement st = null;
		try {
			conn = slaveDruidDataSource.getConnection();
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sqlText);
			T ret = dataMapper.mapper(rs, clazz);
			return ret;
		} catch (SQLException e) {
			throw e;
		} finally {
			if (st != null)
				st.close();
			if (conn != null)
				conn.close();
		}
	}

	@Override
	public <T> T executeQuery(String sqlText, Class<T> clazz, Object... values) throws Exception {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = slaveDruidDataSource.getConnection();
			st = conn.prepareStatement(sqlText);
			for (int i = 0; i < values.length; i++) {
				st.setObject(i + 1, values[i]);
			}
			ResultSet rs = st.executeQuery();
			T ret = dataMapper.mapper(rs, clazz);
			return ret;
		} catch (Exception e) {
			throw e;
		} finally {
			if (st != null)
				st.close();
			if (conn != null)
				conn.close();
		}
	}

	@Override
	public <T> T executeQuery(String sqlText, Class<T> clazz, String[] params, Object... values) throws Exception {
		Connection conn = null;
		CallableStatement st = null;
		try {
			conn = slaveDruidDataSource.getConnection();
			sqlText = DataContextHelper.parseCallSqlText(sqlText, params.length);
			st = conn.prepareCall(sqlText);
			DataContextHelper.parseStatement(st, params, values);
			ResultSet rs = st.executeQuery();
			T ret = dataMapper.mapper(rs, clazz);
			return ret;
		} catch (Exception e) {
			throw e;
		} finally {
			if (st != null)
				st.close();
			if (conn != null)
				conn.close();
		}
	}

	@Override
	public <T> List<T> executeListQuery(String sqlText, Class<T> clazz) throws Exception {
		Connection conn = null;
		Statement st = null;
		try {
			conn = slaveDruidDataSource.getConnection();
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sqlText);
			List<T> ret = dataMapper.mapperSet(rs, clazz);
			return ret;
		} catch (Exception e) {
			throw e;
		} finally {
			if (st != null)
				st.close();
			if (conn != null)
				conn.close();
		}
	}

	@Override
	public <T> List<T> executeListQuery(String sqlText, Class<T> clazz, Object... values) throws Exception {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = slaveDruidDataSource.getConnection();
			st = conn.prepareStatement(sqlText);
			for (int i = 0; i < values.length; i++) {
				st.setObject(i + 1, values[i]);
			}
			ResultSet rs = st.executeQuery();
			List<T> ret = dataMapper.mapperSet(rs, clazz);
			return ret;
		} catch (Exception e) {
			throw e;
		} finally {
			if (st != null)
				st.close();
			if (conn != null)
				conn.close();
		}
	}

	@Override
	public <T> List<T> executeListQuery(String sqlText, Class<T> clazz, String[] params, Object... values) throws Exception {
		Connection conn = null;
		CallableStatement st = null;
		try {
			conn = slaveDruidDataSource.getConnection();
			sqlText = DataContextHelper.parseCallSqlText(sqlText, params.length);
			st = conn.prepareCall(sqlText);
			DataContextHelper.parseStatement(st, params, values);
			ResultSet rs = st.executeQuery();
			List<T> ret = dataMapper.mapperSet(rs, clazz);
			return ret;
		} catch (Exception e) {
			throw e;
		} finally {
			if (st != null)
				st.close();
			if (conn != null)
				conn.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T executeSingleQuery(String sqlText, Class<T> clazz) throws Exception {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = slaveDruidDataSource.getConnection();
			st = conn.prepareStatement(sqlText);
			ResultSet rs = st.executeQuery();
			T ret = (T) rs.getObject(1);
			return ret;
		} catch (Exception e) {
			throw e;
		} finally {
			if (st != null)
				st.close();
			if (conn != null)
				conn.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T executeSingleQuery(String sqlText, Class<T> clazz, Object... values) throws Exception {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = slaveDruidDataSource.getConnection();
			st = conn.prepareStatement(sqlText);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					st.setObject(i + 1, values[i]);
				}
			}
			ResultSet rs = st.executeQuery();
			Object value;
			if (rs.next()) {
				value = rs.getObject(1);
				T ret = (T) value;
				return ret;
			}
			return null;
		} catch (Exception e) {
			throw e;
		} finally {
			if (st != null)
				st.close();
			if (conn != null)
				conn.close();
		}
	}

	/**
	 * 关闭数据源
	 * */
	public void close() throws SQLException {
		if (connection != null)
			connection.close();
	}

	/**
	 * 事务提交
	 * */
	public void commit() throws SQLException {
		if (connection != null)
			connection.commit();
	}

	/**
	 * 事务回滚
	 * */
	public void rollback() throws SQLException {
		if (connection != null)
			connection.rollback();
	}

	@Override
	public void attemptClose(Connection conn) throws Exception {
		if (conn != null)
			conn.close();
	}

	@Override
	public void attemptClose(PreparedStatement pstat) throws Exception {
		if (pstat != null)
			pstat.close();
	}

	@Override
	public void attemptClose(ResultSet rs) throws Exception {
		if (rs != null)
			rs.close();
	}

	/* (non-Javadoc)
	 * @see com.rabbit.framework.data.database.IDataContext#getSlavelConnection()
	 */
	@Override
	public Connection getSlavelConnection() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
