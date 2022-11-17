/**
 * Copyright 2019, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * @author: gilbertandary
 *
 * HeaderInterceptor.java
 *
 * 
 */
package com.path.lib.ws;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;


public class HeaderInterceptor extends AbstractPhaseInterceptor
{


    public HeaderInterceptor()
    {
	super(Phase.READ);// READ
    }

    @Override
    public void handleMessage(Message message) throws Fault
    {
	try
	{
	    Map<String, List> headers = (Map<String, List>) message.get(Message.PROTOCOL_HEADERS);
	    if(headers.get("Content-Type") == null)
	    {
		headers.put("Content-Type", Arrays.asList("application/json"));
		message.put(Message.PROTOCOL_HEADERS, headers);
	    }

	    if(headers.get("Content-Type") != null && headers.get("Content-Type").get(0) == null)
	    {
		headers.put("Content-Type", Arrays.asList("application/json"));
		message.put(Message.PROTOCOL_HEADERS, headers);
	    }
	}
	catch(Exception e)
	{
	    return;
	}
	finally
	{
	}
	return;
    }

}
