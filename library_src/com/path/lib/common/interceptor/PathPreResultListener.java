/**
 * 
 */
package com.path.lib.common.interceptor;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.PreResultListener;
import com.path.bo.common.BaseServices;
import com.path.bo.common.ConstantsCommon;
//import com.path.dbmaps.vo.SYS_PARAM_SCREEN_DISPLAYVO;
import com.path.lib.common.base.BaseServicesImpl;
import com.path.lib.common.util.ApplicationContextProvider;
import com.path.lib.common.util.StringUtil;
import com.path.struts2.lib.common.base.BaseAction;
//import com.path.vo.common.RequiredFieldsSC;
import com.path.vo.common.SessionCO;

/**
 * Copyright 2016, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * @author: marwanmaddah
 *
 * PathPreResultListener.java used to change the response data after the invocation
 * this class will be called from PathSessionInterceptor
 * to catch the event after invocation and call the dynamic expression process
 */
public class PathPreResultListener implements PreResultListener
{
    private ActionInvocation   invocation;
    private String             resultCode;
    
    public PathPreResultListener(ActionInvocation invocation, String resultCode)
    {
	super();
	this.invocation = invocation;
	this.resultCode = resultCode;
	beforeResult(invocation,resultCode);
    }
    
    @Override
    public void beforeResult(ActionInvocation arg0, String arg1)
    {
        // perform operation necessary before Result execution
	    /**
	     * Common dependency process.
	     */
	try
	{
	    BaseAction theBase = (BaseAction) arg0.getAction();
	    Map<String,Object> paramsMap = theBase.getHmDynElems();
	    if(paramsMap!=null && !paramsMap.isEmpty())
	    {
        	    BaseServices baseServices = (BaseServicesImpl) ApplicationContextProvider.getApplicationContext().getBean(
        		    "baseServices");
        	    SessionCO sesCO = (SessionCO)arg0.getInvocationContext().getSession().get(ConstantsCommon.SESSION_VO_ATTR);
//        	    RequiredFieldsSC criteria = new RequiredFieldsSC();
//        	    criteria.setProgRef(((String[])paramsMap.get("_progRef"))[0]);
//        	    paramsMap.remove("_progRef");
//        	    criteria.setAppName(sesCO.getCurrentAppName());
	    }
	    /**
	     * End
	     */
	}
	catch(Exception e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
//    private Map<String,SYS_PARAM_SCREEN_DISPLAYVO> displayStrongestMgmt(Map<String,SYS_PARAM_SCREEN_DISPLAYVO> basicMap,Map<String,SYS_PARAM_SCREEN_DISPLAYVO> exprsMap,RequiredFieldsSC criteria)  throws Exception
    {
	BigDecimal requiredStrongValue = ConstantsCommon.REQUIRED_STRONG_VALUE;
	BigDecimal readonlyStrongValue = ConstantsCommon.READONLY_STRONG_VALUE;
	BigDecimal visibleStrongValue  = ConstantsCommon.VISIBLE_STRONG_VALUE;
	
//	Iterator it  = exprsMap.entrySet().iterator();
//	while(it.hasNext())
//	{
//	    Entry  thisEntry = (Entry) it.next();
//	    String key       = (String)thisEntry.getKey();
////	    SYS_PARAM_SCREEN_DISPLAYVO currExprVO = (SYS_PARAM_SCREEN_DISPLAYVO)thisEntry.getValue();
//	    if(!basicMap.containsKey(key))
//	    {
//		if(!StringUtil.nullToEmpty(currExprVO.getDEFAULT_VALUE()).isEmpty())
//		{
//		    currExprVO.setValue(currExprVO.getDEFAULT_VALUE());
//		    currExprVO.setTriggerChange(ConstantsCommon.ONE);
//		}
//		basicMap.put(key+"_"+criteria.getProgRef(),currExprVO);
//	    }
//	    else
//	    {
//		SYS_PARAM_SCREEN_DISPLAYVO basicDisplayVO = basicMap.get(key);
//		if(!ConstantsCommon.REQUIRED_STRONG_VALUE.equals(basicDisplayVO.getIS_MANDATORY()) && ConstantsCommon.REQUIRED_STRONG_VALUE.equals(currExprVO.getIS_MANDATORY()))
//		{
//		    basicDisplayVO.setIS_MANDATORY(requiredStrongValue); 
//		}
//		if(!ConstantsCommon.READONLY_STRONG_VALUE.equals(basicDisplayVO.getIS_READONLY()) && ConstantsCommon.READONLY_STRONG_VALUE.equals(currExprVO.getIS_READONLY()))
//		{
//		    basicDisplayVO.setIS_READONLY(readonlyStrongValue); 
//		}
//		if(!ConstantsCommon.VISIBLE_STRONG_VALUE.equals(basicDisplayVO.getIS_VISIBLE()) && ConstantsCommon.VISIBLE_STRONG_VALUE.equals(currExprVO.getIS_VISIBLE()))
//		{
//		    basicDisplayVO.setIS_VISIBLE(visibleStrongValue);  
//		}
//		if(BigDecimal.ONE.equals(basicDisplayVO.getDFLT_VAL_EXPR_PRIORITY_YN()))
//		{
//		    basicDisplayVO.setValue(currExprVO.getDEFAULT_VALUE()); 
//		    basicDisplayVO.setTriggerChange(ConstantsCommon.ONE);
//		}
//	    }
//	}
	
//	return basicMap;
    }
    public ActionInvocation getInvocation()
    {
        return invocation;
    }
    public void setInvocation(ActionInvocation invocation)
    {
        this.invocation = invocation;
    }
    public String getResultCode()
    {
        return resultCode;
    }
    public void setResultCode(String resultCode)
    {
        this.resultCode = resultCode;
    }
}
