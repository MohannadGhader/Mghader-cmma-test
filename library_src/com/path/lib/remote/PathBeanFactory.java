package com.path.lib.remote;

import java.lang.reflect.Constructor;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.softamis.cluster4spring.rmi.RmiUrlListProxyFactoryBean;
import org.softamis.cluster4spring.rmi.support.RmiEndpointFactory;
import org.softamis.cluster4spring.support.EndpointSelectionPolicy;
import org.softamis.cluster4spring.support.ServiceMoniker;
import org.softamis.cluster4spring.support.invocation.DefaultEndpointSelectionPolicy;
import org.softamis.cluster4spring.support.invocation.LastAccessTimeEndpointSelectionPolicy;
import org.softamis.cluster4spring.support.invocation.ShuffleEndpointSelectionPolicy;
import org.springframework.beans.BeanUtils;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

import com.path.bo.common.MessageCodes;
import com.path.lib.common.exception.BOException;
import com.path.lib.common.util.ApplicationContextProvider;
import com.path.lib.common.util.StringUtil;
import com.path.lib.log.Log;

/**
 * 
 * Copyright 2011, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * 
 * PathBeanFactory.java used to instantiate beans at client side according to
 * remoting techniques
 */
public class PathBeanFactory
{
    //logger
    private static Log log = Log.getInstance();
    
    //the bean availability
    private String beanAvailability;

    //the service url defined in pathRemoting
    private String serviceURL;

    //the integration bean availability
    private String integBeanAvailability;
    
    //the integration service url defined in pathRemoting
    private String integServiceURL;
    
    // specify the technique to use for every application (rmi, cluster, hessien)
    private String technique;
    
    // specify if the caching is enabled in case the technique is cluster
    private Boolean clusterCacheEnabled = Boolean.TRUE;
    
    // specify the caching refresh time in seconds when the caching is enabled in case the technique is cluster
    private Integer cacheRefreshTime = 600;
    
    // specify the endpoints selection policy in case the technique is cluster
    private EndpointSelectionPolicy selectionPolicy = new DefaultEndpointSelectionPolicy();
    
    public String getServiceURL()
    {
	return serviceURL;
    }

    public void setServiceURL(String serviceURL)
    {
	if(serviceURL != null)
	{
	    this.serviceURL = serviceURL.trim();
	}
    }

    public String getBeanAvailability()
    {
	return beanAvailability;
    }

    public void setBeanAvailability(String beanAvailability)
    {
	this.beanAvailability = beanAvailability;
    }
    
    public String getIntegBeanAvailability()
    {
        return integBeanAvailability;
    }

    public void setIntegBeanAvailability(String integBeanAvailability)
    {
        this.integBeanAvailability = integBeanAvailability;
    }

    public String getIntegServiceURL()
    {
        return integServiceURL;
    }

    public void setIntegServiceURL(String integServiceURL)
    {
	if(integServiceURL != null)
	{
	    this.integServiceURL = integServiceURL.trim();
	}  
    }
    
    
    /**
     * This method should be used when a bean has to be created with default
     * constructor.
     * 
     * @param implClassName
     * @param interfaceName
     * @param remoteServiceName
     * @return
     * @throws Exception
     */
    public Object getInstance(String implClassName, String interfaceName, String remoteServiceName) throws Exception
    {
	return getInstanceCommon(implClassName, interfaceName, remoteServiceName,beanAvailability,serviceURL,null);
    }
    
    /**
     * This method should be used when an integration bean has to be created with constructor
     * arguments.
     * @param implClassName
     * @param interfaceName
     * @param remoteServiceName
     * @return
     * @throws Exception
     */
    public Object getIntegInstance(String implClassName, String interfaceName, String remoteServiceName) throws Exception
    {
	if(StringUtil.isNotEmpty(integBeanAvailability) && StringUtil.isNotEmpty(integServiceURL))
	{    
	    return getInstanceCommon(implClassName, interfaceName, remoteServiceName,integBeanAvailability,integServiceURL,null);
	}
	else
	{
	    //In case the integBeanAvailability are not defined , this means we are not inside the portal application.
	    //so we are in the integration layer or the serices layer. and in this case we need to define the bean instance locally.
	    if(ApplicationContextProvider.getApplicationContext().containsBean(implClassName))
	    {
		return ApplicationContextProvider.getApplicationContext().getBean(implClassName);
	    }
	    return getCastedObject(BeanUtils.instantiate(Class.forName(implClassName)));
	}
	
    }

