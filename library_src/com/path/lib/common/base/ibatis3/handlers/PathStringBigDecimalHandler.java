package com.path.lib.common.base.ibatis3.handlers;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.path.lib.common.util.NumberUtil;
/**
 * Copyright 2018, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * @author: adelnasrallah
 *
 * PathStringBigDecimalHandler.java used to handle String to BigDecimal conversion.
 */
public class PathStringBigDecimalHandler extends BaseTypeHandler {


    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType)
	    throws SQLException
    {
	String s = (String) parameter;
	if(NumberUtil.isNumber(s))
	{
	    BigDecimal d = new BigDecimal(s);
	    ps.setBigDecimal(i, d);
	}
	else
	{
	      ps.setString(i, s);
	}
    }

    public Object getNullableResult(ResultSet rs, String columnName) throws SQLException
    {
	return rs.getBigDecimal(columnName);
    }

    public Object getNullableResult(CallableStatement cs, int columnIndex) throws SQLException
    {
	return cs.getBigDecimal(columnIndex);
    }

    public Object getNullableResult(ResultSet rs, int columnIndex) throws SQLException
    {
	return rs.getBigDecimal(columnIndex);
    }

}
