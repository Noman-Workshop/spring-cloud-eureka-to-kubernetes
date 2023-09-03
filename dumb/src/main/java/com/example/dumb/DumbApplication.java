package com.example.dumb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
@RestController
@EnableFeignClients
@EnableDiscoveryClient
public class DumbApplication {
	
	private final DemoApi demoApi;
	private final DiscoveryClient discoveryClient;
	
	@Value("${spring.application.id}")
	private String id;
	
	public DumbApplication(DemoApi demoApi, DiscoveryClient discoveryClient) {
		this.demoApi = demoApi;
		this.discoveryClient = discoveryClient;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(DumbApplication.class, args);
	}
	
	@GetMapping ("/dumb")
	public String dumb() {
		return "hello from dumb: " + id;
	}
	
	@GetMapping ("/demo")
	public String demo() {
		return demoApi.demo() + " through dumb";
	}
	
	@RequestMapping ("/getservicedetail")
	public String getServiceDetail(@RequestParam (value = "servicename", defaultValue = "") String servicename) {
		return "Service [" + servicename + "]'s instance list : " + discoveryClient.getInstances(servicename);
	}
	
	@RequestMapping ("/services")
	public String services() {
		return this.discoveryClient.getServices()
		                           .toString() + ", " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
}
