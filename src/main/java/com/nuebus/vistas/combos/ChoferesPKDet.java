package com.nuebus.vistas.combos;

import com.nuebus.model.ChoferPK;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Valeria
 */
public class ChoferesPKDet {
    
    ChoferPK choferPK;   
    String nombreChofer;
    List<String> detalles = new ArrayList<>();
    
    public ChoferesPKDet(){
    
    }

    public ChoferPK getChoferPK() {
        return choferPK;
    }

    public ChoferesPKDet(ChoferPK choferPK, String nombreChofer) {
        this.choferPK = choferPK;
        this.nombreChofer = nombreChofer;
    }
    
    

    public void setChoferPK(ChoferPK choferPK) {
        this.choferPK = choferPK;
    }

    public String getNombreChofer() {
        return nombreChofer;
    }

    public void setNombreChofer(String nombreChofer) {
        this.nombreChofer = nombreChofer;
    }

    
    
    public List<String> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<String> detalles) {
        this.detalles = detalles;
    }    
    
    
}
