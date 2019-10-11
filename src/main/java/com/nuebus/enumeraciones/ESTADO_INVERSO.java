package com.nuebus.enumeraciones;

/**
 *
 * @author Valeria
 */
public enum ESTADO_INVERSO {
    
    HABILITADO( 0, "Habilitado", "Hab"),
    DESHABILITADO( 1, "Deshabilitado", "Deshab");

    private ESTADO_INVERSO( Integer valor, String descripcion, String abreviatura ) {
        this.valor = valor;
        this.descripcion = descripcion;
        this.abreviatura = abreviatura;
    }
       
    public Integer valor;
    public String descripcion;
    public String abreviatura;
    
}
