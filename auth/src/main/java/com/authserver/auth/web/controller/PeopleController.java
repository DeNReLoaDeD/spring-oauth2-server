package com.authserver.auth.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.authserver.auth.service.UserService;
import com.authserver.auth.web.dto.response.UserDTO;
import com.authserver.auth.web.exception.UserNotFoundException;

@RestController
@RequestMapping("/people")
public class PeopleController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Iterable<UserDTO>> getPeople() {
		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> getPerson(@PathVariable long id) throws UserNotFoundException {
		UserDTO person = userService.getUserById(id);

		if (person != null) {
			return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletePerson(@PathVariable long id) {
		userService.deleteUser(id);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
