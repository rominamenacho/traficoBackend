
package com.nuebus.model;

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
@Table(name="chofer_viaje_esp")
public class ChoferViaje {
    

     @GenericGenerator(
            name = "choferViajesSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "CHOFER_VIAJE_ID_SEQ")
                ,
                    @Parameter(name = "initial_value", value = "1")
                ,
                    @Parameter(name = "increment_size", value = "1")
            }
    )	
    @Id
    @GeneratedValue(generator = "choferViajesSequenceGenerator")
    Long id;       
        
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
    @JoinColumn( name = "ID_VIAJE" )
    private ViajeEspecial viajeEspecial; 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Chofer getChofer() {
        return chofer;
    }

    public void setChofer(Chofer chofer) {
        this.chofer = chofer;
    }

    public ViajeEspecial getViajeEspecial() {
        return viajeEspecial;
    }

    public void setViajeEspecial(ViajeEspecial viajeEspecial) {
        this.viajeEspecial = viajeEspecial;
    }

    
}
