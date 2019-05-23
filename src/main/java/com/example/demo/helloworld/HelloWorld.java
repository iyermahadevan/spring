package com.example.demo.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

// Controller
@RestController
public class HelloWorld {

	// GET
	// URI : /hello-world
	// return : "Hello World"
	@GetMapping(path="/hello-world")
	public String helloWorld() {
		return "Hello World1";
	}
	
	@GetMapping(path="/hello-world-bean")
	public HelloWorldBean getHelloWorldBean() {
		return new HelloWorldBean("Hello World Bean");
	}
	
	@GetMapping(path = "/hello-world/path-variable/{name}")
	public HelloWorldBean getHelloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World %s", name));
	}
}
