
package com.nuebus.vistas.combos;

import com.nuebus.model.ChoferPK;
import java.io.Serializable;

/**
 *
 * @author Valeria
 */
public class ComboChoferes implements Serializable{
    
    ChoferPK choferPK;
    String nombreChofer;
    
    public ComboChoferes(){
    
    }
    
    public ComboChoferes( ChoferPK choferPK,  String nombreChofer){        
        this.choferPK = choferPK;
        this.nombreChofer = nombreChofer;    
    }
    
    public ComboChoferes( String cho_emp_codigo, long cho_codigo,  String nombreChofer){        
        this.choferPK = new ChoferPK( cho_emp_codigo, cho_codigo  );
        this.nombreChofer = nombreChofer;    
    }

    public ChoferPK getChoferPK() {
        return choferPK;
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
    
    
}
