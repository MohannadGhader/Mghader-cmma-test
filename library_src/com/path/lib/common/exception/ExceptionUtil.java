package com.path.lib.common.exception;

import java.sql.SQLException;
import java.util.Properties;

import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.path.bo.common.CachedConstantsCommon;
import com.path.bo.common.ConstantsCommon;
import com.path.lib.common.util.StringUtil;
import com.path.lib.log.Log;

/**
 * 
 * Copyright 2014, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author: deniskhaddad
 * 
 *          ExceptionUtil.java used to perform Exception common methods
 */
public final class ExceptionUtil
{
    /**
     * private constructor to prevent instantiation of the class
     */
    private ExceptionUtil()
    {
	Log.getInstance().warning("THis Class is Utility cannot be instantiated");
    }
    /**
     * Method that will return correct Data Access SQL exception where the
     * message will be returned from PathDatabaseErrors.properties File if
     * verbose is set to false, according to provided Language
     * 
     * @param inCause
     * @param language Language for message translation in case of verbose is
     *            false
     * @param verbose boolean to specify whether to show generic message (false) or
     *            technical details (true)
     * @return
     */
    public static String[] manageDAOException(Throwable inCause, String language, boolean verbose)
    {
	String[] message = null;
	if(verbose)
	{
	    message = new String[]{ inCause.getMessage(),null};
	}
	else
	{
	    int errorCode = 0;
	    // Error code for Database Errors applied and commented for now
	    SQLException sqlEx = new SQLException(inCause);
	    Throwable nextCause = sqlEx.getCause();
	    int loopCount = 0;// not to loop more than 100 times, not to have
			      // infinite loop
	    while(nextCause != null && loopCount < 100)
	    {
		if(nextCause instanceof SQLException)
		{
		    errorCode = ((SQLException) nextCause).getErrorCode();
		    nextCause = null;
		}
		else
		{
		    nextCause = nextCause.getCause();
		}
		loopCount++;
	    }
	    if(CachedConstantsCommon.databaseErrors == null)
	    {
		try
		{
		    CachedConstantsCommon.databaseErrors = PropertiesLoaderUtils
			    .loadAllProperties("PathDatabaseErrors.properties");
		}
		catch(Exception e)
		{
		    Log.getInstance().error(e, "Error in Loading DatabaseErrors.properites File");
		    CachedConstantsCommon.databaseErrors = new Properties();
		}
	    }
	    String theLang = StringUtil.nullEmptyToValue(language, ConstantsCommon.LANGUAGE_ENGLISH);
	    String errCodeDbMsg = CachedConstantsCommon.databaseErrors.getProperty(theLang + "_" + errorCode);
	    if(errCodeDbMsg == null)
	    {
		errCodeDbMsg = CachedConstantsCommon.databaseErrors.getProperty(ConstantsCommon.LANGUAGE_ENGLISH + "_"
			+ errorCode);
	    }

	    if(errCodeDbMsg == null)
	    {
		String theMessage = String.valueOf(errorCode);
		if(ConstantsCommon.DATABASE_DISCONNECTION_CODE == errorCode || ConstantsCommon.DATABASE_DISCONNECTION_CODE_ORA == errorCode)
		{
		    theMessage = "DataBase Disconnection";
		}
		errCodeDbMsg = "Exception occurred at the level of the Database, please check with your Administrator\nError :"
			+ theMessage;
	    }
	    message = new String[]{errCodeDbMsg, String.valueOf(errorCode)};
	}

	return message;
    }
}
