/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuebus.repository;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.nuebus.model.QVuelta;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Usuario
 */
public class VueltaRepositoryImpl implements VueltaRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public int countCustom() {

        JPQLQuery query = new JPAQuery(em);
        return (int) query.from(QVuelta.vuelta).where(QVuelta.vuelta.id.isNotNull()).count();

    }
}
