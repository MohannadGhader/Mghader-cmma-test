package com.path.lib.common.interceptor;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.HttpParameters;
import org.apache.struts2.dispatcher.Parameter;
import org.apache.struts2.interceptor.FileUploadInterceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.path.lib.common.util.PathPropertyUtil;
import com.path.lib.log.Log;

public class PathFileUploadInterceptor extends FileUploadInterceptor
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static Log log = Log.getInstance();
    
    /* (non-Javadoc)
     * @see org.apache.struts2.interceptor.FileUploadInterceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
     */
    @Override
    public String intercept(ActionInvocation arg0) throws Exception
    {
	HttpServletRequest httprequest = ServletActionContext.getRequest();
	if(false && httprequest.getContentType() != null && httprequest.getContentType().contains("multipart/form-data") )
	{
	    Object internalWrappedRequest = null;
	    try
	    {
		internalWrappedRequest = PathPropertyUtil.returnProperty(httprequest,"request.request.request.request");
	    }
	    catch(Exception e)
	    {
		internalWrappedRequest = null;
		log.error(e, "PathFileUploadInterceptor: Error in retuning internal wrapped request ");
	    }

	    if(internalWrappedRequest instanceof PathFileUploadWrapperRequest)
	    {
		PathFileUploadWrapperRequest fileUploadRequest = (PathFileUploadWrapperRequest) internalWrappedRequest;
		Map<String, FileItem> fileParamsMap = fileUploadRequest.returnFileParamsMap();
		HttpParameters parameters = ActionContext.getContext().getParameters();
		if(fileParamsMap != null && !fileParamsMap.isEmpty())
		{
		    for(FileItem fileItem : fileParamsMap.values())
		    {
			String fieldName = fileItem.getFieldName();
			File uploadedFile = null;
			try
			{
			    uploadedFile = (File) FieldUtils.readField(fileItem, "tempFile", true);
			}
			catch(Exception e)
			{
			    uploadedFile = null;
			    log.error(e, "PathFileUploadInterceptor: Error in retuning uploaded temp file ");
			}
			if(uploadedFile != null)
			{
			    Parameter.File parameterUploadedFile = new Parameter.File(fieldName, uploadedFile);
			    parameters.put(fieldName, parameterUploadedFile);
			}
		    }
		}
	    }
	}
	// TODO Auto-generated method stub
	return super.intercept(arg0);
    }
    
}