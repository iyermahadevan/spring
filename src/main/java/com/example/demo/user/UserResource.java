package com.example.demo.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

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

	// TBD: Get list of all users filtered
	// Current this causes the other methods to break, requires definition of FilterProvider even if no filtering 
	// Until the @JsonFilter annotation is uncommented in User this will not be activated
	@GetMapping(path="/users-filter")
	public MappingJacksonValue getAllUsersFilter() {
		List<User> allUsers = service.getAll();
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
				.filterOutAllExcept("id", "name");
		FilterProvider filters = new SimpleFilterProvider()
				.addFilter("UsersFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(allUsers);
		mapping.setFilters(filters);
		return mapping;
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
