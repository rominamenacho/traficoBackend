
package com.nuebus.dto;

import com.nuebus.model.Linea;
import com.nuebus.model.LineaPK;
import java.io.Serializable;

/**
 *
 * @author Valeria
 */

public class EnlaceLineasDTO implements Serializable {
    
    LineaPK idaPK;     
    LineaPK vueltaPK;

    public EnlaceLineasDTO() {
    }     

    public LineaPK getIdaPK() {
        return idaPK;
    }

    public void setIdaPK(LineaPK idaPK) {
        this.idaPK = idaPK;
    }

    public LineaPK getVueltaPK() {
        return vueltaPK;
    }

    public void setVueltaPK(LineaPK vueltaPK) {
        this.vueltaPK = vueltaPK;
    }     
    
}
