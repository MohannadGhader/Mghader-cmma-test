package com.path.bo.common;

import com.path.lib.log.Log;

/**
 * 
 * Copyright 2013, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author: deniskhaddad
 * 
 *          VersionUtils.java used to perform operation on Versions of the the
 *          Application , and Compare different versions among each other
 */
public final class VersionUtils
{

    private static String VERSION_FORMAT = "[^a-zA-Z0-9]+";

    /**
     * Private constructor only to prevent instantiation in the class
     */
    private VersionUtils()
    {
	Log.getInstance().error("This Class Should not be Instantiated");
    }

    /**
     * 
     * Used for comparing 2 version
     * 
     * @param version1
     * @param version2
     * @return negative if version1 < version2, 0 if version1=version2, positive
     *         if version1>version2
     */
    public static int compareVersions(String version1, String version2)
    {
	if(version1 == null && version2 == null)
	{
	    return 0;
	}
	else if(version1 == null)
	{
	    return -1;
	}
	else if(version2 == null)
	{
	    return 1;
	}
	// split each version into parts so that comparison will be on parts and
	// if any of the parts is number then numeric comparison is performed on
	// that part
	String[] arr1 = version1.split(VERSION_FORMAT), arr2 = version2.split(VERSION_FORMAT);

	int i1, i2, i3;
	// loop on the array split that has maximum size
	for(int ii = 0, max = Math.min(arr1.length, arr2.length); ii <= max; ii++)
	{
	    if(ii == arr1.length)
	    {
		return ii == arr2.length ? 0 : -1;
	    }
	    else if(ii == arr2.length)
	    {
		return 1;
	    }
	    // check if part is number
	    try
	    {
		i1 = Integer.parseInt(arr1[ii]);
	    }
	    catch(Exception x)
	    {
		i1 = Integer.MAX_VALUE;
	    }
	    // check if part is number
	    try
	    {
		i2 = Integer.parseInt(arr2[ii]);
	    }
	    catch(Exception x)
	    {
		i2 = Integer.MAX_VALUE;
	    }
	    // check if two parts(after being converted to number) are Equal, if
	    // not then return the difference
	    if(i1 != i2)
	    {
		return i1 - i2;
	    }

	    i3 = arr1[ii].compareTo(arr2[ii]);

	    if(i3 != 0)
	    {
		return i3;
	    }
	}

	return 0;
    }
}
