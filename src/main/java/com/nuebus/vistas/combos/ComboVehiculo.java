
package com.nuebus.vistas.combos;

import com.nuebus.model.VehiculoPK;

/**
 *
 * @author Valeria
 */
public class ComboVehiculo {
    
    VehiculoPK vehiculoPK;
    int interno;

    public ComboVehiculo(VehiculoPK vehiculoPK, int interno) {
        this.vehiculoPK = vehiculoPK;
        this.interno = interno;
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
    
    
}
