package com.example.demo.site;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class SiteDaoService {

	static List<Site> sites = new ArrayList<Site>();
	static int siteCount = 3;
	
	// Initialize sites
	static {
		sites.add(new Site(1, "CS1", new Date(), "CF1"));
		sites.add(new Site(2, "CS2", new Date(), "CF2"));
		sites.add(new Site(3, "CS3", new Date(), "CF3"));
	}
	
	// List Sites
	public List<Site> getAll() {
		return sites;
	}
	
	// Add Site
	public Site save(Site site) {
		if(site.getId() == null) {
			site.setId(++siteCount);
		}
		sites.add(site);
		return site;
	}
	
	// Find Site
	public Site findOne(int id) {
		for(Site site: sites) {
			if(site.getId() == id)
				return site;
		}
		return null;
	}
	
	// Find Site
	public Site deleteOne(int id) {
		Iterator<Site> iterator = sites.iterator();
		while(iterator.hasNext()) {
			Site site = iterator.next();
			if(site.getId() == id) {
				iterator.remove();
				return site;
			}
		}
		return null;
	}

}
