package com.hpe.springboot.training.web;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello-api/v1")
public class EnglishHelloResource implements HelloResource {

	@Override
	public String hello() {
		return "Hello World";
	}

	@Override
	public String greet() {
		return "Have a nice day";
	}

	@Override
	public String helloUser(@PathVariable("username") String user) {
		return "Hello, " + user + "!";
	}

}
