/**
 * Copyright 2018, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * @author KhaledAlTimany
 *
 * ResponseFaultInterceptor.java
 *
 * 
 */
package com.path.lib.ws;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.security.AccessDeniedException;
import org.apache.cxf.message.Message;
import org.apache.cxf.message.MessageContentsList;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.springframework.http.HttpStatus;

public class ResponseFaultInterceptor extends AbstractPhaseInterceptor {

   
    public ResponseFaultInterceptor() {
	super(Phase.PRE_STREAM);
    }

    @Override
    public void handleMessage(Message message) throws Fault {
	Exception excpetion = message.getContent(Exception.class);
	if (excpetion != null) {
	    
	    try {
		MessageContentsList objs = MessageContentsList.getContentsList(message);
		Object responseObj = null;
		if (objs != null && objs.size() == 1) {
		    responseObj = objs.get(0);
		}
		if (null == responseObj) {
		    try {
			final List outObjects = message.getContent(List.class);
			if(outObjects != null)
			{    
        			for (Object obj : outObjects) {
        			    // usually at index 0 but we have to make sure that
        			    // this
        			    // is our object
        			    if (null != BeanUtils.getProperty(obj, "compCode")) {
        				responseObj = obj;
        			    }
        			}
			}
		    } catch (Exception e) {
		    }
		}

		
	    } catch (Exception e) {
	    }

	    Throwable ex = excpetion.getCause();

	    HttpServletResponse response = (HttpServletResponse) message.getExchange().getInMessage()
		    .get(AbstractHTTPDestination.HTTP_RESPONSE);
	    if (ex instanceof AccessDeniedException) {
		response.setStatus(HttpStatus.FORBIDDEN.value());
	    } else {
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
	    }
	  
	    try {
		StringWriter sw = new StringWriter();
		ex.printStackTrace(new PrintWriter(sw));
		String fullStackTrace = sw.toString();
		if (excpetion.getMessage() != null) {
		    response.getOutputStream().write(excpetion.getMessage().getBytes());
		} else {
		    response.getOutputStream().write(fullStackTrace.getBytes());
		}
		response.getOutputStream().flush();
	    } catch (IOException iex) {
	    }

	    message.getInterceptorChain().abort();
	}
	return;
    }
}
