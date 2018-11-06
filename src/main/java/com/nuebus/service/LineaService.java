package com.nuebus.service;

import com.nuebus.dto.ComboStrDTO;
import com.nuebus.vistas.combos.Combo;
import java.util.List;

/**
 *
 * @author Valeria
 */
public interface LineaService {
    
    public List<ComboStrDTO> finAllLinea(String empresa);
    
}
