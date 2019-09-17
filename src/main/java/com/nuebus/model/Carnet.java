package com.nuebus.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author Valeria
 */

@Entity
@Table(name= "carnets")
public class Carnet implements Serializable {

    private static final long serialVersionUID = 1L;    
    
    @GenericGenerator(
            name = "carnetsSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "carnets_seq")
                ,
                    @Parameter(name = "initial_value", value = "1")
                ,
                    @Parameter(name = "increment_size", value = "1")
            }
    )	
    @Id
    @GeneratedValue(generator = "carnetsSequenceGenerator")
    Long id;     
    
    Integer tipo;
    Date fechaEmision;
    Date fechaVenc;
    String numeroCarnet;    
    String observaciones;
       
   
    
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    
    public Integer getTipo() {
            return tipo;
    }

    public void setTipo(Integer tipo) {
            this.tipo = tipo;
    }


    @Override
    public String toString() {
            return "Carnet [id=" + id + ", tipo=" + tipo + ", fechaEmision=" + fechaEmision + ", fechaVenc=" + fechaVenc
                            + ", numeroCarnet=" + numeroCarnet + ", observaciones=" + observaciones + "]";
    }

    
       
}
