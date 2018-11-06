/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuebus.repository;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.nuebus.model.QDiagramacion;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Usuario
 */
public class DiagramacionRepositoryImpl implements DiagramacionRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public int countCustom() {

        JPQLQuery query = new JPAQuery(em);
        return (int) query.from(QDiagramacion.diagramacion).where(QDiagramacion.diagramacion.id.isNotNull()).count();

    }
}
