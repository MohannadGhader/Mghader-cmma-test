package com.path.lib.remote;

import java.rmi.registry.Registry;

import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.remoting.rmi.RmiServiceExporter;

import com.path.lib.common.util.StringUtil;
/**
 * 
 * Copyright 2011, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 *
 * PathBeanExporter.java used to expose the beans for remote access according to given technique
 */
public class PathBeanExporter
{
    Registry pathRegistry;
    String remoteTechnicque;
    String beanAvailability;

    public void setRemoteTechnicque(String remoteTechnicque)
    {
	//remove JSON {} details if they exists when cluster technique is enabled
	if(StringUtil.nullToEmpty(remoteTechnicque).trim().startsWith(RemoteConstants.CLUSTER_RMI_REMOTING))
	{
	    remoteTechnicque = RemoteConstants.CLUSTER_RMI_REMOTING;
	}
        this.remoteTechnicque = remoteTechnicque;
    }

    public void setBeanAvailability(String beanAvailability)
    {
        this.beanAvailability = beanAvailability;
    }

    public void setPathRegistry(Registry pathRegistry)
    {
	this.pathRegistry = pathRegistry;
    }

    /**
     * This method will export beans so that can be available remotely. And
     * technique will be used based on property file flag.
     * 
     * @param <T>
     * @param serviceName name of the service to be exposed with
     * @param service service name that is used for expose
     * @param serviceInterface interface class for exposed service
     * @return
     * @throws Exception
     */

    public <T> T export(String serviceName, Object service, String serviceInterface) throws Exception
    {
	
	if(RemoteConstants.REMOTE_CONF.equals(StringUtil.nullToEmpty(beanAvailability).trim()))
	{
	    if(RemoteConstants.RMI_REMOTING.equals(StringUtil.nullToEmpty(remoteTechnicque).trim())
		    ||RemoteConstants.CLUSTER_RMI_REMOTING.equals(StringUtil.nullToEmpty(remoteTechnicque).trim()))
	    {
		RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
		rmiServiceExporter.setServiceName(serviceName);
		rmiServiceExporter.setService(service);
		rmiServiceExporter.setServiceInterface(Class.forName(serviceInterface));
		rmiServiceExporter.setRegistry(pathRegistry);
		rmiServiceExporter.setInterceptors(new Object[]{new PathRmiInterceptor()});
		// rmiServiceExporter.prepare();
		// no need to call afterPropertiesSet() because it will be implicitely called from RmiServiceExporter that implements the org.springframework.beans.factory.InitializingBean. when keeping it uncommented  the afterPropertiesSet() will be called twice on server startup
		// rmiServiceExporter.afterPropertiesSet();
		return getCastedObject(rmiServiceExporter);
	    }
	    else if(RemoteConstants.HESSIEN_REMOTING.equals(remoteTechnicque))
	    {
		HessianServiceExporter hessianServiceExporter = new HessianServiceExporter();
		hessianServiceExporter.setService(service);
		hessianServiceExporter.setServiceInterface(Class.forName(serviceInterface));
		//hessianServiceExporter.prepare();
		hessianServiceExporter.afterPropertiesSet();
		return getCastedObject(hessianServiceExporter);
	    }
	}
	return null;
    }

    private <T> T getCastedObject(Object objAny)
    {
	Class eClass = objAny.getClass();
	final Class<T> clType = eClass;
	return clType.cast(objAny);
    }
	
}
