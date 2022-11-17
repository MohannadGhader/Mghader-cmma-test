package com.path.lib.remote;

import static java.text.MessageFormat.format;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.softamis.cluster4spring.support.Endpoint;
import org.softamis.cluster4spring.support.EndpointFactory;
import org.softamis.cluster4spring.support.ServiceMoniker;
import org.softamis.cluster4spring.support.provider.UrlListEndpointProvider;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.remoting.support.RemoteInvocationFactory;

import com.path.lib.log.Log;

public class PathUrlListEndpointProvider extends UrlListEndpointProvider
{
    // specify the caching refresh time in seconds when the caching is enabled in case the technique is cluster
    private Integer cacheRefreshTime;
    
    //logger
    private static Log log = Log.getInstance();
    
    private Long lastCacheRefreshTime = System.currentTimeMillis();
    
    public PathUrlListEndpointProvider(Integer cacheRefreshTime)
    {
	super();
	this.cacheRefreshTime = cacheRefreshTime;
    }
    
    @Override
    protected List getServiceEndpointsList(RemoteInvocationFactory aRemoteInvocationFactory,
	    EndpointFactory aEndpointFactory, String aBeanName) throws RemoteAccessException
    {
	    List<Endpoint> result = null;
	    // depending on mode, we either return cached list of endpoints or discover them for this call
	    if (fCacheEndpoints) 
	    {
		//in case the cacheRefreshTime is not zero and the refresh() is not called since cacheRefreshTime in seconds 
		if(lastCacheRefreshTime != null && cacheRefreshTime > 0
			&& (System.currentTimeMillis() - lastCacheRefreshTime) > cacheRefreshTime * 1000)
		{
		    int cacheSize = fEndpointsCache.size();
		    int servicesSize = fServiceMonikers.size();
		    //we should not refresh the cache in case the cached endpoints is equal to the initial number of rmi urls defined in pathremporting 
		    if(cacheSize < servicesSize)
		    {
			refresh(aRemoteInvocationFactory, aEndpointFactory, aBeanName);
			lastCacheRefreshTime = System.currentTimeMillis();
		    }
		    
		}
		result = getCachedEndpoints(aRemoteInvocationFactory, aEndpointFactory, aBeanName);
	    }
	    else
	    {
	      result = doRefreshServiceEndpointsList(aRemoteInvocationFactory, aEndpointFactory, aBeanName);
	    }
	    
	    
	    return result;
    }

    @Override
    protected List doRefreshServiceEndpointsList(RemoteInvocationFactory aRemoteInvocationFactory,
	    EndpointFactory aEndpointFactory, String aBeanName) throws RemoteAccessException
    {

	    int servicesSize = fServiceMonikers.size();
	    List result = new ArrayList(servicesSize);

	    // simply walk over list of remote service locations and try to create endpoint for
	    // every location
	    for (ServiceMoniker serviceInfo : (ArrayList<ServiceMoniker>)fServiceMonikers)
	    {
		//[PathRemoting] logging if log level ALL
	        if (log != null && log.getLogDriver() != null && Level.ALL.equals(log.getLogDriver().getLevel()))
		{
	            String message =
		                format("Starting endpoint creation. Bean Name: [{0}]. Service Info: [{1}]", aBeanName, serviceInfo);
		    log.trace(message);
		}
	      
	      Endpoint<Serializable> endpoint = null;
	      try
	      {
	        endpoint = doCreateServiceEndpoint(aRemoteInvocationFactory, aEndpointFactory, aBeanName, serviceInfo);
	      }
	      catch (RemoteAccessException e)
	      {
		 //[PathRemoting] logging if log level ALL
		 if (log != null && log.getLogDriver() != null && Level.ALL.equals(log.getLogDriver().getLevel()))
		 {
		     String message = format("Unable to create service endpoint. Bean Name: [{0}]. Service Info: [{1}]", aBeanName,
                             serviceInfo);
		     log.error(e,message);
		 }
	      }
	      if (endpoint != null)
	      {
	        result.add(endpoint);
	      }
	    }
	    if (result.isEmpty())
	    {
	      String message =
	              format("Unable to determine at least one service endpoint for server. Bean Name: [{0}]", aBeanName);
	      throw new RemoteAccessException(message);
	    }
	    return result;
    }

    @Override
    public Endpoint getEndpoint(RemoteInvocationFactory aRemoteInvocationFactory, EndpointFactory aEndpointFactory,
	    String aBeanName) throws RemoteAccessException
    {
	    //[PathRemoting] logging if log level ALL
	    if (log != null && log.getLogDriver() != null && Level.ALL.equals(log.getLogDriver().getLevel()))
	    {
		String message = format("Starting obtaining service endpoint. Bean Name: [{0}]", aBeanName);
		log.trace(message);
	    }
	    
	    // first, we try to obtain list of available endpoints
	    List<Endpoint> serviceEndpoints = getServiceEndpointsList(aRemoteInvocationFactory, aEndpointFactory, aBeanName);

	    if (serviceEndpoints == null || serviceEndpoints.isEmpty())
	    {
	      String message = format("There are no service endpoints in the list available. Bean Name: [{0}]", aBeanName);	
	      //[PathRemoting] logging if log level ALL
	      if (log != null && log.getLogDriver() != null && Level.ALL.equals(log.getLogDriver().getLevel()))
	      {
		 log.error(message);
	      }
	      throw new RemoteAccessException(message);
	    }

	    // if we are there, we have endpoints to invoke. So, now we had to select exact one that
	    // will be actually invoked. We delegate this to EndpointSelectionPolicy
	    Endpoint result = null;
	    synchronized (this)
	    {
	      result = fEndpointSelectionPolicy.selectServiceEndpoint(serviceEndpoints);
	    }

	    //[PathRemoting] logging if log level ALL
	    if (log != null && log.getLogDriver() != null && Level.ALL.equals(log.getLogDriver().getLevel()))
	    {
		if (result == null)
		{
		    String message = format("No endpoint selected. Bean Name: [{0}]", aBeanName);
		    log.trace(message);
		}
		else
		{
		    String message = format("Endpoint selected. Bean Name: [{0}] Endpoint Info: [{1}]", aBeanName, result.getServiceInfo());
		    log.trace(message);
		}  
	    }
	    return result;
    }
}
