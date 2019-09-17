package com.nuebus.enumeraciones;

public enum TipoCarnet {
	
	 NACIONAL( 1, "NACIONAL CNRT"),
	 PROVINCIAL( 2, "PROVINCIAL"); 
	
	 
	 TipoCarnet( Integer tipo, String descripcion ) {
		 this.tipo = tipo;
		 this.descripcion = descripcion;
	 }   
	    
	 public Integer tipo;
	 public String descripcion;

}
