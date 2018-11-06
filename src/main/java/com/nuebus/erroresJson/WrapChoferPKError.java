
package com.nuebus.erroresJson;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 *
 * @author Valeria
 */

public class WrapChoferPKError {    
    
    private Stack<ChoferPKError> choferes = new Stack<>();        
    
    public WrapChoferPKError(){
    
    }  

    public Stack<ChoferPKError> getChoferes() {
        return choferes;
    }

    public void setChoferes(Stack<ChoferPKError> choferes) {
        this.choferes = choferes;
    }  
    
    
    public class ChoferPKError {    
    
        private Set<String> cho_emp_codigo = new HashSet<>();    
        private Set<String> cho_codigo = new HashSet<>();       

        public ChoferPKError(){

        }

        public Set<String> getCho_emp_codigo() {
            return cho_emp_codigo;
        }

        public void setCho_emp_codigo(Set<String> cho_emp_codigo) {
            this.cho_emp_codigo = cho_emp_codigo;
        }

        public Set<String> getCho_codigo() {
            return cho_codigo;
        }

        public void setCho_codigo(Set<String> cho_codigo) {
            this.cho_codigo = cho_codigo;
        }  
    }

    
}
