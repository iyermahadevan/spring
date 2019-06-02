package com.example.demo.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

@RestController
public class UserResource {

	@Autowired
	UserDaoService service;
	
	// Get list of all users
	@GetMapping(path="/users")
	public List<User> getAllUsers() {
		return service.getAll();
	}
	
	// Get specific user
	@GetMapping(path="/users/{id}")
	public Resource<User> getUser(@PathVariable int id) {
		User user = service.findOne(id);
		if(user == null) {
			throw new UserNotFoundException("id=" + id);
		}
		Resource<User> resource = new Resource<User>(user);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUsers());
		resource.add(linkTo.withRel("all-users"));
		return resource;
	}
	
	// Add a new user
	@PostMapping(path="/users")
	public ResponseEntity<Object> addUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location)
				.body(savedUser);
	}

	
	// Delete specific user
	@DeleteMapping(path="/users/{id}")
	public User deleteUser(@PathVariable int id) {
		User user = service.deleteOne(id);
		if(user == null) {
			throw new UserNotFoundException("id=" + id);
		}
		return user;
	}
	

}
