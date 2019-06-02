package com.example.demo.site;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RestController
public class SiteResource {

	@Autowired
	private SiteDaoService service;
	
	@GetMapping(path="/sites")
	public List<Site> getAllSites() {
		return service.getAll();
	}
	
	@GetMapping(path="/sites/{id}")
	public Resource<Site> getSite(@PathVariable int id) {
		Site site = service.findOne(id);
		if(site == null) {
			throw new SiteNotFoundException("id=" + id);
		}
		Resource<Site> resource = new Resource<Site>(site);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllSites());
		resource.add(linkTo.withRel("all-sites"));
		
		return resource;
	}

	@PostMapping(path="/sites")
	public ResponseEntity<Object> addSite(@Valid @RequestBody Site site) {
		Site savedSite = service.save(site);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedSite.getId())
				.toUri();
		return ResponseEntity.created(location)
				.body(savedSite);
	}

	@DeleteMapping(path="/sites/{id}")
	public Site deleteSite(@PathVariable int id) {
		Site site = service.deleteOne(id);
		if(site == null) {
			throw new SiteNotFoundException("id=" + id);
		}
		return site;
	}


}
