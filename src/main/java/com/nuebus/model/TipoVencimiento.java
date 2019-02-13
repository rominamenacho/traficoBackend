package com.nuebus.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table( name="Tipos_Vencimiento" )
public class TipoVencimiento implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	Short id; 
	@Size( max=80 )
    String nombreEntidad;
    @Size( max=80 )
    String nombreCampo;
    @Size( max = 120)
    String descNombreCampo;
        
	public Short getId() {
		return id;
	}
	
	public void setId(Short id) {
		this.id = id;
	}
	
	public String getNombreEntidad() {
		return nombreEntidad;
	}
	
	public void setNombreEntidad(String nombreEntidad) {
		this.nombreEntidad = nombreEntidad;
	}
	
	public String getNombreCampo() {
		return nombreCampo;
	}
	
	public void setNombreCampo(String nombreCampo) {
		this.nombreCampo = nombreCampo;
	}
	
	public String getDescNombreCampo() {
		return descNombreCampo;
	}
	
	public void setDescNombreCampo(String descNombreCampo) {
		this.descNombreCampo = descNombreCampo;
	}
	
	@Override
	public String toString() {
		return "TipoVencimiento [id=" + id + ", nombreEntidad=" + nombreEntidad + ", nombreCampo=" + nombreCampo
				+ ", descNombreCampo=" + descNombreCampo + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descNombreCampo == null) ? 0 : descNombreCampo.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombreCampo == null) ? 0 : nombreCampo.hashCode());
		result = prime * result + ((nombreEntidad == null) ? 0 : nombreEntidad.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoVencimiento other = (TipoVencimiento) obj;
		if (descNombreCampo == null) {
			if (other.descNombreCampo != null)
				return false;
		} else if (!descNombreCampo.equals(other.descNombreCampo))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nombreCampo == null) {
			if (other.nombreCampo != null)
				return false;
		} else if (!nombreCampo.equals(other.nombreCampo))
			return false;
		if (nombreEntidad == null) {
			if (other.nombreEntidad != null)
				return false;
		} else if (!nombreEntidad.equals(other.nombreEntidad))
			return false;
		return true;
	}

    

}
