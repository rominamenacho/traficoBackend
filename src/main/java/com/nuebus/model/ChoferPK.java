/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuebus.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Valeria
 */
@Embeddable
public class ChoferPK implements Serializable{

	@NotNull
    @Size(max = 4)
    @Column( name= "cho_emp_codigo", length = 4 )
    private String empCodigo = new String();
    
    
    @Digits(integer = 10,fraction = 0)
    @Column( name="cho_codigo" )
    private long codigo; 
    
    public ChoferPK( ){
    }
    
	public ChoferPK( String empCodigo, long codigo) {
		super();
		this.empCodigo = empCodigo;
		this.codigo = codigo;
	}

	public String getEmpCodigo() {
		return empCodigo;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setEmpCodigo(String empCodigo) {
		this.empCodigo = empCodigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (codigo ^ (codigo >>> 32));
		result = prime * result + ((empCodigo == null) ? 0 : empCodigo.hashCode());
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
		ChoferPK other = (ChoferPK) obj;
		if (codigo != other.codigo)
			return false;
		if (empCodigo == null) {
			if (other.empCodigo != null)
				return false;
		} else if (!empCodigo.equals(other.empCodigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ChoferPK [empCodigo=" + empCodigo + ", codigo=" + codigo + "]";
	}

	private static final long serialVersionUID = 1L;
    
}
