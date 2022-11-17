package com.path.lib.common.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.IOUtils;

public class MultiReadHttpServletRequest  extends HttpServletRequestWrapper{

	 private ByteArrayOutputStream cachedBytes;
	 private byte[] rawData;
	 private HttpServletRequest request;
	 private ResettableServletInputStream servletStream;
	 public MultiReadHttpServletRequest(HttpServletRequest request) {
		    super(request);
		    this.request = request;
		    this.servletStream = new ResettableServletInputStream();
		  }
//	 @Override
//	    public String getHeader(String headerName) {
//	        String headerValue = super.getHeader(headerName);
//	        if ("Accept".equalsIgnoreCase(headerName)) {
//	            return headerValue.replaceAll(
//	                MediaType.TEXT_PLAIN_VALUE, MediaType.APPLICATION_JSON_VALUE);
//	        } else if ("Content-Type".equalsIgnoreCase(headerName)) {
////	            return headerValue.replaceAll(
////	                MediaType.TEXT_PLAIN_VALUE, MediaType.APPLICATION_JSON_VALUE);
//	            return headerValue.replaceAll(
//		                null, MediaType.APPLICATION_JSON_VALUE);
//
//	        }
//	        if ("Content-Type".equals(null)) {
//	        	return headerValue.replaceAll(
//		                null, MediaType.APPLICATION_JSON_VALUE);
//	        }
//	        return headerValue;
//	    }
//	 @Override
//	    public Enumeration getHeaders(String name) {
//	        List values = Collections.list(super.getHeaders(name));
//	        if(name.equalsIgnoreCase("content-type")) {
//	            values.add("application/json");
//	        }
//	        return Collections.enumeration(values);
//	    }
//	 @Override
//	    public Enumeration getHeaderNames() {
//	        List names = Collections.list(super.getHeaderNames());
//	        names.addAll(Collections.list(super.getParameterNames()));
//	        String url = new String(((HttpServletRequest)super.getRequest()).getRequestURL());
//	  if(!names.contains("content-type"))
//	    names.add("content-type");
//	  return Collections.enumeration(names);
//	    }

		  @Override
		  public ServletInputStream getInputStream() throws IOException {
		    if (cachedBytes == null)
		      cacheInputStream();

		      return new CachedServletInputStream();
		  }

		  @Override
		  public BufferedReader getReader() throws IOException{
		    return new BufferedReader(new InputStreamReader(getInputStream()));
		  }

		  private void cacheInputStream() throws IOException {
		    /* Cache the inputstream in order to read it multiple times. For
		     * convenience, I use apache.commons IOUtils
		     */
		    cachedBytes = new ByteArrayOutputStream();
		    IOUtils.copy(super.getInputStream(), cachedBytes);
		  }

		  /* An inputstream which reads the cached request body */
		  public class CachedServletInputStream extends ServletInputStream {
		    private ByteArrayInputStream input;

		    public CachedServletInputStream() {
		      /* create a new input stream from the cached request body */
		      input = new ByteArrayInputStream(cachedBytes.toByteArray());
		    }

		    @Override
		    public int read() throws IOException {
		      return input.read();
		    }

			@Override
			public boolean isFinished() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isReady() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void setReadListener(ReadListener arg0) {
				// TODO Auto-generated method stub
				
			}
		  }
		  
		  public void resetInputStream(byte[] newRawData) {
			    rawData = newRawData;
			    servletStream.stream = new ByteArrayInputStream(newRawData);
			}

//			@Override
//			public ServletInputStream getInputStream() throws IOException {
//			    if (rawData == null) {
//			        rawData = IOUtils.toByteArray(this.request.getReader());
//			        servletStream.stream = new ByteArrayInputStream(rawData);
//			    }
//			    return servletStream;
//			}
//			@Override
//			public BufferedReader getReader() throws IOException {
//			    if (rawData == null) {
//			        rawData = IOUtils.toByteArray(this.request.getReader());
//			        servletStream.stream = new ByteArrayInputStream(rawData);
//			    }
//			    return new BufferedReader(new InputStreamReader(servletStream));
//			}

		  
			private class ResettableServletInputStream extends ServletInputStream {

			    private InputStream stream;

			    @Override
			    public int read() throws IOException {
			        return stream.read();
			     }

				@Override
				public boolean isFinished() {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public boolean isReady() {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public void setReadListener(ReadListener arg0) {
					// TODO Auto-generated method stub
					
				}
			   }
}
