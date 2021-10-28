package com.pixo.followers;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import brave.sampler.Sampler;

@Component
public class AppConfig {
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
}
