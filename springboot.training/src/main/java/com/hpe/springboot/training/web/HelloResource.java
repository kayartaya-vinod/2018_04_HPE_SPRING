package com.hpe.springboot.training.web;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

public interface HelloResource {
	
	@RequestMapping("/hello")
	public String hello();
	@RequestMapping("/greet")
	public String greet();
	
	@RequestMapping("/hello/{username}")
	public String helloUser(@PathVariable("username") String user);
}
