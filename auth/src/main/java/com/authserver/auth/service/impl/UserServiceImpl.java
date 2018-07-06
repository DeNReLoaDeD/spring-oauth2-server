package com.authserver.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.authserver.auth.persistence.UserRepository;
import com.authserver.auth.persistence.model.User;
import com.authserver.auth.service.UserService;
import com.authserver.auth.web.dto.response.UserDTO;
import com.authserver.auth.web.exception.UserNotFoundException;
import com.authserver.auth.web.exception.UsernameAlreadyExistsException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public UserDTO addUser(User user) throws UsernameAlreadyExistsException {
		
		//check if user already exists
		if(userRepository.findOneByUsername(user.getUsername()) != null) {
			throw new UsernameAlreadyExistsException("The user already exists with the same name " + user.getUsername());
		}
		
		user.setEnabled(true);
		user.setPassword(encoder.encode(user.getPassword()));
		return UserDTO.toDTO(userRepository.save(user));
	}

	@Override
	public void disableUser(Long userId) {
		User user = userRepository.findOne(userId);
		user.setEnabled(false);
		userRepository.save(user);
	}

	@Override
	public UserDTO getUserById(Long userId) throws UserNotFoundException {
		
		User user = userRepository.findOne(userId);
		
		if(user == null) {
			throw new UserNotFoundException("User with id " + userId + " not exists");
		}
		
		return UserDTO.toDTO(user);
	}
	
	@Override
	public Iterable<UserDTO> getAllUsers() {
		return UserDTO.toDTO(userRepository.findAll());
	}
	
	@Override
	public void deleteUser(Long userId) {
		userRepository.delete(userId);;
	}

}
