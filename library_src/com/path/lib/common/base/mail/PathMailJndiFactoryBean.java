package com.path.lib.common.base.mail;

import java.util.Properties;

import javax.mail.Session;

import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.jndi.JndiObjectFactoryBean;

import com.path.lib.common.util.StringUtil;
import com.path.lib.log.Log;

/**
 * 
 * Copyright 2013, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author: marwanmaddah
 * 
 *          PathMailJndiFactoryBean.java used to manage path Mail JNDI factory
 *          Bean
 */
public class PathMailJndiFactoryBean extends JndiObjectFactoryBean
{

    @Override
    protected Object lookupWithFallback()
    {
	Object mailSession = null;
	try
	{
	    /**
	     * get the value of the needed property in case is exists in PathServices.properties 
	     */
	    Properties prop = PropertiesLoaderUtils.loadAllProperties("PathServices.properties");
	    String mailJndiName = prop.getProperty(getJndiName());
	    if(!StringUtil.nullToEmpty(mailJndiName).isEmpty())
	    {
		setJndiName(mailJndiName.trim());
	    }
	    mailSession = super.lookupWithFallback();
	}
	catch(Exception ex)
	{
	    Log.getInstance().warning("JNDI lookup failed - returning specified default object instead");
	}
	finally
	{
	    if(mailSession == null)
	    {
		mailSession = Session.getInstance(new Properties());
	    }
	}
	return mailSession;
    }

}
