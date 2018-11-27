/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuebus.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "diagramacion")
public class Diagramacion implements Serializable {

     private static final long serialVersionUID = 1L;
    
    @GenericGenerator(
            name = "diagramacionSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "diagramacion_seq")
                ,
                    @Parameter(name = "initial_value", value = "1")
                ,
                    @Parameter(name = "increment_size", value = "1")
            }
    )

    @Id
    @GeneratedValue(generator = "diagramacionSequenceGenerator")
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 4)
    private String empCodigo;
    @NotNull
    private java.util.Date fechaDesde;
    @NotNull
    private java.util.Date fechaHasta;
    @NotNull
    private String observaciones;

  

    public Diagramacion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmpCodigo() {
        return empCodigo;
    }

    public void setEmpCodigo(String empCodigo) {
        this.empCodigo = empCodigo;
    }

    public Date getFecha_desde() {
        return getFechaDesde();
    }

    public void setFecha_desde(Date fecha_desde) {
        this.setFechaDesde(fecha_desde);
    }

    public Date getFecha_hasta() {
        return getFechaHasta();
    }

    public void setFecha_hasta(Date fecha_hasta) {
        this.setFechaHasta(fecha_hasta);
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

  
    /**
     * @return the fechaDesde
     */
    public java.util.Date getFechaDesde() {
        return fechaDesde;
    }

    /**
     * @param fechaDesde the fechaDesde to set
     */
    public void setFechaDesde(java.util.Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    /**
     * @return the fechaHasta
     */
    public java.util.Date getFechaHasta() {
        return fechaHasta;
    }

    /**
     * @param fechaHasta the fechaHasta to set
     */
    public void setFechaHasta(java.util.Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

}
