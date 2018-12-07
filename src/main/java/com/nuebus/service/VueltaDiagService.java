
package com.nuebus.service;

import com.nuebus.dto.VueltaDiagDTO;
import com.nuebus.model.VueltaDiag;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Valeria
 */

public interface VueltaDiagService {
    
    void saveVueltaDiag( VueltaDiagDTO  vueltaDiagDTO);   
    List<VueltaDiag> getVueltas( String empresa, String linea, Date inicio, Date fin);      
    
}
