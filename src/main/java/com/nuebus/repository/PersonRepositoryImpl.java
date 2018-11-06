package com.nuebus.repository;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.nuebus.model.QPerson;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PersonRepositoryImpl implements PersonRepositoryCustom {

	@PersistenceContext
	private EntityManager em;

	@Override
	public int countCustom() {
		JPQLQuery query = new JPAQuery(em);
		return (int) query.from(QPerson.person).where(QPerson.person.id.isNotNull()).count();
	}

}
