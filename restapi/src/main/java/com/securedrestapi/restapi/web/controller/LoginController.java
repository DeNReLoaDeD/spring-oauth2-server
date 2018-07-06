package com.securedrestapi.restapi.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.securedrestapi.restapi.web.client.IdentityProviderTokenRestClient;
import com.securedrestapi.restapi.web.dto.common.RestResponse;
import com.securedrestapi.restapi.web.dto.request.TokenRequestDTO;
import com.securedrestapi.restapi.web.dto.response.TokenResponseDTO;

@RestController
@RequestMapping(value = "/token", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginController {
	
	
	@Autowired
	private IdentityProviderTokenRestClient client;
	
	@PostMapping
	public RestResponse<TokenResponseDTO> doToken(@Valid @RequestBody TokenRequestDTO loginDTO) throws Exception {
		return new RestResponse<>(client.token(loginDTO.toMap()));
	}
}
