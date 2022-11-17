package com.path.lib.common.interceptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.path.lib.common.exception.BaseException;

/**
 * Copyright 2017, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * @author: nabilfeghali
 *
 * PathFileUploadWrapperRequest.java used to
 */
public class PathFileUploadWrapperRequest extends HttpServletRequestWrapper
{

    private final Map<String, String[]> regularParamsMap = new LinkedHashMap<String, String[]>();
    private final Map<String, FileItem> fileParamsMap = new LinkedHashMap<String, FileItem>();
    public PathFileUploadWrapperRequest(HttpServletRequest aRequest) throws BaseException
    {
	super(aRequest);
	ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
	try
	{
	    List<FileItem> fileItems = upload.parseRequest(aRequest);
	    convertToMaps(fileItems);
	}
	catch(FileUploadException ex)
	{
	    ex.printStackTrace();
	    throw new BaseException("[PathFileUploadWrapperRequest] Cannot parse underlying request: " + ex.toString());
	}
    }

    @Override
    public Enumeration<String> getParameterNames()
    {
	Set<String> allNames = new LinkedHashSet<String>();
	allNames.addAll(regularParamsMap.keySet());
	allNames.addAll(fileParamsMap.keySet());
	return Collections.enumeration(allNames);
    }

    @Override
    public String getParameter(String aName)
    {
	String result = null;
	String[] values = regularParamsMap.get(aName);
	if(values == null)
	{
	    return null;
	}
	else if(values.length == 0)
	{
	    result = "";
	}
	else
	{
	    result = values[0];
	}
	return result;
    }

    @Override
    public String[] getParameterValues(String aName)
    {
	String[] values = regularParamsMap.get(aName);
	return values;
    }

    @Override
    public Map<String, String[]> getParameterMap()
    {
	return Collections.unmodifiableMap(regularParamsMap);
    }

    public List<FileItem> getFileItems()
    {
	return new ArrayList<FileItem>(fileParamsMap.values());
    }

    public FileItem getFileItem(String aFieldName)
    {
	return fileParamsMap.get(aFieldName);
    }


    private void convertToMaps(List<FileItem> aFileItems)
    {
	for(FileItem item : aFileItems)
	{
	    if(isFileUploadField(item))
	    {
		fileParamsMap.put(item.getFieldName(), item);
	    }
	    else
	    {
		if(alreadyHasValue(item))
		{
		    addMultivaluedItem(item);
		}
		else
		{
		    addSingleValueItem(item);
		}
	    }
	}
    }

    private boolean isFileUploadField(FileItem aFileItem)
    {
	return !aFileItem.isFormField();
    }

    private boolean alreadyHasValue(FileItem aItem)
    {
	return regularParamsMap.get(aItem.getFieldName()) != null;
    }

    private void addSingleValueItem(FileItem aItem)
    {
	regularParamsMap.put(aItem.getFieldName(), new String[]{aItem.getString()});
    }

    private void addMultivaluedItem(FileItem aItem)
    {
	String[] values = regularParamsMap.get(aItem.getFieldName());
	List<String> currentArrayList = Arrays.asList(values);
	List<String> st = new ArrayList<String>();
	st.addAll(currentArrayList);
	st.add(aItem.getString());
	String[] newArray = st.toArray(new String[0]);
	regularParamsMap.put(aItem.getFieldName(), newArray);
    }
    
    public void addParameters(Map<String, String[]> customparams)
    {
	regularParamsMap.putAll(customparams);
    }
    
    public Map<String, FileItem>  returnFileParamsMap()
    {
	return fileParamsMap;
    }
}