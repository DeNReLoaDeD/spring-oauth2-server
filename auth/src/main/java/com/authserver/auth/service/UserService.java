package com.authserver.auth.service;

import com.authserver.auth.persistence.model.User;
import com.authserver.auth.web.dto.response.UserDTO;
import com.authserver.auth.web.exception.UsernameAlreadyExistsException;

public interface UserService {
	
	/**
	 * Add user to database
	 * @param user
	 * @return User created
	 * @throws UsernameAlreadyExistsException when username already exists
	 */
	public UserDTO addUser(User user) throws UsernameAlreadyExistsException;
	
	/**
	 * Disable given user
	 * @param userId
	 */
	public void disableUser(Long userId);
	
	/**
	 * Get user by id
	 * @param userId
	 * @return
	 */
	public UserDTO getUserById(Long userId);

	/**
	 * Return the list of users stored in database
	 * @return
	 */
	Iterable<UserDTO> getAllUsers();

	/**
	 * Delete user by id
	 * @param userId
	 */
	void deleteUser(Long userId);

}
