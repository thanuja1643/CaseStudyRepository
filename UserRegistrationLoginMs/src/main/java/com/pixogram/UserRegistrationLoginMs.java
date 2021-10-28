package com.pixogram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@SpringBootApplication
public class UserRegistrationLoginMs {
		

	
		public static void main(String[] args) {
			SpringApplication.run(UserRegistrationLoginMs.class, args);
		}
		/*
		 * @PostConstruct public void initRoles() {
		 * 
		 * Role r1 = new Role(); r1.setName(RoleNames.ROLE_USER.name());
		 * roleRepository.save(r1); Role r2 = new Role();
		 * r1.setName(RoleNames.ROLE_ADMIN.name()); roleRepository.save(r2); }
		 */
	}



