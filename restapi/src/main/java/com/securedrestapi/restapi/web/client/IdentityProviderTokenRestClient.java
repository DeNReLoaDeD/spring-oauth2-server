package com.securedrestapi.restapi.web.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.securedrestapi.restapi.configuration.feign.IdentityProviderAccountRestClientConfiguration;
import com.securedrestapi.restapi.web.dto.response.TokenResponseDTO;

/**
 * REST client interface for acess to digital backend OUATH services
 */
@FeignClient(name = "identityProviderTokenRestClient",
			 url = "${url.identity.base}${url.identity.oauth}",
			 configuration = IdentityProviderAccountRestClientConfiguration.class)
public interface IdentityProviderTokenRestClient {

	/**
	 * Makes a request for an oauth token against identity provider
	 * 
	 * @param loginDTO
	 *            Map with the values needed
	 * @return the token info
	 */
	@RequestMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, method = RequestMethod.POST, value = "/token")
	public TokenResponseDTO token(@RequestBody MultiValueMap<String, String> loginDTO);

}
