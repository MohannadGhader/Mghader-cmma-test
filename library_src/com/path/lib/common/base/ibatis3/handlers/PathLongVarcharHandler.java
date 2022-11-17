package com.path.lib.common.base.ibatis3.handlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
/**
 * DENISK_LATEST_VERS_UPDATED
 * Copyright 2014, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * @author: deniskhaddad
 *
 * PathLongVarcharHandler.java used to handle LongVarchar results when LONGVARCHAR jdbcType is needed
 * and since LONGVARCHAR specification will lead to ClobTypeHandler of Mybatis, and which is not working in sybase
 * in order not to truncate out parameter of procedure is more than 255 characters returned. 
 */
public class PathLongVarcharHandler extends BaseTypeHandler {


    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType)
        throws SQLException {
      String s = (String) parameter;
      ps.setString(i, s);
    }

    public Object getNullableResult(ResultSet rs, String columnName)
        throws SQLException {
      return rs.getString(columnName);
    }

    public Object getNullableResult(CallableStatement cs, int columnIndex)
        throws SQLException {
	return cs.getString(columnIndex);
    }

	public Object getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		return rs.getString(columnIndex);
	}

}
