package com.example.demo;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

@Component
public class FeignClientRequestInterceptor implements RequestInterceptor {
	
	@Override
	public void apply(RequestTemplate template) {
		String host = template.feignTarget().url().toLowerCase();
		template.target(host);
	}
}
