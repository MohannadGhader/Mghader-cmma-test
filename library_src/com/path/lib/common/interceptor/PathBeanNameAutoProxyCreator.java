package com.path.lib.common.interceptor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

/**
 * 
 * Copyright 2014, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author: deniskhaddad
 * 
 *          PathBeanNameAutoProxyCreator.java used to add additional bean ids to
 *          the list of beans that are specified as transaction enabled, needed
 *          so that some beans list to be set as common among all projects
 */
@SuppressWarnings("serial")
public class PathBeanNameAutoProxyCreator extends BeanNameAutoProxyCreator
{
    /**
    * override the map earlyProxyReferences in the same way as defined in AbstractAutoProxyCreator.getEarlyBeanReference() in Spring 5
    */
    private final Map<Object, Object> earlyProxyReferences = new ConcurrentHashMap<>(16);
    
    private String[] addBeanNames;

    public void setAddBeanNames(String... addBeanNames)
    {
	this.addBeanNames = addBeanNames;
    }
    @Override
    public void setBeanNames(String[] beanNames)
    {
	StringBuffer listBeans = new StringBuffer();
	for(String mappedName : beanNames)
	{
	    if(listBeans.length() > 0)
	    {
		listBeans.append(",");
	    }
	    listBeans.append(StringUtils.trimWhitespace(mappedName));
	}
	if(this.addBeanNames != null)
	{
	    for(String mappedName : this.addBeanNames)
	    {
		if(listBeans.length() > 0)
		{
		    listBeans.append(",");
		}
		listBeans.append(StringUtils.trimWhitespace(mappedName));
	    }
	}
	super.setBeanNames(listBeans.toString().split(","));

    }
    /**
     * override the postProcessAfterInitialization to be able to change the condition from if (this.earlyProxyReferences.remove(cacheKey) != bean) {
     * currently used in Spring 5 to if(!this.earlyProxyReferences.containsKey(cacheKey)) to become the same as Spring 4.3. Without this change 2 transactionals beans defined in commmonTransBeans
     * and added to PathBPMInterceptor will not stay transactional, and genericDao.rollbackTransaction() will fail with throwing a NullPointerException on getConnection().  
     */
    @Override
    public Object postProcessAfterInitialization(@Nullable Object bean, String beanName)
    {
	if(bean != null)
	{
	    Object cacheKey = getCacheKey(bean.getClass(), beanName);
	    if(!this.earlyProxyReferences.containsKey(cacheKey))
	    {
		return wrapIfNecessary(bean, beanName, cacheKey);
	    }
	}
	return bean;
    }

    /**
     * override this function because we need to use the earlyProxyReferences in this class. the code of this method is preserved the same as AbstractAutoProxyCreator.getEarlyBeanReference() in Spring 5
     */
    @Override
    public Object getEarlyBeanReference(Object bean, String beanName)
    {
	Object cacheKey = getCacheKey(bean.getClass(), beanName);
	this.earlyProxyReferences.put(cacheKey, bean);
	return wrapIfNecessary(bean, beanName, cacheKey);
    }
	
}
