
package com.securedrestapi.restapi.configuration.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * Returns a 401 error code (Unauthorized) to the client.
 */
public class Http401UnauthorizedEntryPoint implements AuthenticationEntryPoint {

	private final Logger LOGGER = LoggerFactory.getLogger(Http401UnauthorizedEntryPoint.class);

	/**
	 * Always returns a 401 error code to the client.
	 */
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException arg2)
			throws IOException, ServletException {

		LOGGER.debug("Pre-authenticated entry point called. Rejecting access");
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied");
	}
}
