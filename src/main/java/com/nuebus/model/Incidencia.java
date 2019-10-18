
package com.nuebus.model;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.nuebus.enumeraciones.TipoIncidencia;


/**
 *
 * @author Usuario
 */
@Entity
@Table( name="Incidencias" )
public class Incidencia extends AbstractEntityVersion{    
    
    @Id
    @GenericGenerator(
	      name = "incidenciaSequenceGenerator",
	      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
	      parameters = {
	        @Parameter(name = "sequence_name", value = "incidencia_seq"),
	        @Parameter(name = "initial_value", value = "5"),
	        @Parameter(name = "increment_size", value = "1")
	        }
    )    
    @GeneratedValue( generator = "incidenciaSequenceGenerator" )  
    private Long id;    

    @Size( max = 60)
    @Column( name = "in_descripcion", length = 60 )  
    private String  descripcion;
    
    
    @Digits(integer=1, fraction=0)
    @Column( name="in_tipo" )
    private Integer tipo;
    
    @Size( max = 40)
    @Column( name="in_color" , length = 40 )
    private String  color;
    
    @NotNull    
    @Size( max = 4)
    @Column( name = "in_empresa", nullable = false, length = 4)
    private String  empresa ;
    
    @Size( max = 10, message = "Debe especificar un codigo.")
    @Column( name="codigo", length = 10 )
    private String codigo; 
    
    private Boolean activo; 
    
    public Incidencia(){}
    
    @OneToMany( fetch = FetchType.LAZY, mappedBy = "incidencia", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<ChoferIncidencia> choferIncidencias;    
    
    /*@OneToMany( fetch = FetchType.LAZY, mappedBy = "incidencia", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<VehiculoIncidencia> vehiculoIncidencias;  */

    /*public Set<VehiculoIncidencia> getVehiculoIncidencias() {
        return vehiculoIncidencias;
    }

    public void setVehiculoIncidencias(Set<VehiculoIncidencia> vehiculoIncidencias) {
        this.vehiculoIncidencias = vehiculoIncidencias;
    }   
    
     
    public Set<ChoferIncidencia> getChoferIncidencias() {
        return choferIncidencias;
    }

    public void setChoferIncidencias(Set<ChoferIncidencia> choferIncidencias) {
        this.choferIncidencias = choferIncidencias;
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }    

   
    public String getTipoString() {
        String in_tipoString="";        
        if( tipo == TipoIncidencia.UNIDAD.tipo ) {
        	in_tipoString= TipoIncidencia.UNIDAD.descripcion;
        }else if( tipo == TipoIncidencia.CHOFER.tipo ){
           in_tipoString= TipoIncidencia.CHOFER.descripcion;
        }
        return in_tipoString;
    }  

    public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }    
        
	public String getDescripcion() {
		return descripcion;
	}

	public Integer getTipo() {
		return tipo;
	}

	public String getColor() {
		return color;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	
	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	
	@Override
	public String toString() {
		return "Incidencia [id=" + id + ", descripcion=" + descripcion + ", tipo=" + tipo + ", color=" + color
				+ ", empresa=" + empresa + ", codigo=" + codigo + ", activo=" + activo + "]";
	}

	private static final long serialVersionUID = 1L;
    
}
