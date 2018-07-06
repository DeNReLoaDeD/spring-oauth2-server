package com.securedrestapi.restapi.configuration.feign;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import feign.auth.BasicAuthRequestInterceptor;

/**
 * Configuration class used for IdentityProviderAccountRestClient Feign client
 */
public class IdentityProviderAccountRestClientConfiguration {

	/**
	 * Basic auth interceptor
	 * 
	 * @param username
	 *            for the basic auth
	 * @param password
	 *            for the basic auth
	 * @return {@link BasicAuthRequestInterceptor} instance
	 */
	@Bean
	public BasicAuthRequestInterceptor basicAuthRequestIdentityProviderAccountInterceptor(
			@Value("${auth.server.client.id}") String username,
			@Value("${auth.server.client.secret}") String password) {
		return new BasicAuthRequestInterceptor(username, password);
	}
}
