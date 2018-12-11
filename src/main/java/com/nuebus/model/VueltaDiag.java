package com.nuebus.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author Valeria
 */

@Entity()
@Table( name = "Vuelta_Diag",
        uniqueConstraints={@UniqueConstraint(columnNames = { "ser_emp_codigo_ida",
                                                             "ser_fecha_hora_ida",
                                                             "ser_lin_codigo_ida",
                                                             "ser_refuerzo_ida" }) } )
public class VueltaDiag implements Serializable{
     
    private static long serialVersionUID = 1L;

    @GenericGenerator(
            name = "vueltasSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "vuelta_seq")
                ,
                    @Parameter(name = "initial_value", value = "1")
                ,
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @Id
    @GeneratedValue(generator = "vueltasSequenceGenerator")
    private Long id;
    
    @Size(max = 4)
    String empresa;
    
    @Size( max = 40 )
    private String peliIda;
    
    @Size( max = 40 )
    private String videoIda;
    
    @Size( max = 40) 
    private String peliVta;
    
    @Size( max=40)
    private String videoVta;
    
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumns({
        @JoinColumn( name = "ser_emp_codigo_ida", referencedColumnName = "serEmpCodigo"),
        @JoinColumn( name = "ser_lin_codigo_ida",  referencedColumnName = "serLinCodigo"),
        @JoinColumn( name = "ser_fecha_hora_ida",  referencedColumnName = "serFechaHora"),
        @JoinColumn( name = "ser_refuerzo_ida",  referencedColumnName = "serRefuerzo") 
    })    
    Servicio servicio; 
            
            
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumns({
        @JoinColumn( name = "ser_emp_codigo_vta", referencedColumnName = "serEmpCodigo"),
        @JoinColumn( name = "ser_lin_codigo_vta",  referencedColumnName = "serLinCodigo"),
        @JoinColumn( name = "ser_fecha_hora_vta",  referencedColumnName = "serFechaHora"),
        @JoinColumn( name = "ser_refuerzo_vta",  referencedColumnName = "serRefuerzo") 
    })      
    Servicio servicioRet;

    public VueltaDiag() {
    }    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPeliIda() {
        return peliIda;
    }

    public void setPeliIda(String peliIda) {
        this.peliIda = peliIda;
    }

    public String getVideoIda() {
        return videoIda;
    }

    public void setVideoIda(String videoIda) {
        this.videoIda = videoIda;
    }

    public String getPeliVta() {
        return peliVta;
    }

    public void setPeliVta(String peliVta) {
        this.peliVta = peliVta;
    }

    public String getVideoVta() {
        return videoVta;
    }

    public void setVideoVta(String videoVta) {
        this.videoVta = videoVta;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Servicio getServicioRet() {
        return servicioRet;
    }

    public void setServicioRet(Servicio servicioRet) {
        this.servicioRet = servicioRet;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    @Override
    public String toString() {
        return "VueltaDiag{" + "id=" + id + ", empresa=" + empresa + ", peliIda=" + peliIda + ", videoIda=" + videoIda + ", peliVta=" + peliVta + ", videoVta=" + videoVta + ", servicio=" + servicio + ", servicioRet=" + servicioRet + '}';
    }    
    
}
