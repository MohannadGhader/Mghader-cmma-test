package com.path.lib.common.interceptor;

/**
 * @author rwilson
 * This method is used for setting timeout for methods. The BO names and methods names
 * to be intercepted are specified in the application context.
 */
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.PatternMatchUtils;

import com.path.lib.log.Log;

public class TimeoutInterceptor implements MethodInterceptor
{
	
	protected final static Log log = Log.getInstance();

	/**
	 * Is set from bean specified from application context.
	 * Contains the method name, timeout measure and timeout unit.
	 */
	private Properties timeoutProps;

	/**
	 * A particular method for timeout is intercepted by calling this method.
	 * Will call a timeout exception if timed out.
	 */
	public Object invoke(final MethodInvocation method) throws Throwable
	{
		ExecutorService executor = Executors.newCachedThreadPool();
		Callable<Object> task = new Callable<Object>()
		{
			public Object call()throws Exception
			{
				try
				{
					return method.proceed();
				}
				catch(Throwable e)
				{
					e.printStackTrace();
					Exception ex = (Exception)e;
					throw ex;
				}
			}
		};
		
		Future<Object> future = executor.submit(task);
		try
		{
			String timeoutParam = null;
			Set<Object> methodNames = timeoutProps.keySet();
			for(Object methodName : methodNames)
			{
				/**
				 * Checking for dynamic method identification given from application context.
				 */
				if(PatternMatchUtils.simpleMatch(methodName.toString(), method.getMethod().getName()))
				{
					timeoutParam = timeoutProps.getProperty(methodName.toString());
					break;
				}

			}

			Object result = null;

			if(timeoutParam == null)
			{
			    	long start = System.currentTimeMillis();
				result = future.get(100, TimeUnit.SECONDS);
				long end = System.currentTimeMillis();
				long timeMs = end - start;
				log.info(" took: " + timeMs + "ms.");
			}
			/**
			 * Default timeout will be 100 seconds
			 */
			else
			{
			    	String[] paramArray = timeoutParam.split(",");
				Long timeoutMeasure = Long.parseLong(paramArray[0]);
				String timeoutUnit = paramArray[1];
				long start = System.currentTimeMillis();
				if("s".equalsIgnoreCase(timeoutUnit))
				{
				    result = future.get(timeoutMeasure, TimeUnit.SECONDS);
				}
				else if("mi".equalsIgnoreCase(timeoutUnit))
				{
				    result = future.get(timeoutMeasure, TimeUnit.MILLISECONDS);
				}
				else if("h".equalsIgnoreCase(timeoutUnit))
				{
				    result = future.get(timeoutMeasure, TimeUnit.HOURS);
				}
				else if("d".equalsIgnoreCase(timeoutUnit))
				{
				    result = future.get(timeoutMeasure, TimeUnit.DAYS);
				}
				else if("mic".equalsIgnoreCase(timeoutUnit))
				{
				    result = future.get(timeoutMeasure, TimeUnit.MICROSECONDS);
				}
				else if("m".equalsIgnoreCase(timeoutUnit))
				{
				    result = future.get(timeoutMeasure, TimeUnit.MINUTES);
				}
				else
				{
				    result = future.get(timeoutMeasure, TimeUnit.SECONDS);//If unit is not given then seconds is default.
				}

				long end = System.currentTimeMillis();
				long timeMs = end - start;
				log.info(" took: " + timeMs + "ms.");
			}

			return result;
		}
		catch(TimeoutException ex)
		{
			ex.printStackTrace();
			throw ex;
		}
	}

	public Properties getTimeoutProps()
	{
		return timeoutProps;
	}

	public void setTimeoutProps(Properties timeoutProps)
	{
		this.timeoutProps = timeoutProps;
	}

}
