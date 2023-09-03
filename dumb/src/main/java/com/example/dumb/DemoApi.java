package com.example.dumb;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient (name = "demo-service")
public interface DemoApi {
	
	@GetMapping ("/api/demo")
	String demo();
}
