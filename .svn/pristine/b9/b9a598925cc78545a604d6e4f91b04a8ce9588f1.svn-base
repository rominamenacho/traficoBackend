package com.nuebus.model;

import java.io.Serializable;
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
 * @author Valeria
 */

@Entity
@Table(name="Chofer_Incidencia")
public class ChoferIncidencia implements Serializable{
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHOFER_INCIDENCIA_ID_SEQ")
    @SequenceGenerator(name="CHOFER_INCIDENCIA_ID_SEQ", sequenceName = "CHOFER_INCIDENCIA_ID_SEQ", allocationSize = 100)
    private Long id;
       
    
    
    java.util.Date inicio;
    java.util.Date fin;
    
    
    @ManyToOne( fetch = FetchType.LAZY)
    @NotFound(
        action = NotFoundAction.IGNORE)
    @JoinColumns({
        @JoinColumn(
            name = "id_cho_emp_codigo",
            referencedColumnName = "cho_emp_codigo"),
        @JoinColumn(
            name = "id_cho_codigo",
            referencedColumnName = "cho_codigo")
    })
    Chofer chofer;
    
     
    @ManyToOne
    @JoinColumn( name = "ID_INCIDENCIA" )
    private Incidencia incidencia;    
    
    
    public Chofer getChofer() {
        return chofer;
    }

    public void setChofer(Chofer chofer) {
        this.chofer = chofer;
    }
    
   
    public Incidencia getIncidencia() {
        return incidencia;
    }

    public void setIncidencia(Incidencia incidencia) {
        this.incidencia = incidencia;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
   
    
    public String toString(){
        
        return "chofer.getChoferPK().getCho_emp_codigo()= " + chofer.getChoferPK().getCho_emp_codigo()
         +  "; chofer.getChoferPK().getCho_codigo()= " + chofer.getChoferPK().getCho_codigo()
         +  ";incidencia.getId()= " + incidencia.getId() 
         +  ";inicio= " + inicio + ";fin=" + inicio;    
    }   
    
   
}
