
package com.nuebus.dto;

/**
 *
 * @author Valeria
 */
public class ComboDTO {
    
    long codigo;
    String descripcion;
    
    public ComboDTO(){}

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }  
    
}
