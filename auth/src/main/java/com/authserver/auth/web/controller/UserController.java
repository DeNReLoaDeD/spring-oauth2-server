package com.authserver.auth.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.authserver.auth.persistence.model.User;
import com.authserver.auth.service.UserService;
import com.authserver.auth.web.dto.response.UserDTO;
import com.authserver.auth.web.exception.UsernameAlreadyExistsException;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<UserDTO> addPerson(@RequestBody User user) throws UsernameAlreadyExistsException {
		return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
	}

}
