package com.nuebus.repository;

import com.nuebus.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long>, PersonRepositoryCustom {

}
