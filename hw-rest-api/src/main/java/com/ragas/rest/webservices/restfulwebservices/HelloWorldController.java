package com.ragas.rest.webservices.restfulwebservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ragas.rest.webservices.restfulwebservices.environment.InstanceInformationService;

@RestController
public class HelloWorldController {

	@Autowired
	private InstanceInformationService service;

	@GetMapping(path = "/")
	public String healthCheck() {
		return "{instance-healthy:true}";
	}

	// '/whoami'	
	@GetMapping(path = "/whoami")
	public String whoAmI() {
		return service.getSystemInfo();
	}

	//'/hello-world'
	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return service.retrieveInstanceInfo();
	}

	//'/hello-world-bean'
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Welcome to Docker World");
	}

	//'/hello-world/Ragas'
	@GetMapping(path = "/hello-world/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World, %s", name));
	}
}
