package com.path.lib.remote;

import java.lang.reflect.Method;
import java.util.Map;

import com.path.lib.common.exception.BaseException;
import com.path.lib.common.util.ApplicationContextProvider;
import com.path.lib.common.util.StringUtil;
import com.path.lib.log.Log;

public class LocalServiceCaller
{

    /**
     * private constructor to prevent the class being instantiated since all
     * methods are static
     */
    private LocalServiceCaller()
    {
	Log.getInstance().error("This Class is utility class cannot be instantiated");
    }

    /**
     * Method that executed a local method in provided BO with given parameter as
     * Map
     * @param boReference BO beanId
     * @param boMethod Method to be called
     * @param inputMap Parameter Map to be passed to the method
     * @return output Parameter Map
     * @throws BaseException
     */
    public static Map<String, Object> executeLocalMethod(String boReference, String boMethod,
	    Map<String, Object> inputMap) throws BaseException
    {
	return executeLocalMethod( boReference,  boMethod,  inputMap, null);
    }
    
    /**
     * Method that executed a local method in provided BO with given parameter as
     * Map
     * @param boReference BO beanId
     * @param boMethod Method to be called
     * @param inputMap Parameter Map to be passed to the method
     * @param paramClassType Class dataType of the PArameter to be passed example HashMap.class, Map.class, if passed as null then Map.class is considered
     * @return output Parameter Map
     * @throws BaseException
     */
    public static Map<String, Object> executeLocalMethod(String boReference, String boMethod,
	    Map<String, Object> inputMap, Class paramClassType) throws BaseException
    {
	try
	{
	    if(StringUtil.isNotEmpty(boReference))
	    {
		Object boServiceObject = ApplicationContextProvider.getApplicationContext().getBean(boReference);
		if(boServiceObject != null)
		{
		    Class<?> boServiceClass = boServiceObject.getClass();
		    Class prmClass = Map.class;
		    if(paramClassType != null)
		    {
			prmClass = paramClassType;
		    }
		    Class<?>[] classArray = new Class<?>[] { prmClass };
		    Method methodToCall = boServiceClass.getMethod(boMethod, classArray);
		    if(methodToCall != null)
		    {
			Object result = methodToCall.invoke(boServiceObject, inputMap);
			return (Map<String, Object>) result;
		    }
		}
	    }
	}
	catch(Exception e)
	{
	    Log.getInstance().error(e,
		    "[LocalServiceCaller.executeLocalMethod] Error in method execution of the Local Method Caller");
	    throw new BaseException(e);
	}
	return null;
    }
}
