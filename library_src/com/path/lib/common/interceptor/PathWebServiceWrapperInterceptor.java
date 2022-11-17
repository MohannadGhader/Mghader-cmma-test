package com.path.lib.common.interceptor;

import java.util.HashMap;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.path.bo.common.WebServiceCommonBO;
import com.path.lib.common.exception.BaseException;
import com.path.lib.common.util.ApplicationContextProvider;
import com.path.lib.log.Log;

public class PathWebServiceWrapperInterceptor implements MethodInterceptor
{
	
	protected final static Log log = Log.getInstance();


	/**
	 * A particular method for timeout is intercepted by calling this method.
	 * Will call a timeout exception if timed out.
	 */
	public Object invoke(final MethodInvocation method) throws Throwable
	{
		boolean logApplied = false;
		WebServiceCommonBO webServiceCommonBO = null;
		
		if(method.getArguments().length == 1 && method.getArguments()[0] instanceof HashMap<?, ?>
			&& method.getMethod().getReturnType().isAssignableFrom(HashMap.class))
		{
			HashMap<String, Object> hm = (HashMap<String, Object>)method.getArguments()[0]; 
			//call the logging BO
			webServiceCommonBO = (WebServiceCommonBO) ApplicationContextProvider.getApplicationContext().getBean("webServiceCommonBO");
			try 
			{
				log.info("logging wrapper request of  method is "+method.getMethod().getName()+" with params: "+hm);
				webServiceCommonBO.logRequest(hm);
			}
			catch (Exception e) 
			{
				throw new BaseException("Invalid Request");
			}
			logApplied = true;
		}
		Object ret=method.proceed();
		if(logApplied)
		{
			try 
			{
				log.info("logging wrapper response of  method is "+method.getMethod().getName()+" with result: "+ret);
				webServiceCommonBO.logResponse((HashMap<String, Object>)ret); 
			}
			catch (Exception e) 
			{
				throw new BaseException("Invalid Response");
			}
		}
		return ret;
	}

}
