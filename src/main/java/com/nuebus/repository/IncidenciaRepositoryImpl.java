/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuebus.repository;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.nuebus.model.QIncidencia;

/**
 *
 * @author Usuario
 */
public class IncidenciaRepositoryImpl implements IncidenciaRepositoryCustom{
    @PersistenceContext
	private EntityManager em;

	
        @Override
	public int countCustom() {
	
           JPQLQuery query = new JPAQuery(em);
            return (int) query.from(QIncidencia.incidencia).where(QIncidencia.incidencia.id.isNotNull()).count();
                     
        }  

}
