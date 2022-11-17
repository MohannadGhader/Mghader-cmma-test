package com.path.lib.common.converters;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.path.bo.common.ConstantsCommon;

public class BigDecimalStrutsConverter extends StrutsTypeConverter 
{
    	    public final static BigDecimal EMPTY_BIGDECIMAL_VALUE = ConstantsCommon.EMPTY_BIGDECIMAL_VALUE;
	    public Object convertFromString(Map context, String[] values, Class toClass) 
	    {
		BigDecimal theValue = null ;
		if(values != null)
		{
        		if("".equals(values[0]))
        		{
        		    theValue = EMPTY_BIGDECIMAL_VALUE;
        		}
        		else
        		{
        		    theValue = new BigDecimal(values[0]);
        		}
		}
		
		return theValue;
	    }
	    
	    public String convertToString(Map context, Object o) 
	    {
		String theValue = null;
		if(o != null)
		{
		    if(EMPTY_BIGDECIMAL_VALUE.compareTo((BigDecimal)o) == 0)
		    {
			theValue = "";
		    }
		    else
		    {
			theValue = ((BigDecimal)o).toPlainString(); //solves issues of scientific notation on load of page
		    }
		}
		return  theValue;
	    }

}
