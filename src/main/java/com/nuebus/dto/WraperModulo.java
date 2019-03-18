package com.nuebus.dto;

import java.util.ArrayList;
import java.util.List;

import com.nuebus.annotations.PermisoEnVista;

public class WraperModulo {
    String modulo;
    List<PermisoEnVista> permisos = new ArrayList<>();

    public WraperModulo() {
    }   
    

    public WraperModulo(String modulo, List<PermisoEnVista> permisos) {
        this.modulo = modulo;
        this.permisos = permisos;
    }    

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public List<PermisoEnVista> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<PermisoEnVista> permisos) {
        this.permisos = permisos;
    }


	@Override
	public String toString() {
		return "WraperModulo [modulo=" + modulo + ", permisos=" + permisos + "]";
	}    
    
    
}
