package com.rogge.command;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages={"com.rogge.common","com.rogge.command"})
public class CmApplication {

	public static void main(String[] args) {
		SpringApplication.run(CmApplication.class, args);
	}

	@Bean
	public RestTemplate RestTemplate(){
		return new RestTemplate();
	}
}
