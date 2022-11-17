package com.path.lib.remote;

import org.springframework.remoting.rmi.RmiRegistryFactoryBean;

import com.path.lib.log.Log;
/**
 * 
 * Copyright 2011, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * RegistryRmi.java used to provide Registry object to the RMI remoting technique
 */
public class RegistryRmi extends RmiRegistryFactoryBean
{
    Log log = Log.getInstance();
    /**
     * set the port of the registry according to the port in the properties file for corresponding service , 
     * Used for RMI mechanism, defined in applicationBOExporters.xml
     */
    @Override
    public void setPort(int port)
    {
	try
	{
	    super.setPort(port);
	}
	catch(Exception e)
	{
	    log.error(e, "Error in setting remote port from property file(PathRemoting.properties key rmi.registry.port), ");
	    super.setPort(port);
	}
    }
}
