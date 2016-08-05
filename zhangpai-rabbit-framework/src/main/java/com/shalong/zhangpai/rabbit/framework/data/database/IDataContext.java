package com.shalong.zhangpai.rabbit.framework.data.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IDataContext {

	/**
	 * 获取数据库连接
	 * 
	 * */
	public Connection getSlavelConnection() throws SQLException;

	/**
	 * 执行SQL DDL statement
	 * */
	public int executeNonQuery(String sqlText) throws SQLException;

	/**
	 * 使用参数化方式执行SQL DDL statement，其中参数必须写成?的形式，values为对应顺序的值
	 * */
	public int executeNonQuery(String sqlText, Object... values) throws SQLException;

	/**
	 * 执行存储过程
	 * */
	public int executeNonQuery(String sqlText, String[] params, Object... values) throws SQLException;

	/**
	 * 执行SQL DDL statement查询
	 * */
	public <T> T executeQuery(String sqlText, Class<T> clazz) throws Exception;

	/**
	 * 使用参数化方式执行SQL DDL statement查询，其中参数必须写成?的形式，values为对应顺序的值
	 * */
	public <T> T executeQuery(String sqlText, Class<T> clazz, Object... values) throws Exception;

	/**
	 * 使用存储过程查询
	 * */
	public <T> T executeQuery(String sqlText, Class<T> clazz, String[] params, Object... values) throws Exception;

	/**
	 * 执行SQL DDL statement查询
	 * */
	public <T> List<T> executeListQuery(String sqlText, Class<T> clazz) throws Exception;

	/**
	 * 使用参数化方式执行SQL DDL statement查询，其中参数必须写成?的形式，values为对应顺序的值
	 * */
	public <T> List<T> executeListQuery(String sqlText, Class<T> clazz, Object... values) throws Exception;

	/**
	 * 使用存储过程查询
	 * */
	public <T> List<T> executeListQuery(String sqlText, Class<T> clazz, String[] params, Object... values) throws Exception;

	/**
	 * 返回单值
	 * */
	public <T> T executeSingleQuery(String sqlText, Class<T> clazz) throws Exception;

	/**
	 * 返回单值
	 * */
	public <T> T executeSingleQuery(String sqlText, Class<T> clazz, Object... values) throws Exception;
	
	public void attemptClose(Connection conn) throws Exception;
	
	public void attemptClose(PreparedStatement pstat) throws Exception;
	
	public void attemptClose(ResultSet rs) throws Exception;

	/**
	 * 事务提交的方法
	 *
	 * @author narisu
	 * @created 2014年1月2日 下午3:54:49
	 *
	 * @param sqlTexts
	 * @param values
	 * @return
	 * @throws SQLException
	 * @throws Exception 
	 */
	public int executeNonQuery(String[] sqlTexts, Object[][] values)	throws SQLException, Exception;

}
