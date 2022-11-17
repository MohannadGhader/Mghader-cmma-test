package com.path.lib.common.util;

import org.codehaus.jackson.map.MapperConfig;
import org.codehaus.jackson.map.PropertyNamingStrategy;
import org.codehaus.jackson.map.PropertyNamingStrategy.PropertyNamingStrategyBase;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;

/**
 * @author RichardZourob
 *
 */

public abstract class PathPropertyNamingStrategy
{
    public static final PropertyNamingStrategy KEEP_AS_IS = new PathPropertyNamingKeepAsIs();

    public static class PathPropertyNamingKeepAsIs extends PropertyNamingStrategyBase
    {
        @Override
        public String nameForGetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName)
        {
            if(method.getName().matches("get[_?[A-Z]+[0-9]*]+")){
        	defaultName = defaultName.toUpperCase();
            }
            return translate(defaultName);
        }

        @Override
        public String nameForSetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName)
        {
            if(method.getName().matches("set[_?[A-Z]+[0-9]*]+")){
        	defaultName = defaultName.toUpperCase();
            }
            return translate(defaultName);
        }
        
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codehaus.jackson.map.PropertyNamingStrategy.
	 * PropertyNamingStrategyBase#translate(java.lang.String)
	 */
	@Override
	public String translate(String propertyName)
	{
	    return propertyName;
	}

    }
}
