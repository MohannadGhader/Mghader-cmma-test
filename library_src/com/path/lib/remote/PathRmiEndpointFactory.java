package com.path.lib.remote;

import static java.text.MessageFormat.format;

import java.rmi.Remote;
import java.util.logging.Level;

import org.softamis.cluster4spring.rmi.support.RmiEndpoint;
import org.softamis.cluster4spring.rmi.support.RmiEndpointFactory;
import org.softamis.cluster4spring.support.ServiceMoniker;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.remoting.support.RemoteInvocationFactory;

import com.path.lib.log.Log;

public class PathRmiEndpointFactory extends RmiEndpointFactory<ServiceMoniker>
{

    //logger
    private static Log log = Log.getInstance();

    @Override
    public RmiEndpoint<ServiceMoniker> createServiceEndpoint(RemoteInvocationFactory aFactory, String aBeanName,
	    ServiceMoniker aServiceInfo) throws RemoteAccessException
    {
	RmiEndpoint result = null;
	try
	{
	    // first we try to locate RMI stub for remote service using given
	    // service info

	    Remote remote = obtainRemoteStub(aBeanName, aServiceInfo);

	    // if remote is located, we create corresponding endpoint for it
	    result = createRmiEndpoint(aFactory, aBeanName, aServiceInfo, remote);
	}
	catch(Exception e)
	{
	    String message = format("Unable to obtain remote for bean [{0}] with URL [{1}]", aBeanName, aServiceInfo);
	    /* [PathSolutions] logging when log level is all */
	    if(log != null && log.getLogDriver() != null && Level.ALL.equals(log.getLogDriver().getLevel()))
	    {
		log.error(e, message);
	    }
	    throw new RemoteAccessException(message, e);
	}
	return result;
    }

}
