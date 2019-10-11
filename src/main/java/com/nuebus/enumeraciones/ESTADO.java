package com.nuebus.enumeraciones;

/**
 *
 * @author Valeria
 */
public enum ESTADO {
    
    HABILITADO( 1, "Habilitado", "Hab"),
    DESHABILITADO( 0, "Deshabilitado", "Deshab");

    private ESTADO( Integer valor, String descripcion, String abreviatura ) {
        this.valor = valor;
        this.descripcion = descripcion;
        this.abreviatura = abreviatura;
    }
       
    public Integer valor;
    public String descripcion;
    public String abreviatura;
}
