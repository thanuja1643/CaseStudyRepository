package com.pixo.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class PixoEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PixoEurekaServerApplication.class, args);
	}

}
