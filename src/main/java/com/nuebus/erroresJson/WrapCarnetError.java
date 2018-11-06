package com.nuebus.erroresJson;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 *
 * @author Valeria
 */
public class WrapCarnetError{
    
    private Stack<CarnetError> carnets = new Stack<>();       
    
    public WrapCarnetError(){}

    public Stack<CarnetError> getCarnets() {
        return carnets;
    }

    public void setCarnets(Stack<CarnetError> carnets) {
        this.carnets = carnets;
    }  
    
    
    public class CarnetError{
   
        private Set<String> tipo = new HashSet<>();
        private Set<String> fechaEmision = new HashSet<>(); 
        private Set<String> fechaVenc = new HashSet<>();    
        private Set<String> numeroCarnet = new HashSet<>();   
        private Set<String> observaciones = new HashSet<>();
        
        public CarnetError(){}

        public Set<String> getTipo() {
            return tipo;
        }

        public void setTipo(Set<String> tipo) {
            this.tipo = tipo;
        }

        public Set<String> getFechaEmision() {
            return fechaEmision;
        }

        public void setFechaEmision(Set<String> fechaEmision) {
            this.fechaEmision = fechaEmision;
        }

        public Set<String> getFechaVenc() {
            return fechaVenc;
        }

        public void setFechaVenc(Set<String> fechaVenc) {
            this.fechaVenc = fechaVenc;
        }

        public Set<String> getNumeroCarnet() {
            return numeroCarnet;
        }

        public void setNumeroCarnet(Set<String> numeroCarnet) {
            this.numeroCarnet = numeroCarnet;
        }

        public Set<String> getObservaciones() {
            return observaciones;
        }

        public void setObservaciones(Set<String> observaciones) {
            this.observaciones = observaciones;
        }        
    
    }
    
}
