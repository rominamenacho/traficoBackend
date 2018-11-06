package com.nuebus.excepciones;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Valeria
 */
public class AtributoException {
    
    String campo="";
    Object valor="";
    List <String> mensajes = new ArrayList<>();

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }   

    public List<String> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<String> mensajes) {
        this.mensajes = mensajes;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }    
    
}
