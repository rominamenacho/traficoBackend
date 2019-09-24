package com.nuebus.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nuebus.model.ChoferPK;
import javax.persistence.EmbeddedId;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Valeria
 */
public class ChoferDTO {
    
    @Valid
    @EmbeddedId
    private ChoferPK choferPK;
    @NotBlank
    @NotNull
    @Size(max = 40)
    private String nombre = new String();
    @Size(max = 60)
    private String funcion = new String();    
    //@NotBlank
    @Size(max = 50)
    private String telefono = new String();
    @NotBlank    
    @Size(max = 11)
    private String documento = new String();
    @Size(max = 300)
    private String direccion = new String();    
    @Size(max = 20)
    private String codigoPostal = new String();    
    @Size(max = 255)
    private String observaciones = new String();
    @Size(max = 50)
    private String grupoSanguineo = new String();
    
    //@NotBlank
    @Size(max = 50)
    private String telefonoEmergencia = new String();    
    @Digits(integer = 2,fraction = 0)
    private int codigoDoc = 0;  
    
    @Digits(integer = 10,fraction = 0)
    private long legajo;
    
    private java.util.Date fechaNacimiento = null;    
    
    @Digits(integer = 1,fraction = 0)
    private int estado = 0; 
     
    private Integer idAux;   
   
    private String foto; 
    
    @Digits(integer = 1,fraction = 0)
    private int tipoChofer;
   
    
    List<VencimientoCalculadoDTO> vencimientos = new ArrayList<>();
    
    
    public ChoferPK getChoferPK() {
        return choferPK;
    }

    public void setChoferPK(ChoferPK choferPK) {
        this.choferPK = choferPK;
    }    
	
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	

	public String getNombre() {
		return nombre;
	}

	public String getFuncion() {
		return funcion;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getDocumento() {
		return documento;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public String getGrupoSanguineo() {
		return grupoSanguineo;
	}

	public String getTelefonoEmergencia() {
		return telefonoEmergencia;
	}

	public int getCodigoDoc() {
		return codigoDoc;
	}

	public long getLegajo() {
		return legajo;
	}

	public java.util.Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public int getEstado() {
		return estado;
	}

	public Integer getIdAux() {
		return idAux;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public void setGrupoSanguineo(String grupoSanguineo) {
		this.grupoSanguineo = grupoSanguineo;
	}

	public void setTelefonoEmergencia(String telefonoEmergencia) {
		this.telefonoEmergencia = telefonoEmergencia;
	}

	public void setCodigoDoc(int codigoDoc) {
		this.codigoDoc = codigoDoc;
	}

	public void setLegajo(long legajo) {
		this.legajo = legajo;
	}

	public void setFechaNacimiento(java.util.Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public void setIdAux(Integer idAux) {
		this.idAux = idAux;
	}

	public List<VencimientoCalculadoDTO> getVencimientos() {
		return vencimientos;
	}

	public void setVencimientos(List<VencimientoCalculadoDTO> vencimientos) {
		this.vencimientos = vencimientos;
	}	
	
	public int getTipoChofer() {
		return tipoChofer;
	}

	public void setTipoChofer(int tipoChofer) {
		this.tipoChofer = tipoChofer;
	}

	@Override
	public String toString() {
		return "ChoferDTO [choferPK=" + choferPK + ", nombre=" + nombre + ", funcion=" + funcion + ", telefono="
				+ telefono + ", documento=" + documento + ", direccion=" + direccion + ", codigoPostal=" + codigoPostal
				+ ", observaciones=" + observaciones + ", grupoSanguineo=" + grupoSanguineo + ", telefonoEmergencia="
				+ telefonoEmergencia + ", codigoDoc=" + codigoDoc + ", legajo=" + legajo + ", fechaNacimiento="
				+ fechaNacimiento + ", estado=" + estado + ", idAux=" + idAux + ", foto=" + foto + ", tipoChofer="
				+ tipoChofer + ", vencimientos=" + vencimientos + "]";
	}

	
	
    
}
