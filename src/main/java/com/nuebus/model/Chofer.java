package com.nuebus.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 *
 * @author Valeria
 */

@Entity
@Table(name="choferes")
public class Chofer implements Serializable{
    
    public static final int CHOFER = 0;
    public static final int AUXILIAR = 1;   
    
    public static final int PRIMER_CHOFER = 1;
    public static final int SEGUNDO_CHOFER = 2;
    public static final int PRIMER_AUX = 3;
    public static final int SEGUNDO_AUX = 4;

    
    public static final int HABILITADO = 0;
    public static final int DESHABILITADO = 1;   
    private static final long serialVersionUID = 1L;        
    
    @Valid
    @EmbeddedId
    private ChoferPK choferPK;
    @NotBlank
    @NotNull
    @Size(max = 40)
    private String cho_nombre = new String();
    @Size(max = 60)
    private String cho_funcion = new String();
    
    //@NotBlank
    @Size(max = 50)
    private String cho_telefono = new String();
    @NotBlank    
    @Size(max = 11)
    private String cho_documento = new String();
    @Size(max = 300)
    private String cho_direccion = new String();    
    @Size(max = 20)
    private String cho_codigo_postal = new String();    
    @Size(max = 255)
    private String cho_observaciones = new String();
    @Size(max = 50)
    private String cho_grupo_sanguineo = new String();
    
    //@NotBlank
    @Size(max = 50)
    private String cho_telefono_emergencia = new String();
    
    @Digits(integer = 2,fraction = 0)
    private int cho_doc_codigo = 0;        
    @Digits(integer = 10,fraction = 0)
    private long cho_legajo;        
    private java.util.Date cho_fecha_nacimiento = null;    
    @Digits(integer = 1,fraction = 0)
    private int cho_estado = 0;
    @Digits(integer = 1,fraction = 0)
    private int cho_chofer = 0; /*0 es chofer, 1 es auxiliar*/
    
    private Integer cho_id_aux;
    
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "chofer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Carnet> carnets = new HashSet<Carnet>();  
     
        
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "chofer", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<ChoferIncidencia> choferIncidencias = new HashSet<>();
    
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "chofer", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<ChoferViaje> ChoferViaje = new HashSet<>(); 
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "chofer", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<ChoferViaje> AuxiliarViaje = new HashSet<>(); 
    
    public Chofer(){}
    
    public Set<ChoferIncidencia> getChoferIncidencias() {
        return choferIncidencias;
    }

    public void setChoferIncidencias(Set<ChoferIncidencia> choferIncidencias) {
        this.choferIncidencias = choferIncidencias;
    }
    
    public String getCho_nombre() {
        return cho_nombre;
    }

    public void setCho_nombre(String cho_nombre) {
        this.cho_nombre = cho_nombre;
    }
    

    public String getCho_funcion() {
        return cho_funcion;
    }

    public void setCho_funcion(String cho_funcion) {
        this.cho_funcion = cho_funcion;
    }

    public String getCho_telefono() {
        return cho_telefono;
    }

    public void setCho_telefono(String cho_telefono) {
        this.cho_telefono = cho_telefono;
    }

    public String getCho_documento() {
        return cho_documento;
    }

    public void setCho_documento(String cho_documento) {
        this.cho_documento = cho_documento;
    }

    public String getCho_direccion() {
        return cho_direccion;
    }

    public void setCho_direccion(String cho_direccion) {
        this.cho_direccion = cho_direccion;
    }

    public String getCho_codigo_postal() {
        return cho_codigo_postal;
    }

    public void setCho_codigo_postal(String cho_codigo_postal) {
        this.cho_codigo_postal = cho_codigo_postal;
    }

    public String getCho_observaciones() {
        return cho_observaciones;
    }

    public void setCho_observaciones(String cho_observaciones) {
        this.cho_observaciones = cho_observaciones;
    }

    public String getCho_grupo_sanguineo() {
        return cho_grupo_sanguineo;
    }

    public void setCho_grupo_sanguineo(String cho_grupo_sanguineo) {
        this.cho_grupo_sanguineo = cho_grupo_sanguineo;
    }

    public String getCho_telefono_emergencia() {
        return cho_telefono_emergencia;
    }

    public void setCho_telefono_emergencia(String cho_telefono_emergencia) {
        this.cho_telefono_emergencia = cho_telefono_emergencia;
    }

    public int getCho_doc_codigo() {
        return cho_doc_codigo;
    }

    public void setCho_doc_codigo(int cho_doc_codigo) {
        this.cho_doc_codigo = cho_doc_codigo;
    }

    public long getCho_legajo() {
        return cho_legajo;
    }

    public void setCho_legajo(long cho_legajo) {
        this.cho_legajo = cho_legajo;
    }

    public Date getCho_fecha_nacimiento() {
        return cho_fecha_nacimiento;
    }

    public void setCho_fecha_nacimiento(Date cho_fecha_nacimiento) {
        this.cho_fecha_nacimiento = cho_fecha_nacimiento;
    }   

    public int getCho_estado() {
        return cho_estado;
    }    

    public Integer getCho_id_aux() {
		return cho_id_aux;
	}

	public void setCho_id_aux(Integer cho_id_aux) {
		this.cho_id_aux = cho_id_aux;
	}

	public void setCho_estado(int cho_estado) {
        this.cho_estado = cho_estado;
    }
          
    public Set<Carnet> getCarnets() {
        return this.carnets;
    }

    public void setCarnets(Set<Carnet> carnets) {
        this.carnets = carnets;
    }

    public ChoferPK getChoferPK() {
        return choferPK;
    }

    public void setChoferPK(ChoferPK choferPK) {
        this.choferPK = choferPK;
    } 

    public Set<ChoferViaje> getChoferViaje() {
        return ChoferViaje;
    }

    public void setChoferViaje(Set<ChoferViaje> ChoferViaje) {
        this.ChoferViaje = ChoferViaje;
    }  

    public int getCho_chofer() {
        return cho_chofer;
    }

    public void setCho_chofer(int cho_chofer) {
        this.cho_chofer = cho_chofer;
    }   

    public Set<ChoferViaje> getAuxiliarViaje() {
        return AuxiliarViaje;
    }

    public void setAuxiliarViaje(Set<ChoferViaje> AuxiliarViaje) {
        this.AuxiliarViaje = AuxiliarViaje;
    }

	@Override
	public String toString() {
		return "Chofer [choferPK=" + choferPK + ", cho_nombre=" + cho_nombre + ", cho_funcion=" + cho_funcion
				+ ", cho_telefono=" + cho_telefono + ", cho_documento=" + cho_documento + ", cho_direccion="
				+ cho_direccion + ", cho_codigo_postal=" + cho_codigo_postal + ", cho_observaciones="
				+ cho_observaciones + ", cho_grupo_sanguineo=" + cho_grupo_sanguineo + ", cho_telefono_emergencia="
				+ cho_telefono_emergencia + ", cho_doc_codigo=" + cho_doc_codigo + ", cho_legajo=" + cho_legajo
				+ ", cho_fecha_nacimiento=" + cho_fecha_nacimiento + ", cho_estado=" + cho_estado + ", cho_chofer="
				+ cho_chofer + ", cho_id_aux=" + cho_id_aux + "]";
	}

    
  
    
}
