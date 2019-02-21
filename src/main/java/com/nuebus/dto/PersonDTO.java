package com.nuebus.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

/**
 * Created by mouradzouabi on 04/12/15.
 */
public class PersonDTO {
	
	 @NotNull
     private Long id;

    String firstname;

    String lastname;

    Integer age;

    Date dateOfBirth;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    
    
}
