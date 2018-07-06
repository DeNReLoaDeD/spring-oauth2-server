package com.securedrestapi.restapi.configuration.security.annotation.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.securedrestapi.restapi.configuration.security.annotation.OAuthClientId;

/**
 * {@link HandlerMethodArgumentResolver} class for the annotation
 * {@link OAuthClientId} that extract the clientID from a basicauth or oauth
 * request
 */
public class OAuthIdResolver implements HandlerMethodArgumentResolver {

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.web.method.support.HandlerMethodArgumentResolver#
	 * supportsParameter(org.springframework.core.MethodParameter)
	 */
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(OAuthClientId.class);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.web.method.support.HandlerMethodArgumentResolver#
	 * resolveArgument(org.springframework.core.MethodParameter,
	 * org.springframework.web.method.support.ModelAndViewContainer,
	 * org.springframework.web.context.request.NativeWebRequest,
	 * org.springframework.web.bind.support.WebDataBinderFactory)
	 */
	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

		// get the context and extract the clientId
		if (SecurityContextHolder.getContext().getAuthentication() instanceof OAuth2Authentication) {
			OAuth2Authentication authentication = (OAuth2Authentication) SecurityContextHolder.getContext()
					.getAuthentication();

			return authentication.getOAuth2Request().getClientId();
		} else
			if (SecurityContextHolder.getContext().getAuthentication() instanceof UsernamePasswordAuthenticationToken) {
			UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder
					.getContext().getAuthentication();
			return ((User) authentication.getPrincipal()).getUsername();
		}
		return null;
	}

}
