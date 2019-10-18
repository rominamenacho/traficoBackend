
package com.nuebus.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author Valeria
 */

@Entity
@Table(name="Vehiculo_Incidencia")
public class VehiculoIncidencia implements Serializable {   
    
	private static final long serialVersionUID = 1L;


	@GenericGenerator(
            name = "vehiculoIncidenciaSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "VEHICULO_INCIDENCIA_ID_SEQ")
                ,
                    @Parameter(name = "initial_value", value = "1")
                ,
                    @Parameter(name = "increment_size", value = "1")
            }
    )	
    @Id
    @GeneratedValue(generator = "vehiculoIncidenciaSequenceGenerator")
    Long id;     
       
    
    java.util.Date inicio;
    java.util.Date fin;    
 
    @ManyToOne   
    @JoinColumns({
        @JoinColumn(
            name = "id_veh_emp_codigo ",
            referencedColumnName = "vehEmpCodigo"),
        @JoinColumn(
            name = "id_veh_interno ",
            referencedColumnName = "vehInterno")
    })    
    private Vehiculo vehiculo;
       
   
    @ManyToOne
    @JoinColumn( name = "ID_INCIDENCIA" )
    private Incidencia incidencia;  

   /* public Vehiculo getVehiculo() {
        return vehiculo;
    }*/

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }   

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public Incidencia getIncidencia() {
        return incidencia;
    }

    public void setIncidencia(Incidencia incidencia) {
        this.incidencia = incidencia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fin == null) ? 0 : fin.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((inicio == null) ? 0 : inicio.hashCode());
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
		VehiculoIncidencia other = (VehiculoIncidencia) obj;
		if (fin == null) {
			if (other.fin != null)
				return false;
		} else if (!fin.equals(other.fin))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (inicio == null) {
			if (other.inicio != null)
				return false;
		} else if (!inicio.equals(other.inicio))
			return false;
		return true;
	} 
    
    
    
}
