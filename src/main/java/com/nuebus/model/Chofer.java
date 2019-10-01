package com.nuebus.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
    @Column( name="cho_nombre", length = 40 )
    private String nombre = new String();
    
    @Size(max = 60)
    @Column( name="cho_funcion", length = 60 )
    private String funcion = new String();
    
    //@NotBlank
    @Size(max = 50)
    @Column(name="cho_telefono", length=50 )
    private String telefono = new String();
    
    @NotBlank    
    @Size(max = 11)
    @Column( name="cho_documento", length = 11 )
    private String documento = new String();
    
    @Size(max = 300)
    @Column( name = "cho_direccion", length = 300)
    private String direccion = new String(); 
    
    @Size(max = 20)
    @Column( name ="cho_codigo_postal", length = 20 )
    private String codigoPostal = new String();    
    
    @Size(max = 255)
    @Column( name = "cho_observaciones", length = 255 )
    private String observaciones = new String();
    
    @Size(max = 50)
    @Column( name = "cho_grupo_sanguineo", length = 50  )
    private String grupoSanguineo = new String();
    
    //@NotBlank
    @Size(max = 50)
    @Column( name="cho_telefono_emergencia", length = 50)
    private String telefonoEmergencia = new String();
    
    @Digits(integer = 2,fraction = 0)
    @Column( name= "cho_doc_codigo")
    private int codigoDoc = 0;        
    
    @Digits(integer = 10,fraction = 0)
    @Column( name="cho_legajo" )
    private long legajo;        
    
    @Column( name="cho_fecha_nacimiento")
    private java.util.Date fechaNacimiento = null;
    
    @Digits(integer = 1,fraction = 0)
    @Column( name="cho_estado")
    private int estado = 0;
    
    @Digits(integer = 1,fraction = 0)
    @Column( name="cho_chofer" )
    private Integer tipoChofer = 0; 
    
    @Column(name="cho_id_aux")
    private Integer idAux;
    
    @Size(max = 255)
    @Column( name="cho_foto", length = 255 )
    private String foto;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "chofer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Carnet> carnets = new ArrayList<>();  
     
        
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "chofer", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<ChoferIncidencia> choferIncidencias = new HashSet<>();
    
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "chofer", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<ChoferViaje> choferViaje = new HashSet<>(); 
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "chofer", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<ChoferViaje> auxiliarViaje = new HashSet<>(); 
    
    public Chofer(){}
    
    public Set<ChoferIncidencia> getChoferIncidencias() {
        return choferIncidencias;
    }

    public void setChoferIncidencias(Set<ChoferIncidencia> choferIncidencias) {
        this.choferIncidencias = choferIncidencias;
    }    
    

    public ChoferPK getChoferPK() {
        return choferPK;
    }

    public void setChoferPK(ChoferPK choferPK) {
        this.choferPK = choferPK;
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

	public Set<ChoferViaje> getAuxiliarViaje() {
		return auxiliarViaje;
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

	public void setAuxiliarViaje(Set<ChoferViaje> auxiliarViaje) {
		this.auxiliarViaje = auxiliarViaje;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public List<Carnet> getCarnets() {
		return carnets;
	}

	public void setCarnets(List<Carnet> carnets) {
		this.carnets = carnets;
	}

	public Set<ChoferViaje> getChoferViaje() {
		return choferViaje;
	}

	public void setChoferViaje(Set<ChoferViaje> choferViaje) {
		this.choferViaje = choferViaje;
	}

        public Integer getTipoChofer() {
            return tipoChofer;
        }

        public void setTipoChofer(Integer tipoChofer) {
            this.tipoChofer = tipoChofer;
        }
        

	@Override
	public String toString() {
		return "Chofer [choferPK=" + choferPK + ", nombre=" + nombre + ", funcion=" + funcion + ", telefono=" + telefono
				+ ", documento=" + documento + ", direccion=" + direccion + ", codigoPostal=" + codigoPostal
				+ ", observaciones=" + observaciones + ", grupoSanguineo=" + grupoSanguineo + ", telefonoEmergencia="
				+ telefonoEmergencia + ", codigoDoc=" + codigoDoc + ", legajo=" + legajo + ", fechaNacimiento="
				+ fechaNacimiento + ", estado=" + estado + ", tipochofer=" + tipoChofer + ", idAux=" + idAux + ", foto="
				+ foto + ", carnets=" + carnets + "]";
	}
  
    
    
}
