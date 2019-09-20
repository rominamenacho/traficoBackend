package com.nuebus.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author Valeria
 */

@Entity
@Table(name="Chofer_Incidencia")
public class ChoferIncidencia implements Serializable{
	
	@GenericGenerator(
            name = "choferIncidenciaIdSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "CHOFER_INCIDENCIA_ID_SEQ")
                ,
                    @Parameter(name = "initial_value", value = "1")
                ,
                    @Parameter(name = "increment_size", value = "1")
            }
    )	
    @Id
    @GeneratedValue(generator = "choferIncidenciaIdSequenceGenerator")
	private Long id;    
    
    java.util.Date inicio;
    java.util.Date fin;
    
    
    @ManyToOne( fetch = FetchType.LAZY)
    @NotFound(
        action = NotFoundAction.IGNORE)
    @JoinColumns({
        @JoinColumn(
            name = "id_cho_emp_codigo",
            referencedColumnName = "cho_emp_codigo"),
        @JoinColumn(
            name = "id_cho_codigo",
            referencedColumnName = "cho_codigo")
    })
    Chofer chofer;
    
     
    @ManyToOne
    @JoinColumn( name = "ID_INCIDENCIA" )
    private Incidencia incidencia;    
    
    
    public Chofer getChofer() {
        return chofer;
    }

    public void setChofer(Chofer chofer) {
        this.chofer = chofer;
    }
    
   
    public Incidencia getIncidencia() {
        return incidencia;
    }

    public void setIncidencia(Incidencia incidencia) {
        this.incidencia = incidencia;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
       
    
    public String toString(){
        
        return "chofer.getChoferPK().getCho_emp_codigo()= " + chofer.getChoferPK().getCho_emp_codigo()
         +  "; chofer.getChoferPK().getCho_codigo()= " + chofer.getChoferPK().getCho_codigo()
         +  ";incidencia.getId()= " + incidencia.getId() 
         +  ";inicio= " + inicio + ";fin=" + inicio;    
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
		ChoferIncidencia other = (ChoferIncidencia) obj;
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




	private static final long serialVersionUID = 1L;
   
}
