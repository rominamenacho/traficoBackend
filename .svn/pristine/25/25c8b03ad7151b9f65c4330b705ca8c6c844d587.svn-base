package com.nuebus.erroresJson;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 *
 * @author Valeria
 */
public class WrapChoferIncidenciaError {
    
    List<String> errores = new ArrayList<>();
    
    Stack<ChoferIncidenciaError>  choferIncidencias = new Stack<>();      
    public WrapChoferIncidenciaError(){}

    public Stack<ChoferIncidenciaError> getChoferIncidencias() {
        return choferIncidencias;
    }

    public void setChoferIncidencias(Stack<ChoferIncidenciaError> choferIncidencias) {
        this.choferIncidencias = choferIncidencias;
    }

    public List<String> getErrores() {
        return errores;
    }

    public void setErrores(List<String> errores) {
        this.errores = errores;
    }    
    
    public class ChoferIncidenciaError{

        Set<String> idIncidencia = new HashSet<>();
        Set<String> inicio = new HashSet<>();
        Set<String> fin = new HashSet<>();
        
        public ChoferIncidenciaError(){}      

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
