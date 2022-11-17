/**
 * 
 */
package com.path.lib.ws;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;

import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.jaxrs.utils.JAXRSUtils;
import org.apache.cxf.message.Message;

/**
 * Copyright 2016, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * @author: nabilfeghali
 *
 * RestBasicAuthInterceptor.java used to
 */
public class RestBasicAuthInterceptor implements ContainerRequestFilter
{
	public void filter(ContainerRequestContext context) throws IOException {
		Response failedRS = Response.status(401)
				.header("WWW-Authenticate", "Basic").build();
		Message message = JAXRSUtils.getCurrentMessage();
		AuthorizationPolicy policy = (AuthorizationPolicy) message
				.get(AuthorizationPolicy.class);
		if (policy == null) {
			context.abortWith(failedRS);
		} else {
			String username = policy.getUserName();
			String password = policy.getPassword();

			if (!isAuthenticated(username, password)) {
				context.abortWith(failedRS);
			}
		}
	}

	private boolean isAuthenticated(String username, String password) {
		if (username != null && username.equals(password)) {
			return true;
		}
		return false;
	}
}