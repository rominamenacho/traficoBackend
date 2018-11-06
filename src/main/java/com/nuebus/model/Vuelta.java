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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "vuelta")
public class Vuelta implements Serializable {

    private static long serialVersionUID = 1L;

    @GenericGenerator(
            name = "vueltasSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "vuelta_seq")
                ,
                    @Parameter(name = "initial_value", value = "1")
                ,
                    @Parameter(name = "increment_size", value = "1")
            }
    )

    @Id
    @GeneratedValue(generator = "vueltasSequenceGenerator")
    private Long id;

    @NotNull
    private java.util.Date fechaHoraSalida;
    @NotNull
    private java.util.Date fechaHoraLlegada;
    @NotNull
    private java.util.Date fechaHoraSalidaTaller;
    @NotNull
    private java.util.Date fechaHoraLlegadaTaller;

    private int kmTotales;

    private java.util.Date duracionTotal;

    private String observaciones;
    @NotNull
    private String empCodigo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vuelta", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ServiciosChoferes> serviciosChoferes = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vuelta", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ServiciosVehiculos> serviciosVehiculos = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "id_diagramacion")
    private Diagramacion diagramacion;

    public Vuelta() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaHoraSalida() {
        return fechaHoraSalida;
    }

    public void setFechaHoraSalida(Date fechaHoraSalida) {
        this.fechaHoraSalida = fechaHoraSalida;
    }

    public Date getFechaHoraLlegada() {
        return fechaHoraLlegada;
    }

    public void setFechaHoraLlegada(Date fechaHoraLlegada) {
        this.fechaHoraLlegada = fechaHoraLlegada;
    }

    public Date getFechaHoraSalidaTaller() {
        return fechaHoraSalidaTaller;
    }

    public void setFechaHoraSalidaTaller(Date fechaHoraSalidaTaller) {
        this.fechaHoraSalidaTaller = fechaHoraSalidaTaller;
    }

    public Date getFechaHoraLlegadaTaller() {
        return fechaHoraLlegadaTaller;
    }

    public void setFechaHoraLlegadaTaller(Date fechaHoraLlegadaTaller) {
        this.fechaHoraLlegadaTaller = fechaHoraLlegadaTaller;
    }

    public int getKmTotales() {
        return kmTotales;
    }

    public void setKmTotales(int kmTotales) {
        this.kmTotales = kmTotales;
    }

    public Date getDuracionTotal() {
        return duracionTotal;
    }

    public void setDuracionTotal(Date duracionTotal) {
        this.duracionTotal = duracionTotal;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Set<ServiciosChoferes> getServiciosChoferes() {
        return serviciosChoferes;
    }

    public void setServiciosChoferes(Set<ServiciosChoferes> ServiciosChoferes) {
        this.serviciosChoferes = ServiciosChoferes;
    }

    public Set<ServiciosVehiculos> getServiciosVehiculos() {
        return serviciosVehiculos;
    }

    public void setServiciosVehiculos(Set<ServiciosVehiculos> ServiciosVehiculos) {
        this.serviciosVehiculos = ServiciosVehiculos;
    }

    public String getEmpCodigo() {
        return empCodigo;
    }

    public void setEmpCodigo(String empCodigo) {
        this.empCodigo = empCodigo;
    }

    public Diagramacion getDiagramacion() {
        return diagramacion;
    }

    public void setDiagramacion(Diagramacion diagramacion) {
        this.diagramacion = diagramacion;
    }

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }

}
