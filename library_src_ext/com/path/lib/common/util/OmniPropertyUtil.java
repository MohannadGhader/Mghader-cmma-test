package com.path.lib.common.util;

import java.util.HashMap;

public class OmniPropertyUtil
{
    
    /**
     * customized OMNI method that convert the map constructed using the 'convertToMap' method
     * to object.
     * 
     * @param map Map to be converted to Object.
     * @param objClass Class of required Object.
     * @return resulted Object with map data.
     */
    public static Object convertToObject(HashMap<String, Object> map, Class<?> objClass)
    {
	PathObjectMapper m = new PathObjectMapper();
	return m.convertValue(map, objClass);
    }

}
