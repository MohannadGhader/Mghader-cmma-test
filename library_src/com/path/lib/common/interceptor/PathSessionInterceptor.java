/**
 * 
 */
package com.path.lib.common.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.path.bo.common.BaseServices;
import com.path.bo.common.ConstantsCommon;
//import com.path.dbmaps.vo.S_APPLOGVO;
import com.path.lib.common.base.BaseServicesImpl;
import com.path.lib.common.util.ApplicationContextProvider;
import com.path.lib.log.Log;
import com.path.struts2.lib.common.base.BaseAction;
import com.path.struts2.lib.common.base.BaseInterceptor;
import com.path.vo.common.CommonLibCO;
import com.path.vo.common.SessionCO;

/**
 * Copyright 2014, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author: marwanmaddah
 * 
 *          PathSessionInterceptor.java used to
 */
@SuppressWarnings("serial")
public class PathSessionInterceptor extends BaseInterceptor
{
    protected final static Log pathlog = Log.getInstance();

    @Override
    public String intercept(ActionInvocation invocation) throws Exception
    {
	boolean toLogout = false;
	String accessChangedMsg = null;
	String result = null;
	try
	{
	    CommonLibCO commonLibCO = null;
	    Map<String, Object> session = ActionContext.getContext().getSession();
	    BaseServices baseServices = (BaseServicesImpl) ApplicationContextProvider.getApplicationContext().getBean(
		    "baseServices");
	    SessionCO sessionCO = (SessionCO) session.get(ConstantsCommon.SESSION_VO_ATTR);
	    /**
	     * in case of kill the session if forced to logout and set attribute in new session to
             * catch it in sessionTimeout method to display correct message
             * in this case we have to avoid the interceptor for that we will check the forcedLoggedOut
             *  
	     * the condition on companyCode has been added to avoid http session checking in case company & branch are not defined yet 
	     * (added after the changes on setSessionId location to be before company & branch step)
	     */
	    if(session.get("forcedLoggedOut") == null && sessionCO != null && sessionCO.getHttpSessionID()!=null && sessionCO.getCompanyCode()!=null)
	    {
		String sessionId = sessionCO.getHttpSessionID();
//		S_APPLOGVO sAppLogVO = new S_APPLOGVO();
//		sAppLogVO.setCOMP_CODE(sessionCO.getCompanyCode());
//		sAppLogVO.setBRANCH_CODE(sessionCO.getBranchCode());
//		sAppLogVO.setAPP_NAME(sessionCO.getCurrentAppName());
//		sAppLogVO.setUSER_ID(sessionCO.getUserName());
//		sAppLogVO.setWEB_HTTP_SESSION_ID(sessionId);
		if(commonLibCO !=null && !commonLibCO.getCheckStillLoggedIn())
		{
		    toLogout = true;
		}
		
		//in case comming from login in premain.jsp and the username or comp or branch is changed, we need to bypass the force logout and open the screen directly
		HttpServletRequest request = ServletActionContext.getRequest();
		if(toLogout && request != null && request.getSession().getAttribute("reload_user_session_details") != null)
		{
		    toLogout = false;
		}
		
	    }
	    if(toLogout)
	    {
		result = "forced_logged_out";
	    }
	    else
	    {
		/* Login Alert Implementation TP#297149 
		 In case the session does not contains the alertLoginApprovalFlag that means the login alert is disabled
		 In case of alertLoginApprovalFlag = 0 that means the login alert is enabled but the teller didn't receive the approval
		 */
	    }

	}
	catch(Exception e)
	{
	    Object action = invocation.getAction();
	    Log.getInstance().error(e, "Error in Path Session Interceptor"+ action.getClass().getCanonicalName());
	    result = ActionSupport.ERROR;
	    if(action instanceof BaseAction)
	    {
		BaseAction theBase = (BaseAction) action;
		try
		{
		    theBase.handleException(e, null, null);
		}
		catch(Exception ex)
		{
		    Log.getInstance().error(ex, "Error in Path Session Interceptor Catch block");
		    theBase.addActionError(theBase.getText("session_interceptor_exception_key")+"\nERROR:"+ex.getMessage());
		}
		result = theBase.ERROR_ACTION;
	    }
	}
	return result;


    }
}
