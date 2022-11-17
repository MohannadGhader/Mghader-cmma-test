package com.path.lib.common.filter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.NoSuchElementException;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang.StringUtils;

/**
 * After modifying the http request body and contentType, build a new request
 * object Only for request types that are readable by the request body
 *
 * @author zhaoxb
 * @create 2019-09-26 17:49
 */
//@Data
public class ModifyRequestBodyWrapper extends HttpServletRequestWrapper {
	/**
	 * Original request object
	 */
	private HttpServletRequest orginalRequest;
	/**
	 * Modified request body
	 */
	private String modifyRequestBody;
	/**
	 * Modified request type
	 */
	private String contentType;

	/**
	 * Modify the request body, and use the original request type
	 *
	 * @param orginalRequest    Original request object
	 * @param modifyRequestBody Modified request body
	 */
	public ModifyRequestBodyWrapper(HttpServletRequest orginalRequest, String modifyRequestBody) {
		this(orginalRequest, modifyRequestBody, null);
	}

	/**
	 * Modify request body and request type
	 *
	 * @param orginalRequest    Original request object
	 * @param modifyRequestBody Modified request body
	 * @param contentType       Modified request type
	 */
	public ModifyRequestBodyWrapper(HttpServletRequest orginalRequest, String modifyRequestBody, String contentType) {
		super(orginalRequest);
		this.modifyRequestBody = modifyRequestBody;
		this.orginalRequest = orginalRequest;
		this.contentType = contentType;
	}

	/**
	 * Build a new input stream and put the modified request body into the new input
	 * stream (using the character set in the original request)
	 *
	 * @return New input stream (including modified request body)
	 * @throws IOException 
	 */
	
	public ServletInputStream getInputStream() throws IOException {
		return new ServletInputStream() {
			private InputStream in = new ByteArrayInputStream(
					modifyRequestBody.getBytes("UTF-8"));
//			private InputStream in = orginalRequest.getInputStream();
			@Override
			public int read() throws IOException {
				return in.read();
			}

			@Override
			public boolean isFinished() {
				return false;
			}

			@Override
			public boolean isReady() {
				return false;
			}

			@Override
			public void setReadListener(ReadListener readListener) {

			}
		};
	}

	/**
	 * Get new request body size
	 *
	 * @return
	 */
	@Override
//	@SneakyThrows
	public int getContentLength() {
		try {
			return modifyRequestBody.getBytes(orginalRequest.getCharacterEncoding()).length;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}

	}

	/**
	 * Get new request body size
	 *
	 * @return
	 */
	@Override
//	@SneakyThrows
	public long getContentLengthLong() {
		try {
			return modifyRequestBody.getBytes(orginalRequest.getCharacterEncoding()).length;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * Get the new request type. By default, the original request's
	 *
	 * @return
	 */
	@Override
	public String getContentType() {
		return StringUtils.isBlank(contentType) ? orginalRequest.getContentType() : contentType;
	}

	/**
	 * Modify contentType
	 *
	 * @param name Request header
	 * @return
	 */
//	@Override
	public Enumeration<String> getHeaders(String name) {
		if (null != name && name.replace("-", "").toLowerCase().equals("contenttype")
				&& !StringUtils.isBlank(contentType)) {
			return new Enumeration<String>() {
				private boolean hasGetted = false;

				@Override
				public boolean hasMoreElements() {
					return !hasGetted;
				}

				@Override
				public String nextElement() {
					if (hasGetted) {
						throw new NoSuchElementException();
					} else {
						hasGetted = true;
						return contentType;
					}
				}
			};
		}
		return super.getHeaders(name);
	}
}
