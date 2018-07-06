
package com.securedrestapi.restapi.web.dto.common;

import java.io.Serializable;
import java.security.Principal;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Encapsulate the basic information of a user authenticated
 */
public class CustomPrincipalDTO implements Serializable, Principal, UserDetails{

	private static final long serialVersionUID = -375643419780551172L;

	private final String username;

	
	public CustomPrincipalDTO(String username) {
		this.username = username;

	}

	@Override
	public String getName() {
		return getUsername();
	}

	@Override
	public String toString() {
		return "CustomPrincipalDTO [username=" + username + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

}