    /**
     * This method should be used when a bean has to be created with constructor
     * arguments.
     * 
     * @param implClassName fully qualified name of class.(has to be provided
     *            when instance has to be created locally)
     * @param interfaceName interface name (has to be provided when instance has
     *            to be created remotely)
     * @param remoteServiceName (has to be provided when instance has to be
     *            created remotely)
     * @param constructorArgs variable arguments if bean is having any
     *            constructor
     * @return
     * @throws Exception
     */
    public Object getInstance(String implClassName, String interfaceName, String remoteServiceName,
	    Object... constructorArgs) throws Exception
    {
	return getInstanceCommon(implClassName, interfaceName, remoteServiceName,beanAvailability,serviceURL,constructorArgs);
    }
    
    /**
     * This method should be used when an integration bean has to be created with constructor
     * arguments.
     * @param implClassName
     * @param interfaceName
     * @param remoteServiceName
     * @param constructorArgs
     * @return
     * @throws Exception
     */
    public Object getIntegInstance(String implClassName, String interfaceName, String remoteServiceName,
	    Object... constructorArgs) throws Exception
    {
	
	if(StringUtil.isNotEmpty(integBeanAvailability) && StringUtil.isNotEmpty(integServiceURL))
	{    
	    return getInstanceCommon(implClassName, interfaceName, remoteServiceName,integBeanAvailability,integServiceURL,constructorArgs);
	}
	else
	{
	    //In case the integBeanAvailability are not defined , this means we are not inside the portal application.
	    //so we are in the integration layer or the serices layer. and in this case we need to define the bean instance locally.
	    if(ApplicationContextProvider.getApplicationContext().containsBean(implClassName))
	    {
		return ApplicationContextProvider.getApplicationContext().getBean(implClassName);
	    }
	    if(constructorArgs != null)
	    {
		Constructor<?> constructor = Class.forName(implClassName).getConstructor(
			getParameterTypes(constructorArgs));
		return getCastedObject(BeanUtils.instantiateClass(constructor, constructorArgs));
	    }
	    return getCastedObject(BeanUtils.instantiate(Class.forName(implClassName)));
	}
    }
    
    
    /**
     * This is a common method used by getInstance() function
     * @param implClassName
     * @param interfaceName
     * @param remoteServiceName
     * @param beanAvailability
     * @param constructorArgs
     * @return
     * @throws Exception
     */
    private Object getInstanceCommon(String implClassName, String interfaceName, String remoteServiceName, String beanAvailability, String serviceURL, 
	    Object... constructorArgs) throws Exception
    {
	// check if bean has to be provided locally or remotely
	if(RemoteConstants.REMOTE_CONF.equals(StringUtil.nullToEmpty(beanAvailability).trim()))
	{
	    if(ApplicationContextProvider.getApplicationContext().containsBean(interfaceName))
	    {
		return getCastedObject(ApplicationContextProvider.getApplicationContext().getBean(interfaceName));
	    }
	    return getRemoteInstance(interfaceName, remoteServiceName,serviceURL);
	}
	else
	{
	    if(ApplicationContextProvider.getApplicationContext().containsBean(implClassName))
	    {
		return ApplicationContextProvider.getApplicationContext().getBean(implClassName);
	    }
	    if(constructorArgs != null)
	    {
		Constructor<?> constructor = Class.forName(implClassName).getConstructor(
			getParameterTypes(constructorArgs));
		return getCastedObject(BeanUtils.instantiateClass(constructor, constructorArgs));
	    }
	    return getCastedObject(BeanUtils.instantiate(Class.forName(implClassName)));
	}
    }

