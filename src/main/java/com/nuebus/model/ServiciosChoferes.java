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
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "servicios_choferes")
public class ServiciosChoferes {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHOFER_SERVICIO_ID_SEQ")
    @SequenceGenerator(name = "CHOFER_SERVICIO_ID_SEQ", sequenceName = "CHOFER_SERVICIO_ID_SEQ", allocationSize = 100)
    private Long id;
    @NotNull
    @NotBlank
    private String empresa;
    @NotNull
    @NotBlank
    private String linea_codigo;
    @NotNull
    private java.util.Date fecha_hora;
    @NotNull
    private int refuerzo;

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
    private Chofer chofer;

 
    public ServiciosChoferes() {
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

    public int getRefuerzo() {
        return refuerzo;
    }

    public void setRefuerzo(int refuerzo) {
        this.refuerzo = refuerzo;
    }

    public Chofer getChofer() {
        return chofer;
    }

    public void setChofer(Chofer chofer) {
        this.chofer = chofer;
    }

  

}
