package com.rogge.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class UmApplication {

	public static void main(String[] args) {
		SpringApplication.run(UmApplication.class, args);
	}

	@Bean
	public RestTemplate RestTemplate(){
		return new RestTemplate();
	}
}
