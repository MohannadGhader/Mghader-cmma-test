package com.path.lib.common.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.path.lib.log.Log;

public class MyRequestWrapper extends HttpServletRequestWrapper {

	protected final static Log log = Log.getInstance();

	

	 private String _body;
	    private HttpServletRequest _request;

	    public MyRequestWrapper(HttpServletRequest request) throws IOException
	    {
	        super(request);
	        _request = request;

	        _body = "";
	        try (BufferedReader bufferedReader = request.getReader())
	        {
	            String line;
	            while ((line = bufferedReader.readLine()) != null)
	                _body += line;
	        }
	    }

	    @Override
	    public ServletInputStream getInputStream() throws IOException
	    {
	        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(_body.getBytes());
	        return new ServletInputStream()
	        {
	            public int read() throws IOException
	            {
	                return byteArrayInputStream.read();
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
	        };
	    }

	    @Override
	    public BufferedReader getReader() throws IOException
	    {
	        return new BufferedReader(new InputStreamReader(this.getInputStream()));
	    }
	
//    private byte[] body;
//    
//   
//	public MyRequestWrapper(HttpServletRequest request) throws IOException {
//        super(request);
// 
//        StringBuilder sb = new StringBuilder();
//        String line;
//        BufferedReader reader = request.getReader();
//        while ((line = reader.readLine()) != null) {
//            sb.append(line);
//        }
//        String body = sb.toString();
//        this.body = body.getBytes(StandardCharsets.UTF_8);
//    }
// 
// 
//    public String getBody() {
//        return new String(body, StandardCharsets.UTF_8);}

}
