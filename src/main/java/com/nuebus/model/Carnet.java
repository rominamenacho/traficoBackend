/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuebus.model;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 *
 * @author Valeria
 */

@Entity
//public class Carnet extends AbstractEntity{    
public class Carnet extends AbstractEntityVersion{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Carnet_id_seq")
    @SequenceGenerator(name="Carnet_id_seq", sequenceName = "Carnet_ID_SEQ", allocationSize = 100)
    private Integer id;
    
    int tipo;
    Date fechaEmision;
    Date fechaVenc;
    String numeroCarnet;    
    String observaciones;
    
    
    /*
         @NotFound(
        action = NotFoundAction.IGNORE)
    */
    
    @ManyToOne( fetch = FetchType.LAZY)
    @NotFound(
        action = NotFoundAction.IGNORE)
    @JoinColumns({
        @JoinColumn(
            name = "cho_emp_codigo",
            referencedColumnName = "cho_emp_codigo"),
        @JoinColumn(
            name = "cho_codigo",
            referencedColumnName = "cho_codigo")
    })
    Chofer chofer;

    public Carnet(){}
    
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Chofer){
            Carnet carnet = (Carnet) obj;     
 
            if( carnet.getId()== getId() ){
                return false;
            } 
            return true;
        }
 
        return false;
    }
 
    @Override
    public int hashCode() {
        return  String.valueOf(getId()).hashCode();
    }
    
    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Date getFechaVenc() {
        return fechaVenc;
    }

    public void setFechaVenc(Date fechaVenc) {
        this.fechaVenc = fechaVenc;
    }  
    
   
    public Chofer getChofer() {
            return this.chofer;
    }

    public void setChofer(Chofer chofer) {
            this.chofer = chofer;
    }

    public String getNumeroCarnet() {
        return numeroCarnet;
    }

    public void setNumeroCarnet(String numeroCarnet) {
        this.numeroCarnet = numeroCarnet;
    }  

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


	@Override
	public String toString() {
		return "Carnet [id=" + id + ", tipo=" + tipo + ", fechaEmision=" + fechaEmision + ", fechaVenc=" + fechaVenc
				+ ", numeroCarnet=" + numeroCarnet + ", observaciones=" + observaciones + "]";
	}
   
    
       
}
