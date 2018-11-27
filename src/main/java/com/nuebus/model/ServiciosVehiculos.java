/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuebus.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "servicios_vehiculos")
public class ServiciosVehiculos implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SERVICIO_VEHICULO_ID_SEQ")
    @SequenceGenerator(name = "SERVICIO_VEHICULO_ID_SEQ", sequenceName = "SERVICIO_VEHICULO_ID_SEQ", allocationSize = 100)
    private Long id;
    @NotNull
    @NotBlank
    private String empresa;
    @NotNull
    @NotBlank
    private String interno;
    @NotNull
    @NotBlank
    private String linea_codigo;
    @NotNull
    private java.util.Date fecha_hora;
    @NotNull
    private int refuerzo;

  
    @Valid
    @EmbeddedId
     @JoinColumns({
        @JoinColumn(
            name = "empresa",
            referencedColumnName = "vehEmpCodigo"),
        @JoinColumn(
            name = "interno ",
            referencedColumnName = "vehInterno")
    })    
    private VehiculoPK vehiculoPK;

    public ServiciosVehiculos() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getLinea_codigo() {
        return linea_codigo;
    }

    public void setLinea_codigo(String linea_codigo) {
        this.linea_codigo = linea_codigo;
    }

    public Date getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(Date fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public String getInterno() {
        return interno;
    }

    public void setInterno(String interno) {
        this.interno = interno;
    }

    
    public int getRefuerzo() {
        return refuerzo;
    }

    public void setRefuerzo(int refuerzo) {
        this.refuerzo = refuerzo;
    }

  

    public VehiculoPK getVehiculoPK() {
        return vehiculoPK;
    }

    public void setVehiculoPK(VehiculoPK vehiculoPK) {
        this.vehiculoPK = vehiculoPK;
    }

    public Vehiculo encontrarVehiculoXPK() {
        Vehiculo vehiculo = null;
        if (this.getVehiculoPK() != null) {
            vehiculo.getVehiculoPK().equals(getVehiculoPK());
        }
        return vehiculo;
    }

}
