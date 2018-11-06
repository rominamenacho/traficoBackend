
package com.nuebus.vistas.combos;

/**
 *
 * @author Valeria
 */
public class ComboStr {
    
    String codigo;
    String descripcion;
    
    public ComboStr(){
    
    }
    
    public ComboStr(  int codigo,  int descripcion){
        this.codigo =  String.valueOf( codigo );
        this.descripcion = String.valueOf( descripcion );        
    }
    
    public ComboStr(  String codigo,  long descripcion){
        this.codigo = codigo;
        this.descripcion = String.valueOf( descripcion );        
    }

    
    public ComboStr(  String codigo,  String descripcion){
        this.codigo = codigo;
        this.descripcion = descripcion;        
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    
}
