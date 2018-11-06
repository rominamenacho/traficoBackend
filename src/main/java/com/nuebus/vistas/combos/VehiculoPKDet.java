
package com.nuebus.vistas.combos;

import com.nuebus.model.VehiculoPK;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Valeria
 */

public class VehiculoPKDet {
    
    VehiculoPK vehiculoPK;
    int interno;
    List<String> detalles = new ArrayList<>();
    
    public VehiculoPKDet(){
    
    }    

    public VehiculoPK getVehiculoPK() {
        return vehiculoPK;
    }

    public void setVehiculoPK(VehiculoPK vehiculoPK) {
        this.vehiculoPK = vehiculoPK;
    }

    public int getInterno() {
        return interno;
    }

    public void setInterno(int interno) {
        this.interno = interno;
    }

    public List<String> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<String> detalles) {
        this.detalles = detalles;
    } 
    
}
