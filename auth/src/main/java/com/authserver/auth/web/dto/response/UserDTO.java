package com.authserver.auth.web.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.authserver.auth.persistence.model.User;

public class UserDTO {
	
	private String username;
	
	private Boolean enabled;

	public UserDTO() {}
	
	public UserDTO(String username, Boolean enabled) {
		super();
		this.username = username;
		this.enabled = enabled;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "UserDTO [username=" + username + ", enabled=" + enabled + "]";
	}
	
	public static UserDTO toDTO(User entity) {
		UserDTO dto = new UserDTO();
		dto.setUsername(entity.getUsername());
		dto.setEnabled(entity.isEnabled());
		return dto;
	}
	
	public static List<UserDTO> toDTO(List<User> entity){
		return entity.stream().map(user -> toDTO(user)).collect(Collectors.toList());
	}

}
