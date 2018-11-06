package com.nuebus.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Person extends AbstractEntityVersion {
    
        private static final long serialVersionUID = 1L;
    
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Person_id_seq")
        @SequenceGenerator(name="Person_id_seq", sequenceName = "Person_ID_SEQ", allocationSize = 100)
        private Integer id;
	

	String firstname;

	String lastname;

	Integer age;

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
}
