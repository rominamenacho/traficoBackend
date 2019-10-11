 package com.nuebus.model;

import com.nuebus.enumeraciones.SiNo;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Valeria
 */
 
@Entity
@Table(name="personal")
public class Personal implements Serializable {
    
    private static final long serialVersionUID = 1L;
     
    @EmbeddedId
    private PersonalPK personalPK;
    
    @Column( name = "prs_estado" )
    private Integer estado = 1;
    
    @Column( name= "prs_nombre" )    
    private String nombre; 
    
    @Column( name="prs_documento" )    
    private String documento = new String();       
    
    @Column( name="prs_mail" )
    private String email;
    
    @Column( name="prs_vis_solo_ag_propietarias") 
    private String soloAgPropias  =  SiNo.NO.valor;    

    public PersonalPK getPersonalPK() {
        return personalPK;
    }
    
    public void setPersonalPK(PersonalPK personalPK) {
        this.personalPK = personalPK;
    }    
   
    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }   

    public String getSoloAgPropias() {
        return soloAgPropias;
    }

    public void setSoloAgPropias(String soloAgPropias) {
        this.soloAgPropias = soloAgPropias;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.personalPK);
        hash = 79 * hash + Objects.hashCode(this.estado);
        hash = 79 * hash + Objects.hashCode(this.nombre);
        hash = 79 * hash + Objects.hashCode(this.documento);
        hash = 79 * hash + Objects.hashCode(this.email);
        hash = 79 * hash + Objects.hashCode(this.soloAgPropias);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Personal other = (Personal) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.documento, other.documento)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.soloAgPropias, other.soloAgPropias)) {
            return false;
        }
        if (!Objects.equals(this.personalPK, other.personalPK)) {
            return false;
        }
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Personal{" + "personalPK=" + personalPK + ", estado=" + estado + ", nombre=" + nombre + ", documento=" + documento + ", email=" +email + ", soloAgPropias=" + soloAgPropias + '}';
    }    
    
}
