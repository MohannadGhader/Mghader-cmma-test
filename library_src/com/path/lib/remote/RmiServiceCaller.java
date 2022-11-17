package com.path.lib.remote;

import java.util.ArrayList;
import java.util.List;

import org.softamis.cluster4spring.rmi.RmiUrlListProxyFactoryBean;
import org.softamis.cluster4spring.rmi.support.RmiEndpointFactory;
import org.softamis.cluster4spring.support.ServiceMoniker;
import org.softamis.cluster4spring.support.invocation.ShuffleEndpointSelectionPolicy;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

import com.path.lib.log.Log;

/**
 * 
 * Copyright 2015, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author: deniskhaddad
 * 
 *          RmiServiceCaller.java used to call services through RMI
 */
public final class RmiServiceCaller
{
    /**
     * private constructor to prevent the class being instantiated since all
     * methods are static
     */
    private RmiServiceCaller()
    {
	Log.getInstance().error("This Class is utility class cannot be instantiated");
    }
    /**
     * Method to return Service IUnterface through RMI
     * 
     * @param serviceUrl service URL through RMI example
     *            rmi://lblm7-dhaddad:1599/loginBOService where loginBOService
     *            is remoteservice name (remoteServiceName) defined in BOContext of the calling application
     * @param theInterface Interface Object of the service
     * @return
     */
    public static Object returnRmiInterface(String serviceUrl, Class theInterface) throws Exception
    {
      // in case of Cluster
      if(serviceUrl.indexOf(",") > 0)
      {
                  RmiUrlListProxyFactoryBean rmiUrlListProxyFactoryBean = new RmiUrlListProxyFactoryBean();
                  rmiUrlListProxyFactoryBean.setServiceInterface(theInterface);
                  rmiUrlListProxyFactoryBean.setRefreshEndpointsOnConnectFailure(true);
                  rmiUrlListProxyFactoryBean.setRefreshEndpointsOnStartup(true);
                  rmiUrlListProxyFactoryBean.setSwitchEndpointOnFailure(false);
                  rmiUrlListProxyFactoryBean.setEndpointFactory(new RmiEndpointFactory());
                  rmiUrlListProxyFactoryBean.setEndpointSelectionPolicy(new ShuffleEndpointSelectionPolicy());

                  // fill the cluster service urls which are defined comma separated
                  // in PathREmoting.properties
                  String[] listClusters = serviceUrl.trim().split(",");
                  List<ServiceMoniker> theClusterSericeUrls = new ArrayList<ServiceMoniker>();
                  for (String clusterUrl : listClusters) 
                  {
                        ServiceMoniker serviceMoniker = new ServiceMoniker(clusterUrl.trim());
                        theClusterSericeUrls.add(serviceMoniker);
                  }
                  rmiUrlListProxyFactoryBean.setServiceURLs(theClusterSericeUrls);

                  rmiUrlListProxyFactoryBean.afterPropertiesSet();
                  Object objRef = rmiUrlListProxyFactoryBean.getObject();
                  return objRef;
      }
      else
      {
            RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
            rmiProxyFactoryBean.setServiceUrl(serviceUrl);
            rmiProxyFactoryBean.setServiceInterface(theInterface);
            rmiProxyFactoryBean.setRefreshStubOnConnectFailure(true);
            rmiProxyFactoryBean.setLookupStubOnStartup(false);
            rmiProxyFactoryBean.afterPropertiesSet();
            Object objRef = rmiProxyFactoryBean.getObject();
            return objRef;
      }
    }

    /**
     * Method that returns the reference to a service considering Clustered
     * environment
     * 
     * @param rmiServerUrl service URL could be comma separated example
     *            rmi://lblm1-dhaddad:1599/,rmi://lblm1-dhaddad:1799/
     * @param theInterface Interface Class
     * @param serviceName service Name
     * @return reference to Object Class
     * @throws Exception
     */
    public static Object returnRmiInterface(String rmiServerUrl, Class theInterface, String serviceName)
	    throws Exception
    {
	// in case of Cluster
	String coreServiceURL = rmiServerUrl;
	String[] serviceUrls = null;

	if(coreServiceURL != null)
	{
	    // in case of cluster Environment
	    if(coreServiceURL.indexOf(",") > 0)
	    {
		serviceUrls = coreServiceURL.split(",");
		coreServiceURL = "";
		for(int i = 0; i < serviceUrls.length; i++)
		{
		    // add comma
		    if(i > 0)
		    {
			coreServiceURL = coreServiceURL.concat(",");
		    }
		    // add the service NAme to the URL of each cluster Node
		    if(!serviceUrls[i].endsWith("/"))
		    {
			coreServiceURL = coreServiceURL.concat(serviceUrls[i] + "/" + serviceName);
		    }
		    else
		    {
			coreServiceURL = coreServiceURL.concat(serviceUrls[i] + serviceName);
		    }
		}
	    }
	    else
	    {
		// add the service NAme to the URL of each cluster Node
		if(!coreServiceURL.endsWith("/"))
		{
		    coreServiceURL = coreServiceURL.concat("/");
		}
		coreServiceURL = coreServiceURL.concat(serviceName);
	    }

	    Object objRef = returnRmiInterface(coreServiceURL, theInterface);
	    return objRef;
	}
	else
	{
	    throw new Exception("rmiServerUrl provided for returnRmiInterface is null");
	}
    }
}
