package com.nuebus.dto;

import java.util.HashSet;
import java.util.Set;
import com.nuebus.model.ChoferPK;

public class ChoferConCarnetsDTO {
	 
	    public ChoferPK choferPK;
	    public String cho_nombre = new String();
	    public String cho_funcion = new String();	    
	    public String cho_telefono = new String();	    
	    public String cho_observaciones = new String();
	    public String cho_telefono_emergencia = new String();	    
	    public int cho_doc_codigo = 0;        
	    public long cho_legajo;        
	    public java.util.Date cho_fecha_nacimiento = null;    
	    public int cho_estado = 0;	    
	    public Set<CarnetDTO> carnets = new HashSet<>();	    
	    
	    
		public ChoferPK getChoferPK() {
			return choferPK;
		}
		public void setChoferPK(ChoferPK choferPK) {
			this.choferPK = choferPK;
		}
		public String getCho_nombre() {
			return cho_nombre;
		}
		public void setCho_nombre(String cho_nombre) {
			this.cho_nombre = cho_nombre;
		}
		public String getCho_funcion() {
			return cho_funcion;
		}
		public void setCho_funcion(String cho_funcion) {
			this.cho_funcion = cho_funcion;
		}
		public String getCho_telefono() {
			return cho_telefono;
		}
		public void setCho_telefono(String cho_telefono) {
			this.cho_telefono = cho_telefono;
		}
		public String getCho_observaciones() {
			return cho_observaciones;
		}
		public void setCho_observaciones(String cho_observaciones) {
			this.cho_observaciones = cho_observaciones;
		}
		public String getCho_telefono_emergencia() {
			return cho_telefono_emergencia;
		}
		public void setCho_telefono_emergencia(String cho_telefono_emergencia) {
			this.cho_telefono_emergencia = cho_telefono_emergencia;
		}
		public int getCho_doc_codigo() {
			return cho_doc_codigo;
		}
		public void setCho_doc_codigo(int cho_doc_codigo) {
			this.cho_doc_codigo = cho_doc_codigo;
		}
		public long getCho_legajo() {
			return cho_legajo;
		}
		public void setCho_legajo(long cho_legajo) {
			this.cho_legajo = cho_legajo;
		}
		public java.util.Date getCho_fecha_nacimiento() {
			return cho_fecha_nacimiento;
		}
		public void setCho_fecha_nacimiento(java.util.Date cho_fecha_nacimiento) {
			this.cho_fecha_nacimiento = cho_fecha_nacimiento;
		}
		public int getCho_estado() {
			return cho_estado;
		}
		public void setCho_estado(int cho_estado) {
			this.cho_estado = cho_estado;
		}
		public Set<CarnetDTO> getCarnets() {
			return carnets;
		}
		public void setCarnets(Set<CarnetDTO> carnets) {
			this.carnets = carnets;
		}
		
		
		@Override
		public String toString() {
			return "ChoferConCarnetsDTO [choferPK=" + choferPK + ", cho_nombre=" + cho_nombre + ", cho_funcion="
					+ cho_funcion + ", cho_telefono=" + cho_telefono + ", cho_observaciones=" + cho_observaciones
					+ ", cho_telefono_emergencia=" + cho_telefono_emergencia + ", cho_doc_codigo=" + cho_doc_codigo
					+ ", cho_legajo=" + cho_legajo + ", cho_fecha_nacimiento=" + cho_fecha_nacimiento + ", cho_estado="
					+ cho_estado + ", carnets=" + carnets + "]";
		}
	    
	    
		
}
