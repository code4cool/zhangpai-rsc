package com.shalong.zhangpai.rabbit.framework.data.database;

import java.sql.CallableStatement;
import java.sql.SQLException;

public class DataContextHelper {

	public static String parseCallSqlText(String sqlText, int paramsCount) {
		StringBuffer sb = new StringBuffer();
		sb.append("{call ");
		sb.append(sqlText);
		sb.append("(");
		for (int i = 0; i < paramsCount; i++) {
			sb.append("?");
			if (i < paramsCount - 1) {
				sb.append(",");
			}
		}
		sb.append(")}");
		return sb.toString();
	}

	public static void parseStatement(CallableStatement stmt, String[] params, Object... values) throws SQLException {
		for (int i = 0; i < values.length; i++) {
			if (params == null || params.length != values.length) {
				stmt.setObject(i + 1, values[i]);
			} else {
				stmt.setObject(params[i], values[i]);
			}
		}
	}

}
