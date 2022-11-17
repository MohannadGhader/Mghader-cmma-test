/**
 * 
 */
package com.path.lib.common.converters;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

/**
 * Copyright 2013, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author: marwanmaddah
 * 
 * IntegerStrutsConverter.java used to override the default struts Integer Converter  
 */
public class IntegerStrutsConverter extends StrutsTypeConverter
{
    /**
     * to convert the data from String to Integer.
     */
    public Object convertFromString(Map context, String[] values, Class toClass)
    {
	Integer theValue = null;
	if(values != null)
	{
	    if("".equals(values[0]))
	    {
		theValue = null;
	    }
	    else
	    {
		theValue = Integer.valueOf(values[0]);
	    }
	}

	return theValue;
    }

    public String convertToString(Map context, Object o)
    {
	String theValue = null;
	if(o != null)
	{
	    theValue = o.toString();
	}
	return theValue;

    }
}
