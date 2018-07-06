/*
 * Copyright (C) 2016 SEAT, S.A - All Rights Reserved
 *
 * This file is part of MySeatApp.
 *
 * Unauthorized reproduction, copying, or modification, of this file, via
 * any medium is strictly prohibited.
 *
 * This code is Proprietary and Confidential.
 *
 * All the 3rd parties libraries included in the project are regulated by
 * their own licenses.
 */
package com.securedrestapi.restapi.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * Configuration for Basic auth resources for the service
 */
@Configuration
public class BasicAuthServerConfig extends ResourceServerConfigurerAdapter {

	private static final String SERVER_RESOURCE_ID = "SECURED-APP";

	@Value("${client.credentials.client-id}")
	private String defautUsername;

	@Value("${client.credentials.client-secret}")
	private String defaultPassword;

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.security.oauth2.config.annotation.web.configuration.
	 * ResourceServerConfigurerAdapter#configure(org.springframework.security.
	 * config.annotation.web.builders.HttpSecurity)
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		/* @formatter:off */
		http
			.exceptionHandling()
			.authenticationEntryPoint(http401UnauthorizedEntryPoint())
		.and()
			.exceptionHandling().accessDeniedHandler(http403ForbiddenHandler())
		.and()
			.csrf()
			.disable()
			.headers()
			.frameOptions()
			.disable()
		.and().
		sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
			.authorizeRequests()
			.antMatchers( "/token").authenticated()			
			.antMatchers("/v2/api-docs").permitAll()
		.and()
			.httpBasic();
		/* @formatter:on */
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		/* @formatter:off */
		auth
			.inMemoryAuthentication()
			.withUser(defautUsername)
			.password(defaultPassword)
			.roles("USER");
		/* @formatter:on */
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(SERVER_RESOURCE_ID);
	}

	@Bean
	public Http401UnauthorizedEntryPoint http401UnauthorizedEntryPoint() {
		return new Http401UnauthorizedEntryPoint();
	}

	@Bean
	public Http403ForbiddenHandler http403ForbiddenHandler() {
		return new Http403ForbiddenHandler();
	}
}
