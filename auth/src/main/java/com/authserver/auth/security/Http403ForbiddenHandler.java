package com.authserver.auth.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * Returns a 403 error code (Forbidden) to the client.
 */
public class Http403ForbiddenHandler implements AccessDeniedHandler {

	private final Logger LOGGER = LoggerFactory.getLogger(Http403ForbiddenHandler.class);

	/**
	 * Always returns a 403 error code to the client.
	 */
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		LOGGER.debug("Forbbiden acces. Rejecting access");
		response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
	}
}