    /**
     * this is method will return a remote bean instance
     * @param beanName
     * @param remoteServiceName
     * @param serviceURL
     * @return
     * @throws Exception
     */
    private Object getRemoteInstance(String beanName, String remoteServiceName, String serviceURL) throws Exception
    {
	String remotingTechnique = RemoteConstants.HESSIEN_REMOTING;
	remotingTechnique = StringUtil.nullEmptyToValue(technique,PathRemotingPropertiesProvider.getPropertyValue("remoting.technique"));
	Class<?> cls = Class.forName(beanName);

	if(remotingTechnique.trim().equals(RemoteConstants.RMI_REMOTING))
	{
	    RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
	    rmiProxyFactoryBean.setServiceUrl(serviceURL + remoteServiceName);
	    rmiProxyFactoryBean.setServiceInterface(cls);
	    rmiProxyFactoryBean.setRefreshStubOnConnectFailure(true);
	    rmiProxyFactoryBean.setLookupStubOnStartup(false);
	    // rmiProxyFactoryBean.prepare();
	    rmiProxyFactoryBean.afterPropertiesSet();
	    return getCastedObject(rmiProxyFactoryBean.getObject());
	}
	else if(remotingTechnique.trim().equals(RemoteConstants.CLUSTER_RMI_REMOTING))
	{
	    PathRmiListProxyFactoryBean  rmiUrlListProxyFactoryBean = new PathRmiListProxyFactoryBean();
	    rmiUrlListProxyFactoryBean.setServiceInterface(cls);
	    rmiUrlListProxyFactoryBean.setCacheEndpoints(clusterCacheEnabled);
	    rmiUrlListProxyFactoryBean.setCacheRefreshTime(cacheRefreshTime);
	    rmiUrlListProxyFactoryBean.setRefreshEndpointsOnConnectFailure(true);
	    rmiUrlListProxyFactoryBean.setRefreshEndpointsOnStartup(true);
	    rmiUrlListProxyFactoryBean.setSwitchEndpointOnFailure(false);
	    rmiUrlListProxyFactoryBean.setEndpointFactory(new PathRmiEndpointFactory());
	    rmiUrlListProxyFactoryBean.setEndpointSelectionPolicy(selectionPolicy);
	    
	    // fill the cluster service urls which are defined comma separated in PathREmoting.properties
	    String[] listClusters =  serviceURL.trim().split(",");
	    List<ServiceMoniker> theClusterSericeUrls = new ArrayList<ServiceMoniker>();
	    for(String clusterUrl : listClusters)
	    {
		ServiceMoniker serviceMoniker  = new ServiceMoniker(clusterUrl.trim() + remoteServiceName);
		theClusterSericeUrls.add(serviceMoniker);
	    }
	    rmiUrlListProxyFactoryBean.setServiceURLs(theClusterSericeUrls);
	    
	    rmiUrlListProxyFactoryBean.afterPropertiesSet();
	    return getCastedObject(rmiUrlListProxyFactoryBean.getObject());
	}
	else
	// hessian
	{
	    HessianProxyFactoryBean hessianProxyFactoryBean = new HessianProxyFactoryBean();
	    hessianProxyFactoryBean.setServiceUrl(serviceURL + remoteServiceName);
	    hessianProxyFactoryBean.setServiceInterface(cls);
	    // hessianProxyFactoryBean.prepare();
	    hessianProxyFactoryBean.afterPropertiesSet();
	    return getCastedObject(hessianProxyFactoryBean.getObject());
	}
    }

    private static Object getCastedObject(Object objAny)
    {
	Class<? extends Object> eClass = objAny.getClass();
	final Class<? extends Object> clType = eClass;
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

    private EndpointSelectionPolicy returnEndpointSelectionPolicy(String selectionPolicy)
    {
	if("LAST".equals(selectionPolicy))
	{
	    return new LastAccessTimeEndpointSelectionPolicy();
	}
	if("RANDOM".equals(selectionPolicy))
	{
	    return new ShuffleEndpointSelectionPolicy();
	}
	return new DefaultEndpointSelectionPolicy();
    }
    
    public void setTechnique(String technique)
    {
        this.technique = technique;
        try
	{
	    if(StringUtil.isNotEmpty(technique))
	    {
		Pattern pattern = Pattern.compile("(.*?)(\\{(.*?)\\})");
		Matcher matcher = pattern.matcher(technique);
		if(matcher.find())
		{
		    String techniqueValue = matcher.group(1);
		    if(StringUtil.isNotEmpty(techniqueValue))
		    {
			this.technique = techniqueValue;
			String techniqueAttributes = matcher.group(2);
			if(StringUtil.isNotEmpty(techniqueAttributes))
			{
			    ObjectMapper mapper = new ObjectMapper();
			    JsonNode actualObj = mapper.readTree(techniqueAttributes);
			    JsonNode jsonNodeCache = actualObj.get("cache");
			    JsonNode jsonNodeCacheRefreshTime = actualObj.get("cacheRefreshTime");
			    JsonNode jsonNodeSelectionPolicy = actualObj.get("selectionPolicy");

			    if(jsonNodeCache != null)
			    {
				clusterCacheEnabled = jsonNodeCache.getBooleanValue();
			    }
			    if(jsonNodeCacheRefreshTime != null)
			    {
				cacheRefreshTime = jsonNodeCacheRefreshTime.getIntValue();
			    }
			    if(jsonNodeSelectionPolicy != null
				    && StringUtil.isNotEmpty(jsonNodeSelectionPolicy.getTextValue()))
			    {
				selectionPolicy = returnEndpointSelectionPolicy(jsonNodeSelectionPolicy.getTextValue());
			    }
			}
		    }
		}
	    }
	}
        catch(Exception e)
        {
            log.error(e, "error in setTechnique() during fetching technique attributes ");
        }
    }
}
