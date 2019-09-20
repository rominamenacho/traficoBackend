package com.nuebus.enumeraciones;

public enum TipoIncidencia {
	
	UNIDAD( 0, "Unidades"), CHOFER( 1, "Personal");
	
	TipoIncidencia( Integer tipo, String descripcion){
	  this.tipo = tipo;
	  this.descripcion = descripcion;
	}
	
	public Integer tipo;
	public String descripcion;
	

}
