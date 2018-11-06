
package com.nuebus.dto;

import com.nuebus.model.ChoferPK;
import java.util.List;
import javax.validation.Valid;


/**
 *
 * @author Valeria
 */
public class ListaChoferPK {
    @Valid
    List<ChoferPK> choferesPK;
    
    public ListaChoferPK(){
    
    }

    public List<ChoferPK> getChoferesPK() {
        return choferesPK;
    }

    public void setChoferesPK(List<ChoferPK> choferesPK) {
        this.choferesPK = choferesPK;
    }    
    
}
