
package com.nuebus.model;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Usuario
 */
@Entity
public class Incidencia extends AbstractEntityVersion{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_incidencia_idincidencia")
    @SequenceGenerator(name="seq_incidencia_idincidencia", sequenceName = "seq_incidencia_idincidencia", allocationSize = 100)
    private Long id;    

    @Size( max = 60)
    private String  in_descripcion;
    @Digits(integer=1, fraction=0)
    private Integer in_tipo;
    @Size( max = 60)
    private String  in_color;
    @NotNull
    @NotBlank
    @Size( max = 4)
    private String  in_empresa;
    @NotBlank
    @Size( max = 10, message = "Debe especificar un codigo.")
    private String codigo; 
    
    public Incidencia(){}
    
    @OneToMany( fetch = FetchType.LAZY, mappedBy = "incidencia", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<ChoferIncidencia> choferIncidencias;    
    
    @OneToMany( fetch = FetchType.LAZY, mappedBy = "incidencia", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<VehiculoIncidencia> vehiculoIncidencias;  

    public Set<VehiculoIncidencia> getVehiculoIncidencias() {
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
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }    

    public String getIn_descripcion() {
        return in_descripcion;
    }

    public void setIn_descripcion(String in_descripcion) {
        this.in_descripcion = in_descripcion;
    }

    public Integer getIn_tipo() {
        return in_tipo;
    }

    public void setIn_tipo(Integer in_tipo) {
        this.in_tipo = in_tipo;
    }
    
      public String getIn_tipoString() {
        String in_tipoString="";
        /*0 unidades , 1 choferes */
        if(in_tipo==0){
        in_tipoString= "Unidades";
        }else if(in_tipo ==1){
          in_tipoString= "Choferes";
        }
        return in_tipoString;
    }

        public String getIn_color() {
        return in_color;
    }

    public void setIn_color(String in_color) {
        this.in_color = in_color;
    }

    public String getIn_empresa() {
        return in_empresa;
    }

    public void setIn_empresa(String in_empresa) {
        if(!in_empresa.equals("")){
        this.in_empresa = in_empresa;
        }else{
        //this.userContext.getEmpresa();
        }        
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public String toString(){
        
        return  "id="+ id + ";in_descripcion=" +  in_descripcion + ";in_tipo=" + in_tipo 
                + ";in_color="+ in_color + ";in_empresa=" + in_empresa + ";codigo=" + codigo;
    
    }
    
}
