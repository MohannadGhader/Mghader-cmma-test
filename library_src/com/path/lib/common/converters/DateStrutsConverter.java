/**
 * 
 */
package com.path.lib.common.converters;

import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.opensymphony.xwork2.ActionContext;
import com.path.lib.common.util.DateUtil;
import com.path.lib.common.util.StringUtil;
import com.path.lib.log.Log;

/**
 * Copyright 2014, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * @author: marwanmaddah
 *
 * DateStrutsConverter.java used to convert the parsed parameters from String to Date Object
 */
public class DateStrutsConverter extends StrutsTypeConverter
{
    protected final static Log log = Log.getInstance();
    @Override
    public Object convertFromString(Map context, String[] values, Class toClass)
    {
	Date result = null;
	if(values != null)
	{
	    String dateString = values[0] == null ? "" : values[0].trim();
	    if(!dateString.isEmpty())
	    {
		try
		{
		    // sometimes like in toolbar search one might not key in a
		    // valid date string which will generate exception,
		    // 160 means space but comes as special character in grid
		    // case
		    if(!StringUtil.nullToEmpty(dateString).isEmpty()
			    && (dateString.length() != 1 || dateString.codePointAt(0) != 160))
		    {
			Map<String, Object> session = ActionContext.getContext().getContextMap();
			result = DateUtil.returnDateObj(dateString, session);
		    }
		}
		catch(Exception e)
		{
		    log.error("Date string to be converted is not a valid date " + dateString);
		}
	    }
	}

	return result;
    }
    @Override
    public String convertToString(Map context, Object o)
    {
	return null;
    }
}
