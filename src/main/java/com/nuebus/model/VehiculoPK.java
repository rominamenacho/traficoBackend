package com.nuebus.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 *
 * @author Valeria
 */

/*
    There are some rules that your PK class should follow:

    It must have a default constructor without arguments.
    It must implement the java.io.Serializable interface.
    It must override the methods equals and hashCode.

*/


@Embeddable
public class VehiculoPK  implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Column
    @NotNull
    @NotBlank
    @Size( max = 4)
    private String vehEmpCodigo = new String();
    
    @Column
    @NotNull
    @Digits(integer=4, fraction=0)
    private Integer vehInterno = 0;  
    
    public VehiculoPK(){
    
    }    
    
    public VehiculoPK( String vehEmpCodigo, Integer vehInterno ){
        this.vehEmpCodigo = vehEmpCodigo;
        this.vehInterno = vehInterno;
    } 
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof VehiculoPK){
            VehiculoPK vehiPk = (VehiculoPK) obj;
 
            if(!vehiPk.getVehEmpCodigo().equals(vehEmpCodigo)){
                return false;
            }
 
            if( vehiPk.getVehInterno() != vehInterno ){
                return false;
            }
 
            return true;
        }
 
        return false;
    }
 
    @Override
    public int hashCode() {
        return vehEmpCodigo.hashCode() +  String.valueOf(vehInterno).hashCode();
    }
 

    public String getVehEmpCodigo() {
        return vehEmpCodigo;
    }

    public void setVehEmpCodigo(String vehEmpCodigo) {
        this.vehEmpCodigo = vehEmpCodigo;
    }

	public Integer getVehInterno() {
		return vehInterno;
	}

	public void setVehInterno(Integer vehInterno) {
		this.vehInterno = vehInterno;
	}

	@Override
	public String toString() {
		return "VehiculoPK [vehEmpCodigo=" + vehEmpCodigo + ", vehInterno=" + vehInterno + "]";
	}
       
    
}
