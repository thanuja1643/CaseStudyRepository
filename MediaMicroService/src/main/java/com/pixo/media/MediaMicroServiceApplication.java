package com.pixo.media;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.pixo.media.dto.FileStorageProperties;

@EnableConfigurationProperties({
    FileStorageProperties.class
})
@EnableEurekaClient
@SpringBootApplication
public class MediaMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediaMicroServiceApplication.class, args);
	}

}
