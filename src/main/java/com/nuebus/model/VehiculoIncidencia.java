
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
@Table(name="Vehiculo_Incidencia")
public class VehiculoIncidencia implements Serializable {  
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VEHICULO_INCIDENCIA_ID_SEQ")
    @SequenceGenerator(name="VEHICULO_INCIDENCIA_ID_SEQ", sequenceName = "VEHICULO_INCIDENCIA_ID_SEQ", allocationSize = 100)
    private Long id;
       
    
    java.util.Date inicio;
    java.util.Date fin;    
 
    @ManyToOne   
    @JoinColumns({
        @JoinColumn(
            name = "id_veh_emp_codigo ",
            referencedColumnName = "vehEmpCodigo"),
        @JoinColumn(
            name = "id_veh_interno ",
            referencedColumnName = "vehInterno")
    })    
    private Vehiculo vehiculo;
       
   
    @ManyToOne
    @JoinColumn( name = "ID_INCIDENCIA" )
    private Incidencia incidencia;  

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
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

    public Incidencia getIncidencia() {
        return incidencia;
    }

    public void setIncidencia(Incidencia incidencia) {
        this.incidencia = incidencia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }       
    
}
