
package com.nuebus.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
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
@Table( name = "Enlaces_lineas" )
public class EnlaceLineas implements Serializable {
    
    private static final long serialVersionUID = 1L;
   
    @GenericGenerator(
        name = "enlaceLineasSG",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @Parameter(name = "sequence_name", value = "ENLACE_LINEAS_SEQUENCE"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
        }
    )
    @Id
    @GeneratedValue(generator = "enlaceLineasSG")
    Long id;    
    
    @ManyToOne( fetch = FetchType.LAZY)
    @NotFound(
        action = NotFoundAction.IGNORE)
    @JoinColumns({
        @JoinColumn(
            name = "linEmpCodigoIda",
            referencedColumnName = "linEmpCodigo"),
        @JoinColumn(
            name = "linCodigoIda",
            referencedColumnName = "linCodigo")
    })
    Linea ida;
    
    @ManyToOne( fetch = FetchType.LAZY)
    @NotFound(
        action = NotFoundAction.IGNORE)
    @JoinColumns({
        @JoinColumn(
            name = "linEmpCodigoVta",
            referencedColumnName = "linEmpCodigo"),
        @JoinColumn(
            name = "linCodigoVta",
            referencedColumnName = "linCodigo")
    })
    Linea vuelta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Linea getIda() {
        return ida;
    }

    public void setIda(Linea ida) {
        this.ida = ida;
    }

    public Linea getVuelta() {
        return vuelta;
    }

    public void setVuelta(Linea vuelta) {
        this.vuelta = vuelta;
    }  

    @Override
    public String toString() {
        return "EnlaceLineas{" + "id=" + id + ", ida=" + ida + ", vuelta=" + vuelta + '}';
    } 
    
    
    
}
