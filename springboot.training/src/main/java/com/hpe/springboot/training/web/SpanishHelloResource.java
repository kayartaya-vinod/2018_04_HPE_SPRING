package com.hpe.springboot.training.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello-api/v2")
public class SpanishHelloResource implements HelloResource {

	@Override
	public String hello() {
		return "Hola munda";
	}

	@Override
	public String greet() {
		return "Have a nice day";
	}

	@Override
	public String helloUser(String user) {
		return "Hola, " + user + "!!";
	}

}
