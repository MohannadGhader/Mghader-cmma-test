package com.path.lib.remote;

import java.util.Calendar;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.path.bo.common.ConstantsCommon;
import com.path.lib.common.util.DateUtil;
import com.path.lib.common.util.StringUtil;

public class PathRmiInterceptor implements MethodInterceptor
{
    private static String servDelimtr = "-PATH_SERVICES[";
    public Object invoke(MethodInvocation invocation) throws Throwable
    {
	String currentThreadName = Thread.currentThread().getName();
	String newName = currentThreadName;
	newName = StringUtil.nullToEmpty(newName);
	int servInx = newName.indexOf(servDelimtr);
	if(servInx > 0)
	{
	    newName = newName.substring(0,servInx);
	}
	String newThreadName = newName
				.concat(servDelimtr)
				.concat(DateUtil.format(Calendar.getInstance().getTime(),"dd_MM_yyyy_hh_mm_ss"))
				.concat("-")
				.concat(ConstantsCommon.returnCurrentAppName())
				.concat("]");
	Thread.currentThread().setName(newThreadName);
	Object result = null;
	try
	{
	    result = invocation.proceed();
	}
	catch(Throwable t)
	{
	    Thread.currentThread().setName(currentThreadName);
	    throw t;
	}
	Thread.currentThread().setName(currentThreadName);
	return result;

    }
}