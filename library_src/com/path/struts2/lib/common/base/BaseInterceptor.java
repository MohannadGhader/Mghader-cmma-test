package com.path.struts2.lib.common.base;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class BaseInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception 
	{
		//toDo check upon method name and constraints whether to submit or no
		return invocation.invoke();
	}

}
