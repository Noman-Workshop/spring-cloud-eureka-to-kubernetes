package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableFeignClients
@EnableDiscoveryClient
public class DemoApplication {
	
	private final DumbApi dumbApi;
	
	@Value("${spring.application.id}")
	private String id;
	
	public DemoApplication(DumbApi dumbApi) {
		this.dumbApi = dumbApi;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@GetMapping ("/demo")
	public String demo() {
		return "hello from demo: " + id;
	}
	
	@GetMapping ("/dumb")
	public String dumb() {
		return dumbApi.dumb() + " through demo";
	}
	
}
