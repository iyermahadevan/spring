package com.example.demo.user;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

// @JsonFilter("UsersFilter")
// Need to find a way to do this so that methods other than getUsersFilter are not impacted
@ApiModel(description="All details about the user")
@Entity
public class User {

	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private Date birthDate;
	private String code;
	
	// Hibernate needs this constructor
	public User() {
		
	}

	public User(Integer id, String name, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.code = "code123";
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Size(min=2, message="Name should have at least 2 characters")
	@ApiModelProperty(value="Name should have at least 2 characters", example="u123")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Past
	@ApiModelProperty(value="Birth date should be in the past not in future")
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	@JsonIgnore
	public String getCode() {
		return code;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}

}
