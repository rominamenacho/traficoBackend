/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuebus.dto;


import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.nuebus.enumeraciones.TipoIncidencia;


/**
 *
 * @author Usuario
 */
public class IncidenciaDTO implements Serializable {    

	private static final long serialVersionUID = 1L;	
	
	@NotNull
	Long id;    
    @Size( max = 60)    
    String  descripcion;       
    Integer tipo;    
    @Size( max = 40)    
    String  color;    
    @NotNull    
    @Size( max = 4)    
    String  empresa ;    
    @Size( max = 10, message = "Debe especificar un codigo.")    
    String codigo;
    
    private Boolean activo; 
    
	public Long getId() {
		return id;
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
	public String getEmpresa() {
		return empresa;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setId(Long id) {
		this.id = id;
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
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
	 
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
	
	@Override
	public String toString() {
		return "IncidenciaDTO [id=" + id + ", descripcion=" + descripcion + ", tipo=" + tipo + ", color=" + color
				+ ", empresa=" + empresa + ", codigo=" + codigo + ", activo=" + activo + "]";
	} 
	 
    
    
}
