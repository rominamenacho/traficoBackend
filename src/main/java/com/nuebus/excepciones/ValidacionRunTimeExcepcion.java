package com.nuebus.excepciones;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Valeria
 */
public class ValidacionRunTimeExcepcion extends RuntimeException {
    
    private static final long serialVersionUID = -3332292346834265371L;    
    Map<String, Set<String>> errors = new HashMap<>();

    public ValidacionRunTimeExcepcion( Map<String, Set<String>> errors ) {        
        super();
        this.errors = errors;
    }  

    public Map<String, Set<String>> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, Set<String>> errors) {
        this.errors = errors;
    }  
    
    
}
