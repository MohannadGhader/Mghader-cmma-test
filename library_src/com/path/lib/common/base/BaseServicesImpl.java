package com.path.lib.common.base;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.path.bo.common.BaseServices;
import com.path.bo.common.ConstantsCommon;
import com.path.lib.common.exception.BOException;
import com.path.lib.common.exception.BaseException;
import com.path.lib.common.exception.DAOException;
import com.path.lib.common.exception.ExceptionUtil;
import com.path.lib.log.Log;
import com.path.vo.omni.common.base.SessionCO;

/**
 * Handler class for exceptions that will be thrown by all classes who use
 * BaseException
 * <p>
 * Copyright: Copyright (c) 2012
 * 
 * <p>
 * Company: PathSolutions
 */
public class BaseServicesImpl implements BaseServices
{
    protected final static Log log = Log.getInstance();
    private boolean verbose;// verbose value that could change depending on DB
    private boolean verboseDbSet;

    /**
     * Used to handle exceptions. It loops through the Exceptions of type
     * {@link BaseException} and returns the error Message. If the 'cause'
     * isn't of type BaseException and the cause != null, It logs the first
     * exception
     * 
     * @param inCause Throwable
     * @param request HttpServletRequest
     */
    public String[] logError(Throwable inCause, SessionCO  sessionCO)
    {
	String[] message = new String[]{"",""};
	// logging the exception before performing querying for ctsmessages
	if(inCause instanceof BOException)
	{
	    if(log.returnInfoLevel().booleanValue())
	    {
		log.error(inCause, "[PATH-INFO] Logging BOExceptions for debugging purpose only");
	    }
	}
	else
	{
	    log.error(inCause, inCause != null ? inCause.getMessage() :"");
	}
	try
	{
	    message = getExceptionGenericMessage(inCause, sessionCO);
	}
	catch(Exception ex)
	{
	    log.error(ex, "Error parsing handling PathSolError. Please report this Error to the R&D departement");
	}

	return message;
    }

    /**
     * Used to get the general exception message. It loops through the
     * Exceptions of type {@link BaseException}. If the 'inCause' isn't of
     * type BaseException and the inCause != null, It returns the first
     * exception generic message
     * 
     * @param request HttpServletRequest
     * @param inCause Throwable
     * @return String
     */
    public String[] getExceptionGenericMessage(Throwable inCause, SessionCO  sessionCO)
    {
	
	String[] msg = new String[]{"", ""};
	if(inCause != null)
	{
	    // check correct verbose parameter based on PTH CTRL column
	    if(!verboseDbSet)
	    {
		try
		{
		    verbose = false;
		    // in PB zero mean to show the technical errors and 1 means hide

		}
		catch(Exception e)
		{
		    log.error(e,"Error in Reading Verbose property corresponfing to PTH_CTRL.SYS_ERROR, verbose will be set as "+verbose);
		}
		verboseDbSet = true;
	    }
	    
	    Throwable cause = inCause;
	    do
	    {
		if(cause instanceof DAOException || cause.getCause() instanceof SQLException)
		{
		    // check if DAO Exception contains specific manipulation to extract the message from the exception rather than make generic
		    String delimiter = "~#12abc";
		    Pattern regExpPat = Pattern.compile(delimiter+"(.*?)"+delimiter,Pattern.DOTALL);
		    Matcher matcher = regExpPat.matcher(inCause.getMessage());
		    if(matcher.find())
		    {
			msg[0] = matcher.group(1);
			break;
		    }
		    else
		    {
			String language = ConstantsCommon.LANGUAGE_ENGLISH;
			String[] sqlExc = ExceptionUtil.manageDAOException(cause,language,verbose);
			msg[0] = sqlExc[0] ;
		    }
		}
		else if(cause instanceof BOException)
		{
		    msg = getErrorMessage((BaseException) cause, sessionCO);
		}
		else
		{
		    String message;
		    if(verbose)
		    {
			message = cause.getMessage();
			if(message == null)
			{
			    message = cause.toString();
			}
		    }
		    else
		    {
			message = "Exception occurred at the level of the Application, please check with your Administrator";
		    }
		    msg[0] = message; 
		}
		cause = cause.getCause();
	    }while(cause instanceof BaseException);
	}
	return msg;
    }

    /**
     * return the translated message of an Error
     * @param cause the Exception occurred
     * @return
     */
    private String[] getErrorMessage(BaseException cause, SessionCO  sessionCO)
    {
	String returnMsg[] = new String[]{"",""};
	    returnMsg[0] = cause.getMessage();
	    returnMsg[1] = "";
	return returnMsg;
    }

    /**
    
    /**
     * Sets if the exception handler user message is verbose Usually set to true
     * in development mode
     * 
     * @param verbose boolean
     */
    public void setVerbose(boolean verbose)
    {
	this.verbose = verbose;
    }

    /**
     * Get verbose property
     * 
     * @return verbose boolean
     */
    public boolean getVerbose()
    {
	return verbose;
    }

    @Override
    public String[] logError(Throwable inCause, com.path.vo.common.SessionCO sessionCO)
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String[] getExceptionGenericMessage(Throwable inCause, com.path.vo.common.SessionCO sessionCO)
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void clearBaseServiceCache() throws BaseException
    {
	// TODO Auto-generated method stub
	
    }




}
