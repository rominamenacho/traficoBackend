package com.nuebus.model;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.persistence.Entity;

@Entity
@Table(name="imagenesChoferes")
public class ImagenChofer implements Serializable{    

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_chofer_idImagen")
    @SequenceGenerator(name="seq_chofer_idImagen", sequenceName = "seq_chofer_idImagen", allocationSize = 1)
    private Long id;
	
	@Size(max = 4)
	private String empresa;
 
	@Lob
	private byte[] imagen;

	public Long getId() {
		return id;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}	

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}	
	

	@Override
	public String toString() {
		return "ImagenChofer [id=" + id + ", empresa=" + empresa + ", imagen=" + Arrays.toString(imagen) + "]";
	}





	private static final long serialVersionUID = 1L;

}
