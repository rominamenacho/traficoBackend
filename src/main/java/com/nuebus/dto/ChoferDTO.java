package com.nuebus.dto;

import java.util.ArrayList;
import java.util.List;
import com.nuebus.model.ChoferPK;
import java.util.Date;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Valeria
 */
public class ChoferDTO {    
    
    private ChoferPK choferPK;
    
    @NotBlank
    @NotNull
    @Size(max = 40)    
    private String nombre = "";
    
    @Size(max = 60)    
    private String funcion = "";
    
    
    @Size(max = 50)    
    private String telefono = "";
    
    @NotBlank    
    @Size(max = 11)    
    private String documento = "";
    
    @Size(max = 300)
    private String direccion = "";
    
    @Size(max = 20)    
    private String codigoPostal = "";    
    
    @Size(max = 255)    
    private String observaciones = "";
    
    @Size(max = 50)    
    private String grupoSanguineo = "";
        
    @Size(max = 50)    
    private String telefonoEmergencia = "";
    
    @Digits(integer = 2,fraction = 0)    
    private int codigoDoc = 0;        
    
    @Digits(integer = 10,fraction = 0)    
    private long legajo;        
    
    
    private java.util.Date fechaNacimiento = null;
    
    @Digits(integer = 1,fraction = 0)    
    private int estado = 0;
    
    @Digits(integer = 1,fraction = 0)    
    private Integer tipoChofer = 0; 
    
    
    private Integer idAux;
    
    @Size(max = 255)    
    private String foto;
    
  
    List<VencimientoCalculadoDTO> vencimientos = new ArrayList<>();

    public ChoferPK getChoferPK() {
        return choferPK;
    }

    public void setChoferPK(ChoferPK choferPK) {
        this.choferPK = choferPK;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getGrupoSanguineo() {
        return grupoSanguineo;
    }

    public void setGrupoSanguineo(String grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }

    public String getTelefonoEmergencia() {
        return telefonoEmergencia;
    }

    public void setTelefonoEmergencia(String telefonoEmergencia) {
        this.telefonoEmergencia = telefonoEmergencia;
    }

    public int getCodigoDoc() {
        return codigoDoc;
    }

    public void setCodigoDoc(int codigoDoc) {
        this.codigoDoc = codigoDoc;
    }

    public long getLegajo() {
        return legajo;
    }

    public void setLegajo(long legajo) {
        this.legajo = legajo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Integer getTipoChofer() {
        return tipoChofer;
    }

    public void setTipoChofer(Integer tipoChofer) {
        this.tipoChofer = tipoChofer;
    }   

    public Integer getIdAux() {
        return idAux;
    }

    public void setIdAux(Integer idAux) {
        this.idAux = idAux;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public List<VencimientoCalculadoDTO> getVencimientos() {
        return vencimientos;
    }

    public void setVencimientos(List<VencimientoCalculadoDTO> vencimientos) {
        this.vencimientos = vencimientos;
    }

    @Override
    public String toString() {
        return "ChoferDTO{" + "choferPK=" + choferPK + ", nombre=" + nombre + ", funcion=" + funcion + ", telefono=" + telefono + ", documento=" + documento + ", direccion=" + direccion + ", codigoPostal=" + codigoPostal + ", observaciones=" + observaciones + ", grupoSanguineo=" + grupoSanguineo + ", telefonoEmergencia=" + telefonoEmergencia + ", codigoDoc=" + codigoDoc + ", legajo=" + legajo + ", fechaNacimiento=" + fechaNacimiento + ", estado=" + estado + ", tipoChofer=" + tipoChofer + ", idAux=" + idAux + ", foto=" + foto + ", vencimientos=" + vencimientos + '}';
    }	
    
}
