package com.nuebus.excepciones;

/**
 *
 * @author Valeria
 */
public class ValidacionGenerica extends Exception{
    
    private static final long serialVersionUID = -3332292346834265371L;
    Object objeto;
     
    public ValidacionGenerica(  Object objeto ){ 
        super();
        this.objeto = objeto;
    } 

    public Object getObjeto() {
        return objeto;
    }

    public void setObjeto(Object objeto) {
        this.objeto = objeto;
    }
     
     
    
}
