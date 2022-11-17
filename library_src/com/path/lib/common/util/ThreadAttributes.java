package com.path.lib.common.util;

import java.util.HashMap;
import java.util.Map;
/**
 * 
 * Copyright 2015, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * @author: deniskhaddad
 *
 * ThreadAttributes.java used to Add Custom Attributes to Running Thread
 */
public class ThreadAttributes
{
    private static ThreadLocal<Map<String, String>> threadAttrs = new ThreadLocal<Map<String, String>>()
    {
	@Override
	protected Map<String, String> initialValue()
	{
	    return new HashMap<String, String>();
	}
    };

    public static String get(String key)
    {
	return threadAttrs.get().get(key);
    }

    public static void set(String key, String value)
    {
	threadAttrs.get().put(key, value);
    }
    
    /**
     * Method to remove the thread attributes upon stop/undeloy of the application not to cause memry leak
     */
    public static void removeThread()
    {
	threadAttrs.remove();
    }
}
