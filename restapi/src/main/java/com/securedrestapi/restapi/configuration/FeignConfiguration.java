package com.securedrestapi.restapi.configuration;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * Feign clients configuration
 */
@Configuration
@EnableFeignClients(basePackages = "com.securedrestapi.restapi.web.client")
public class FeignConfiguration {

}
