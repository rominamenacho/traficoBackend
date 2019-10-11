package com.nuebus.model;

import com.nuebus.enumeraciones.ESTADO_INVERSO;
import com.nuebus.enumeraciones.ESTADO;
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
@Table(name = "agencias")
public class Agencia implements Serializable{        
   
    @EmbeddedId
    private AgenciaPK agenciaPK;

    @Column(name = "agn_nombre")
    private String nombre;

    @Column(name = "agn_estado")
    private Integer estado = ESTADO.HABILITADO.valor;

    @Column(name = "agn_estado_venta")
    private Integer estadoVta = ESTADO_INVERSO.DESHABILITADO.valor;

    public AgenciaPK getAgenciaPK() {
        return agenciaPK;
    }

    public void setAgenciaPK(AgenciaPK agenciaPK) {
        this.agenciaPK = agenciaPK;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getEstadoVta() {
        return estadoVta;
    }

    public void setEstadoVta(Integer estadoVta) {
        this.estadoVta = estadoVta;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.agenciaPK);
        hash = 79 * hash + Objects.hashCode(this.nombre);
        hash = 79 * hash + Objects.hashCode(this.estado);
        hash = 79 * hash + Objects.hashCode(this.estadoVta);
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
        final Agencia other = (Agencia) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.agenciaPK, other.agenciaPK)) {
            return false;
        }
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        if (!Objects.equals(this.estadoVta, other.estadoVta)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Agencia{" + "agenciaPK=" + agenciaPK + ", nombre=" + nombre + ", estado=" + estado + ", estadoVta=" + estadoVta + '}';
    }


    
}
