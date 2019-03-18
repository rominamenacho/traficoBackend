package com.nuebus.annotations;


/**
 * Contiene el nombre y la descripcion del permiso que hay que mostrar en la vista,
 * corresponde al permiso obtenido de leer la Annotation en el Controller
 * @author daxcurson
 *
 */
public class PermisoEnVista 
{
	private String authority;
	private String descripcionPermiso;
	
	
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getDescripcionPermiso() 
	{
		return descripcionPermiso;
	}
	public void setDescripcionPermiso(String descripcionPermiso) 
	{
		this.descripcionPermiso = descripcionPermiso;
	}
	
	@Override
	public String toString() {
		return "PermisoEnVista [authority=" + authority + ", descripcionPermiso=" + descripcionPermiso + "]";
	}
	
	
}
