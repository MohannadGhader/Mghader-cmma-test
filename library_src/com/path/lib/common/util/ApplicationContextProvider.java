package com.path.lib.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 
 * This class provides an application-wide access to the
 * 
 * Spring ApplicationContext! The ApplicationContext is
 * 
 * injected in a static method of the class "AppContext".
 * 
 * 
 * 
 * Use AppContext.getApplicationContext() to get access
 * 
 * to all Spring Beans.
 * 
 * 
 */

public class ApplicationContextProvider implements ApplicationContextAware
{
    private static ApplicationContext ctx;
    // dummy object to apply concurrent access from differetn threads, which is
    // not the case of single spring web application
    private static Object lock = new Object(); 
    public void setApplicationContext(ApplicationContext ctx) throws BeansException
    {
	synchronized(lock)
	{
	    ApplicationContextProvider.ctx = ctx;
	}
    }

    /**
     * Get access to the Spring ApplicationContext from everywhere in your
     * Application.
     * 
     * @return
     */
    public static ApplicationContext getApplicationContext()
    {
	return ctx;
    }

}