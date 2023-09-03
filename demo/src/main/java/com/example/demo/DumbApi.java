package com.example.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient (name = "DUMB-SERVICE")
public interface DumbApi {
	
	@GetMapping ("/api/dumb")
	String dumb();
}
