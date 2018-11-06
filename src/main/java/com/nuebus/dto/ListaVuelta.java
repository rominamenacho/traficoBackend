/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuebus.dto;

import com.nuebus.model.Vuelta;
import java.util.List;
import javax.validation.Valid;

/**
 *
 * @author Usuario
 */
public class ListaVuelta {

    @Valid
    private List<Vuelta> vueltas;

    public ListaVuelta() {
    }

    public List<Vuelta> getVueltas() {
        return vueltas;
    }

    public void setVueltas(List<Vuelta> vueltas) {
        this.vueltas = vueltas;
    }

}
