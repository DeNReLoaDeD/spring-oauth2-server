package com.securedrestapi.restapi.configuration.security.token;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.FixedAuthoritiesExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.client.RestTemplate;

import com.securedrestapi.restapi.web.dto.common.CustomPrincipalDTO;

/**
 * Based on {@link UserInfoTokenServices} implementation. Adds the token to the
 * url as query parameter
 */
public class CustomUserInfoTokenServices implements ResourceServerTokenServices {

	protected final Log logger = LogFactory.getLog(getClass());

	private static final String PRINCIPAL_KEY = "username";

	private final String userInfoEndpointUrl;

	private final String clientId;

	private final String clientSecret;

	@SuppressWarnings("unused")
	private String tokenType = DefaultOAuth2AccessToken.BEARER_TYPE;

	private AuthoritiesExtractor authoritiesExtractor = new FixedAuthoritiesExtractor();

	public CustomUserInfoTokenServices(String userInfoEndpointUrl, String clientId, String clientSecret) {
		this.userInfoEndpointUrl = userInfoEndpointUrl;
		this.clientId = clientId;
		this.clientSecret = clientSecret;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public void setAuthoritiesExtractor(AuthoritiesExtractor authoritiesExtractor) {
		this.authoritiesExtractor = authoritiesExtractor;
	}

	public OAuth2Authentication loadAuthentication(String accessToken)
			throws AuthenticationException, InvalidTokenException {

		Map<String, Object> map = getMap(this.userInfoEndpointUrl, accessToken);
		if (map.containsKey("error")) {
			this.logger.debug("userinfo returned error: " + map.get("error"));
			throw new InvalidTokenException(accessToken);
		}

		return extractAuthentication(map);
	}

	private OAuth2Authentication extractAuthentication(Map<String, Object> map) {
		Object principal = getPrincipal(map);
		List<GrantedAuthority> authorities = this.authoritiesExtractor.extractAuthorities(map);
		OAuth2Request request = new OAuth2Request(null, this.clientId, null, true, null, null, null, null, null);
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(principal, "N/A",
				authorities);
		token.setDetails(map);
		return new OAuth2Authentication(request, token);
	}

	/**
	 * Return the principal that should be used for the token. The default
	 * implementation looks for well know {@code user*} keys in the map.
	 * country_code
	 * 
	 * @param map
	 *            the source map
	 * @return the principal
	 */
	protected Object getPrincipal(Map<String, Object> map) {
		logger.debug(" custom principal  " + map.get(PRINCIPAL_KEY));
		CustomPrincipalDTO principal = new CustomPrincipalDTO((String) map.get(PRINCIPAL_KEY));
		return principal;
	}

	public OAuth2AccessToken readAccessToken(String accessToken) {
		throw new UnsupportedOperationException("Not supported: read access token");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Map<String, Object> getMap(String path, String accessToken) {
		this.logger.debug("Getting user info from: " + path + "/" + accessToken);
		try {
			RestTemplate temp = new RestTemplate();
			return temp.exchange(path + "/" + accessToken, HttpMethod.GET,
					new HttpEntity(createHeaders(clientId, clientSecret)), Map.class).getBody();

		} catch (Exception ex) {
			this.logger.info("Could not fetch user details: " + ex.getClass() + ", " + ex.getMessage());
			return Collections.<String, Object> singletonMap("error", "Could not fetch user details");
		}
	}

	/**
	 * Create basic auth headers for tokeninfo endpoint
	 * 
	 * @param username
	 *            of the basic auth
	 * @param password
	 *            of the basic auth
	 * @return headers
	 */
	@SuppressWarnings("serial")
	private HttpHeaders createHeaders(String username, String password) {
		return new HttpHeaders() {
			{
				String auth = username + ":" + password;
				byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);
				set("Authorization", authHeader);
			}
		};
	}

}
