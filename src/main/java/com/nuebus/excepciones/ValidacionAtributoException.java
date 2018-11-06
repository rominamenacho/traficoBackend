package com.nuebus.excepciones;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Valeria
 */
public class ValidacionAtributoException extends Exception {
    
    private static final long serialVersionUID = -3332292346834265371L;    
    List<AtributoException> excepciones = new ArrayList<AtributoException>();

    public ValidacionAtributoException( List<AtributoException> excepciones ) {
        super();
        setExcepciones(excepciones);
    }

    public List<AtributoException> getExcepciones() {
        return excepciones;
    }

    public void setExcepciones(List<AtributoException> excepciones) {
        this.excepciones = excepciones;
    }
    
    
    
    
}
