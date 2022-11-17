package com.path.lib.remote;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.path.lib.common.util.ApplicationContextProvider;

/**
 * 
 * Copyright 2011, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * PathServiceBeanFactory.java used to instantiate beans on service side.
 */
public class PathServiceBeanFactory
{	
	

	/**
	 * This method should be used when a bean has to be created with default constructor.
	 * @param implClassName implementation class name of the bean
	 * @param interfaceName interface class for the bean
	 * @param remoteServiceName remote service name for communication
	 * @return
	 * @throws Exception 
	 */
	public Object getInstance(String implClassName, String interfaceName, String remoteServiceName)
			throws Exception
	{
		return getInstance(implClassName, interfaceName, remoteServiceName, null);
	}

		
	/**
	 * This method should be used when an integration bean has to be created without default constructor.
	 * @param implClassName
	 * @param interfaceName
	 * @param remoteServiceName
	 * @return
	 * @throws Exception
	 */
	public Object getIntegInstance(String implClassName, String interfaceName, String remoteServiceName)
	throws Exception
        {
	    	// the getIntegInstance used in case of PathServiceBeanFactory, that mean we are in imal_core_services or in imal_core_intergration,
	    	// and in this case we need to instantiate the bean locally
	    	return getInstance(implClassName, interfaceName, remoteServiceName, null);
        }
	
			
	/**
	 * This method should be used when a bean has to be created with constructor arguments.
	 * @param implClassName fully qualified name of class.(has to be provided when
	 *          instance has to be created locally)
	 * @param interfaceName interface name (has to be provided when instance has to be
	 *            created remotely)
	 * @param remoteServiceName (has to be provided when instance has to be created remotely)
	 * @param constructorArgs variable arguments if bean is having any constructor
	 * @return Instance for the declared bean class
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public Object getInstance(String implClassName, String interfaceName, String remoteServiceName, Object... constructorArgs)
			throws Exception
	{		
		
		if(ApplicationContextProvider.getApplicationContext().containsBean(implClassName))
		{
		   return ApplicationContextProvider.getApplicationContext().getBean(implClassName);
		}
		if(constructorArgs != null)
		{
			Constructor<?> constructor = Class.forName(implClassName).getConstructor(getParameterTypes(constructorArgs));
			return getCastedObject(BeanUtils.instantiateClass(constructor, constructorArgs));
		}
		return getCastedObject(BeanUtils.instantiate(Class.forName(implClassName)));
		
	}

	/**
	 * This method should be used when an integration bean has to be created with default constructor.
	 * @param implClassName
	 * @param interfaceName
	 * @param remoteServiceName
	 * @param constructorArgs
	 * @return
	 * @throws Exception
	 */
	public Object getIntegInstance(String implClassName, String interfaceName, String remoteServiceName, Object... constructorArgs)
			throws Exception
	{
	    	// the getIntegInstance used in case of PathServiceBeanFactory, that mean we are in imal_core_services or in imal_core_intergration,
	    	// and in this case we need to instantiate the bean locally
	    	return getInstance(implClassName, interfaceName, remoteServiceName, constructorArgs);
	}
	
	/**
	 * 
	 * Used to cast the instance provided to its corresponding time.
	 * 
	 * @param objAny
	 * @return
	 */
	private static Object getCastedObject(Object objAny)
	{
		Class eClass = objAny.getClass();
		final Class clType = eClass;
		return clType.cast(objAny);
	}

	private Class[] getParameterTypes(Object... arguments)
	{
		Class[] parameterType = null;
		List<Class> listClass = new ArrayList<Class>();
		if(arguments != null)
		{
			for(int i = 0; i < arguments.length; i++)
			{
				if(arguments[i] != null && arguments[i].getClass().isArray())
				{
					Object[] indexParams = (Object[]) arguments[i];
					for(int j = 0; j < indexParams.length; j++)
					{
					    listClass.add(indexParams[j].getClass());
					}
					break;
				}
				listClass.add(arguments[i].getClass());
			}
			parameterType = listClass.toArray(new Class[listClass.size()]);
		}

		return parameterType;
	}
}
