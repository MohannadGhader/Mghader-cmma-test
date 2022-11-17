package com.path.lib.common.exception;

import java.io.IOException;
import java.io.Writer;

import com.opensymphony.xwork2.ActionContext;
import com.path.lib.log.Log;
import com.path.struts2.lib.common.base.BaseAction;

import freemarker.core.Environment;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class PathFreeMarkerExpHandler implements TemplateExceptionHandler
{

    @Override
    public void handleTemplateException(TemplateException te, Environment env, Writer out) throws TemplateException
    {
	try
	{
	    String theError = "Error at Level of Application, Please Contact Administrator.";
	    if(ActionContext.getContext() != null)
	    {
		BaseAction baseAction = ((BaseAction) ActionContext.getContext().getActionInvocation().getAction());
		BaseException baseExp = new BaseException(te);
		baseAction.handleException(baseExp, null, null);
		theError = baseAction.get_error();
	    }
	    out.flush();
	    out.write(theError);
	    throw new TemplateException(theError, env);
	}
	catch(Exception e)
	{
	    Log.getInstance().error(te, "Error in Freemarker FTL handling");
	    Log.getInstance().error(e, "Exception occured while Handling Template Error PathFreeMarkerExpHandler.handleTemplateException ");
	    throw new TemplateException("Failed to print error message. Cause: " + e, env);
	}
    }

}
