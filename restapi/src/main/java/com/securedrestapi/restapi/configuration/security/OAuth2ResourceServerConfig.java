package com.securedrestapi.restapi.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

import com.securedrestapi.restapi.configuration.security.token.CustomUserInfoTokenServices;

/**
 * Configuration for oauth2.0 resources for the service
 */
@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

	private static final String SERVER_RESOURCE_ID = "SECURED-APP";

	@Autowired
	private ResourceServerProperties sso;

	@Autowired(required = false)
	@Qualifier("userInfoRestTemplate")
	private OAuth2RestOperations restTemplate;

	@Autowired(required = false)
	private AuthoritiesExtractor authoritiesExtractor;

	@Value("${client.credentials.client-id}")
	private String clientId;

	@Value("${client.credentials.client-secret}")
	private String clientSecret;

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
			.antMatchers("/error/**").permitAll()
			.antMatchers("/account/**").authenticated();			
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

	/**
	 * Custom implemmentation of {@link ResourceServerTokenServices} used to
	 * validate the dbe oauth token against the oauth dbe endopoint
	 * 
	 * @return {@link CustomUserInfoTokenServices}
	 */
	@Bean
	@Primary
	public ResourceServerTokenServices customUserInfoTokenServices() {
		CustomUserInfoTokenServices services = new CustomUserInfoTokenServices(sso.getUserInfoUri(), clientId,
				clientSecret);
		services.setTokenType(this.sso.getTokenType());
		if (this.authoritiesExtractor != null) {
			services.setAuthoritiesExtractor(this.authoritiesExtractor);
		}
		return services;
	}
}
