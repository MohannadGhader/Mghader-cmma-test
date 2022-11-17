package com.path.lib.common.filter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.springframework.http.MediaType;

public class HttpServletResponseReadableWrapper extends HttpServletResponseWrapper{

	
	public HttpServletResponseReadableWrapper(HttpServletResponse response) {
		super(response);
	}
	
	 @Override
	    public String getHeader(String headerName) {
	        String headerValue = super.getHeader(headerName);
	        if ("Accept".equalsIgnoreCase(headerName)) {
	            return headerValue.replaceAll(
	                MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE);
	        } else if ("Content-Type".equalsIgnoreCase(headerName)) {
	            return headerValue.replaceAll(
	                MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE);
	        }
	        return headerValue;
	    }

	 
	  @Override
	    public String getContentType() {
	        String contentTypeValue = super.getContentType();
	        if (MediaType.APPLICATION_JSON_VALUE.equalsIgnoreCase(contentTypeValue)) {
	            return MediaType.TEXT_PLAIN_VALUE;
	        }
	        return contentTypeValue;
	    }
	  
}
