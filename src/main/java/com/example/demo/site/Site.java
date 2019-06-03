package com.example.demo.site;

import java.util.Date;

import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

public class Site {

	public Site(Integer id, String credentials, Date creationDate, String config) {
		super();
		this.id = id;
		this.credentials = credentials;
		this.creationDate = creationDate;
		this.config = config;
	}
	@Override
	public String toString() {
		return "Site [id=" + id + ", credentials=" + credentials + ", creationDate=" + creationDate + ", config="
				+ config + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCredentials() {
		return credentials;
	}
	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	@Size(min=2, message="Site configuration should have at least 2 characters")
	@ApiModelProperty(value="The configuration for the site", example="www.example.com")
	public String getConfig() {
		return config;
	}
	public void setConfig(String config) {
		this.config = config;
	}
	private Integer id;
	private String credentials;
	private Date creationDate;
	private String config;
}
