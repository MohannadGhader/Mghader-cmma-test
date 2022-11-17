package com.path.lib.common.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.path.bo.common.ConstantsCommon;
import com.path.lib.common.util.NumberUtil;
import com.path.lib.common.util.SecurityUtils;
import com.path.lib.common.util.SecurityUtilsExt;
import com.path.lib.log.Log;

/**
 * 
 * Copyright 2012, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author: DeniskHaddad, Rabih El Khatib
 *
 *          CachHeadersFilter.java used to Cache Static Resources
 */
public class CachHeadersFilter implements Filter {
	private static Log log = Log.getInstance();
	private long expiryTime;

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest servletRequest = (HttpServletRequest) req;
		HttpServletResponse servletResponse = (HttpServletResponse) res;
		

		// If ENABLE_STATIC_CACHING == 2 then enable caching by specified time
		if (ConstantsCommon.ENABLE_STATIC_CACHING == 2) {
			servletResponse.setDateHeader("Expires", (Calendar.getInstance().getTimeInMillis() + expiryTime));
			chain.doFilter(req, res);
		} else {
			StringBuffer reqURI = new StringBuffer(servletRequest.getRequestURI());
			String queryString = servletRequest.getQueryString();
			if (queryString != null) {
				reqURI = reqURI.append(queryString);
			}
			log.debug("In CachHeaderFilter request URI " + reqURI.toString());

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ETagResponseWrapper wrappedResponse = new ETagResponseWrapper(servletResponse, baos);
			chain.doFilter(servletRequest, wrappedResponse);

			/*
			 * added by Alaa
			 */
			HttpServletRequest httpServletRequest = (HttpServletRequest) req;
			HttpServletResponse httpServletResponse = (HttpServletResponse) res;
			BufferedServletResponseWrapper bufferedResponse = new BufferedServletResponseWrapper(httpServletResponse);
			System.out.println("do filter");
		
			// pass the wrappers on to the next entry
			chain.doFilter(httpServletRequest, bufferedResponse);
			       System.out.println("filter is invoked after");   

			try {
				String responseData = bufferedResponse.getResponseData();
				SecretKey secretKey = SecurityUtilsExt.generateSecretKey();
				System.out.println(secretKey);
				String encryptedResponseData = SecurityUtilsExt.encryptWithSecretKeyAES(secretKey, responseData);
				System.out.println(encryptedResponseData);
	 			OutputStream outputStream = httpServletResponse.getOutputStream();
				outputStream.write(encryptedResponseData.getBytes());
				outputStream.flush();
				outputStream.close();
			       
			} catch (Exception e) {
				// TODO: handle exception
				log.info(e.getMessage());
			}
			/*
			 *
			 * */

			byte[] bytes = baos.toByteArray();
			String token = null;

			try {
				byte[] messageDigest = SecurityUtils.returnMd5Digest(bytes, "MD5");

				BigInteger number = new BigInteger(1, messageDigest);
				// prepend a zero to get a "proper" MD5 hash value
				StringBuffer sb = new StringBuffer('0');
				sb.append(number.toString(16));

				token = '"' + sb.toString() + '"';
			} catch (Exception e) {
				log.error(e, "ERROR In CachHeaderFilter " + reqURI.toString());
			}

			// the header
			servletResponse.setHeader("ETag", token); // always store the ETag in
			servletResponse.addDateHeader("Expires", 0);

			String previousToken = servletRequest.getHeader("If-None-Match");
			if (previousToken != null && previousToken.equals(token)) { // compare previous token with the current one
				log.debug("ETag match: returning 304 Not Modified");
				servletResponse.sendError(HttpServletResponse.SC_NOT_MODIFIED);
				// re-use original last modified time
				servletResponse.setHeader("Last-Modified", servletRequest.getHeader("If-Modified-Since"));
			} else {
				// first time through - set last modified time to now
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.MILLISECOND, 0);
				Date lastModified = cal.getTime();
				servletResponse.setDateHeader("Last-Modified", lastModified.getTime());

				log.debug("Writing body content");
				servletResponse.setContentLength(bytes.length);
				ServletOutputStream sos = servletResponse.getOutputStream();
				sos.write(bytes);
				sos.flush();
				sos.close();
			}
		}

	}

	public void init(FilterConfig filterConfig) {
		String expTime = filterConfig.getInitParameter("expire-time");
		if (NumberUtil.isNumber(expTime)) {
			expiryTime = Long.parseLong(expTime);
		} else {
			expiryTime = 2592000000l;
		}
	}

	/**
	 * exists in the interface
	 */
	public void destroy() {
		log.warning("inherited from the parent class");
	}
}
