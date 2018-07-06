package com.authserver.auth.web.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.authserver.auth.persistence.model.User;

public class UserDTO {
	
	private Long userId;
	
	private String username;
	
	private Boolean enabled;

	public UserDTO() {}

	public UserDTO(Long userId, String username, Boolean enabled) {
		super();
		this.userId = userId;
		this.username = username;
		this.enabled = enabled;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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
		return "UserDTO [userId=" + userId + ", username=" + username + ", enabled=" + enabled + "]";
	}

	public static UserDTO toDTO(User entity) {
		UserDTO dto = new UserDTO();
		dto.setUserId(entity.getId());
		dto.setUsername(entity.getUsername());
		dto.setEnabled(entity.isEnabled());
		return dto;
	}
	
	public static List<UserDTO> toDTO(List<User> entity){
		return entity.stream().map(user -> toDTO(user)).collect(Collectors.toList());
	}

}
