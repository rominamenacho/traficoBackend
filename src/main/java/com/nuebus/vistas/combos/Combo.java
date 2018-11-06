package com.nuebus.vistas.combos;

/**
 *
 * @author Valeria
 */
public class Combo {
    
    long codigo;
    String descripcion;

    public Combo(  long codigo,  String descripcion){        
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

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
