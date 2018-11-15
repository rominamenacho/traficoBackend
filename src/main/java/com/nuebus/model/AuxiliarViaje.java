/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuebus.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name="auxiliar_viaje_esp")
public class AuxiliarViaje {
          
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUXILIAR_VIAJE_ID_SEQ")
    @SequenceGenerator(name="AUXILIAR_VIAJE_ID_SEQ", sequenceName = "AUXILIAR_VIAJE_ID_SEQ", allocationSize = 100)
    private Long id;
       
        
    java.util.Date inicio;
    java.util.Date fin;
    
    
    
   
    @ManyToOne( fetch = FetchType.LAZY)
    @NotFound(
        action = NotFoundAction.IGNORE)
    @JoinColumns({
        @JoinColumn(
            name = "id_cho_emp_codigo_aux",
            referencedColumnName = "cho_emp_codigo"),
        @JoinColumn(
            name = "id_cho_codigo_aux",
            referencedColumnName = "cho_codigo")
    })
    Chofer auxiliar;
    
    
    @ManyToOne
    @JoinColumn( name = "ID_VIAJE" )
    private ViajeEspecial viajeEspecial; 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }
   

    public ViajeEspecial getViajeEspecial() {
        return viajeEspecial;
    }

    public void setViajeEspecial(ViajeEspecial viajeEspecial) {
        this.viajeEspecial = viajeEspecial;
    }

    public Chofer getAuxiliar() {
        return auxiliar;
    }

    public void setAuxiliar(Chofer auxiliar) {
        this.auxiliar = auxiliar;
    }
    
    
}
