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

public class DataContext implements IDataContext {

	private DataMapper dataMapper;
	private DruidDataSource masterDruidDataSource;
	private DruidDataSource slaveDruidDataSource;

	public DataContext(DruidDataSource dataSource) {
		if (dataSource == null) {
			throw new InvalidParameterException("DruidDataSource is not null.");
		}
		dataMapper = new DataMapper();
		masterDruidDataSource = slaveDruidDataSource = dataSource;

	}

	public DataContext(DruidDataSource masterDataSource, DruidDataSource slaveDataSource) {
		if (masterDataSource == null || slaveDataSource == null) {
			throw new InvalidParameterException("DruidDataSource is not null.");
		}
		dataMapper = new DataMapper();
		masterDruidDataSource = masterDataSource;
		slaveDruidDataSource = slaveDataSource;
	}

	public Connection getSlavelConnection() throws SQLException {
		return slaveDruidDataSource.getConnection();
//		throw new InvalidParameterException("DataContext is not surport...");
	}
	
	public Connection getConnection() throws Exception {
		 return masterDruidDataSource.getConnection();
	}

	public int executeNonQuery(String sqlText) throws SQLException {
		Connection conn = null;
		Statement st = null;
		try {
			conn = masterDruidDataSource.getConnection();
			st = conn.createStatement();
			return st.executeUpdate(sqlText);
		} catch (SQLException e) {
			throw e;
		} finally {
			if (st != null)
				st.close();
			if (conn != null)
				conn.close();
		}
	}

	public int executeNonQuery(String sqlText, Object... values) throws SQLException {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = masterDruidDataSource.getConnection();
			st = conn.prepareStatement(sqlText);
			for (int i = 0; i < values.length; i++) {
				st.setObject(i + 1, values[i]);
			}
			return st.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			if (st != null)
				st.close();
			if (conn != null)
				conn.close();
		}
	}

	public int executeNonQuery(String sqlText, String[] params, Object... values) throws SQLException {
		Connection conn = null;
		CallableStatement st = null;
		try {
			conn = masterDruidDataSource.getConnection();
			sqlText = DataContextHelper.parseCallSqlText(sqlText, params.length);
			st = conn.prepareCall(sqlText);
			DataContextHelper.parseStatement(st, params, values);
			return st.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			if (st != null)
				st.close();
			if (conn != null)
				conn.close();
		}
	}

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
			if(rs.next()) {
				T ret = (T) rs.getObject(1);
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

	@Override
	public void attemptClose(Connection conn) throws Exception {
		if(conn != null) conn.close();
	}

	@Override
	public void attemptClose(PreparedStatement pstat)  throws Exception {
		if(pstat != null) pstat.close();
	}

	@Override
	public void attemptClose(ResultSet rs)  throws Exception {
		if(rs != null) rs.close();
		
	}

	@Override
	public int executeNonQuery(String[] sqlTexts, Object[][] values)
			throws Exception {
		Connection conn = this.getConnection();
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
}
