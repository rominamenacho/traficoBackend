
package com.nuebus.dto;

import com.nuebus.model.VehiculoPK;

/**
 *
 * @author Valeria
 */
public class VehiculoMinDTO {
    
    private VehiculoPK vehiculoPK; 
    private int vehEstado = 0;
    
    
    public VehiculoMinDTO(){
    
    }

    public VehiculoMinDTO(VehiculoPK vehiculoPK) {
        this.vehiculoPK = vehiculoPK;
    }     

    public VehiculoPK getVehiculoPK() {
        return vehiculoPK;
    }

    public void setVehiculoPK(VehiculoPK vehiculoPK) {
        this.vehiculoPK = vehiculoPK;
    }

    public int getVehEstado() {
        return vehEstado;
    }

    public void setVehEstado(int vehEstado) {
        this.vehEstado = vehEstado;
    }
    
    
    
}
