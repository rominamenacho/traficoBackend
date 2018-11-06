package com.nuebus.model;

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
@Table(name="mapa_asientos")
public class MapaAsiento implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
   /* (MPA_EMP_CODIGO, MPA_CODIGO, MPA_DESCRIPCION, MPA_HABILITADO, 
      MPA_TIPO, MPA_ALTO, MPA_ANCHO, MPA_LARGO)    */ 
    
    @EmbeddedId
    MapaAsientoPK mapaAsientoPK;
    @Column(name="MPA_DESCRIPCION")            
    String descripcion;

    public MapaAsientoPK getMapaAsientoPK() {
        return mapaAsientoPK;
    }

    public void setMapaAsientoPK(MapaAsientoPK mapaAsientoPK) {
        this.mapaAsientoPK = mapaAsientoPK;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.mapaAsientoPK);
        hash = 61 * hash + Objects.hashCode(this.descripcion);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MapaAsiento other = (MapaAsiento) obj;
        if (!Objects.equals(this.mapaAsientoPK, other.mapaAsientoPK)) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
