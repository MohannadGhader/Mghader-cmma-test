package com.path.bo.common;

import com.path.lib.common.exception.BaseException;
import com.path.vo.common.SessionCO;



/**
 * Interface for Exception handler  classes
 * <p>Copyright: Copyright (c) 2006
 *
 * <p>Company: Pathsolutions
 *
 */
public interface BaseServices
{
 /**
  * Used to handle exceptions.
  * It loops through the Exceptions of type
  * {@link BaseException} and add an alert to the BaseForm. If the 'cause'
  * isn't of type BaseException and the cause != null, It logs the first
  * exception
  *
  * @param form BaseForm
  * @param inCause Throwable
  * @param request HttpServletRequest
  */
 public String[] logError(Throwable inCause, SessionCO  sessionCO);

 /**
  * Used to get the general exception message.
  * It loops through the Exceptions of type {@link BaseException}.
  * If the 'inCause' isn't of type BaseException and the inCause != null, It returns the first
  * exception generic message
  *
  * @param request HttpServletRequest
  * @param inCause Throwable
  * @return String: exception message
  */
 public String[] getExceptionGenericMessage(Throwable inCause, SessionCO  sessionCO);
 
/**
 * clearing cached parameters in baseService Portal class
 * @throws BaseException
 */
 public void clearBaseServiceCache() throws BaseException;
}
