package com.securedrestapi.restapi.web.dto.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * Encapsulates information for login related operations
 */
public class TokenRequestDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String FIELD_GRANT_TYPE = "grant_type";

	private static final String FIELD_USERNAME = "username";

	private static final String FIELD_PASSWORD = "password";	

	@NotNull
	private String grantType;

	@NotNull
	private String password;

	@NotNull
	private String username;

	public String getGrantType() {
		return grantType;
	}

	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "LoginDTO [grantType=" + grantType + ", password=" + password
				+ ", username=" + username + "]";
	}

	public MultiValueMap<String, String> toMap() {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add(FIELD_GRANT_TYPE, "password");
		map.add(FIELD_USERNAME, this.getUsername());
		map.add(FIELD_PASSWORD, this.getPassword());
		return map;
	}
}
