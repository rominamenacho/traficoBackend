package com.nuebus.erroresJson;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 *
 * @author Valeria
 */
public class WrapVehiculoIncidenciaError {
    
    Stack<VehiculoIncidenciaError>  incidencias = new Stack<>();//   
    
    
    public WrapVehiculoIncidenciaError(){
    
    }

    public Stack<VehiculoIncidenciaError> getIncidencias() {
        return incidencias;
    }

    public void setIncidencias(Stack<VehiculoIncidenciaError> incidencias) {
        this.incidencias = incidencias;
    }  
    
    
    public class  VehiculoIncidenciaError{
        
        Set<String> idIncidencia = new HashSet<>();
        Set<String> inicio = new HashSet<>();
        Set<String> fin = new HashSet<>();
        
        public VehiculoIncidenciaError(){}

        public Set<String> getIdIncidencia() {
            return idIncidencia;
        }

        public void setIdIncidencia(Set<String> idIncidencia) {
            this.idIncidencia = idIncidencia;
        }

        public Set<String> getInicio() {
            return inicio;
        }

        public void setInicio(Set<String> inicio) {
            this.inicio = inicio;
        }

        public Set<String> getFin() {
            return fin;
        }

        public void setFin(Set<String> fin) {
            this.fin = fin;
        }        
        
    }
    
    
}
