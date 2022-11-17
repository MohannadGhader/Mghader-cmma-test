package com.path.lib.remote;

import static java.text.MessageFormat.format;

import java.io.Serializable;
import java.util.logging.Level;

import org.aopalliance.intercept.MethodInvocation;
import org.softamis.cluster4spring.rmi.RmiUrlListProxyFactoryBean;
import org.softamis.cluster4spring.rmi.support.RmiEndpoint;
import org.softamis.cluster4spring.support.ServiceMoniker;
import org.softamis.cluster4spring.support.provider.UrlListEndpointProvider;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.remoting.RemoteLookupFailureException;

import com.path.lib.log.Log;

public class PathRmiListProxyFactoryBean extends RmiUrlListProxyFactoryBean<ServiceMoniker>
{
    // specify the caching refresh time in seconds when the caching is enabled in case the technique is cluster
    private Integer cacheRefreshTime;
    
    //logger
    private static Log log = Log.getInstance();
 
    public PathRmiListProxyFactoryBean()
    {
	super();
    }
    
    @Override
    protected UrlListEndpointProvider<RmiEndpoint<ServiceMoniker>, ServiceMoniker> createEndpointProvider()
    {
	PathUrlListEndpointProvider result = new PathUrlListEndpointProvider(cacheRefreshTime);
	return result;
    }

    @Override
    protected RmiEndpoint<ServiceMoniker> obtainEndpointToExecute() throws RemoteLookupFailureException
    {
	    RmiEndpoint<ServiceMoniker> result = null;
	    try
	    {
	      result = fEndpointProvider.getEndpoint(fRemoteInvocationFactory, fEndpointFactory, fBeanName);
	      //[PathRemoting] logging if log level ALL
	      if (log != null && log.getLogDriver() != null && Level.ALL.equals(log.getLogDriver().getLevel()))
	      {
	        Class serviceInterface = getServiceInterface();
	        String name = serviceInterface.getName();
	        ServiceMoniker serviceInfo = result.getServiceInfo();
	        String protocol = getProtocol();
	        String message = format("{0} endpoint selected to invocation -  bean [{1}] on URL [{2}] with interface [{3}]",
	                                protocol, fBeanName, serviceInfo, name);
	        log.info(message);
	      }
	    }
	    catch (RemoteAccessException e)
	    {
	      throwRemoteLookupFailureException(e);
	    }
	    catch (Throwable ex)
	    {
	      throwRemoteLookupFailureException(ex);
	    }
	    return result;
    }

    /* (non-Javadoc)
     * @see org.softamis.cluster4spring.support.RemoteClientInterceptor#markServiceInvalid(org.softamis.cluster4spring.support.Endpoint)
     */
    @Override
    protected void markServiceInvalid(RmiEndpoint<ServiceMoniker> aServiceEndpoint)
    {
	//[PathRemoting] logging if log level ALL
	if (log != null && log.getLogDriver() != null && Level.ALL.equals(log.getLogDriver().getLevel()))
	{
	    ServiceMoniker serviceInfo = aServiceEndpoint.getServiceInfo();
	    String protocol = getProtocol();
	    String message = format("Unable to invoke {0} service [{1}] on [{2}] - marking URL invalid", protocol, fBeanName,
	                              serviceInfo);
	    log.info(message);
	}

	fEndpointProvider.markInvalid(fBeanName, aServiceEndpoint);
    }

    /* (non-Javadoc)
     * @see org.softamis.cluster4spring.support.RemoteClientInterceptor#handleRemoteConnectFailure(org.aopalliance.intercept.MethodInvocation, java.lang.Exception, org.softamis.cluster4spring.support.Endpoint)
     */
    @Override
    protected Object handleRemoteConnectFailure(MethodInvocation aInvocation, Exception aException, RmiEndpoint aServiceEndpoint)
	    throws Throwable
    {
	   // first, we mark failed endpoint invalid
	    Serializable serviceInfo = aServiceEndpoint.getServiceInfo();
	    markServiceInvalid(aServiceEndpoint);

	    Object result = null;
	    if (fRefreshEndpointsOnConnectFailure) // we need to refresh on failure
	    {
		//[PathRemoting] logging if log level ALL
		if (log != null && log.getLogDriver() != null && Level.ALL.equals(log.getLogDriver().getLevel()))
		{
		    String protocol = getProtocol();
		    String message = format("Could not connect to {0} service [{1}] on [{2}] - refreshing and retrying", protocol,
		                                fBeanName, serviceInfo);
		    log.debug(message);
		}

	      // here we refresh provider and retry
	      result = refreshAndRetry(aInvocation);
	    }
	    else if (fSwitchEndpointOnFailure) // we need to switch to another enpoint on failure
	    {
		//[PathRemoting] logging if log level ALL
		if (log != null && log.getLogDriver() != null && Level.ALL.equals(log.getLogDriver().getLevel()))
		{
		    String protocol = getProtocol();
		    String message = format("Could not connect to {0} service [{1}] on [{2}] - switching and retrying", protocol,
		                                fBeanName, serviceInfo);
		    log.debug(message);
		}
	      // since original endpoint already marked as invalid, we need to simply retry
	      result = retry(aInvocation);
	    }
	    else // failed, simply rethrow original exception
	    {
	      throw aException;
	    }
	    return result;
    }

    /**
     * @return the cacheRefreshTime
     */
    public Integer getCacheRefreshTime()
    {
        return cacheRefreshTime;
    }

    /**
     * @param cacheRefreshTime the cacheRefreshTime to set
     */
    public void setCacheRefreshTime(Integer cacheRefreshTime)
    {
        this.cacheRefreshTime = cacheRefreshTime;
    }
    
    
}
