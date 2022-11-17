package com.path.lib.remote;

import java.util.Properties;

import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.path.lib.common.exception.BaseException;
import com.path.lib.log.Log;
/**
 * 
 * Copyright 2011, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 *
 * PathRemotingPropertiesProvider.java used to provide the properties for remoting technique from properties file
 */
public final class PathRemotingPropertiesProvider
{
    private static Properties pathRemotingproperties;
    static Log log = Log.getInstance();
    static
    {
	try
	{
	    pathRemotingproperties = PropertiesLoaderUtils.loadAllProperties("PathRemoting.properties");
	}
	catch(Exception e)
	{
	    log.error(e,"error in PathRemotingPropertiesProvider PathRemoting.properties not found");
	}
    }
    /**
     * Private constructor to prevent class from instantiation
     */
    private PathRemotingPropertiesProvider()
    {
	log.warning("This class is utility and cannot be instantiated");
    }
    /**
     * 
     * Used for return property value for given key
     * 
     * @param key
     * @return
     * @throws BaseException
     */
    public static String getPropertyValue(String key) throws BaseException
    {
	if(PathRemotingPropertiesProvider.pathRemotingproperties.containsKey(key))
	{
	    return (String) PathRemotingPropertiesProvider.pathRemotingproperties.get(key);
	}
	else
	{
	    throw new BaseException("Key not found in property file Key : " + key);
	}
    }
}
