package com.nuebus.service;

import com.nuebus.dto.ComboStrDTO;
import com.nuebus.dto.EnlaceLineasDTO;
import com.nuebus.model.Linea;
import com.nuebus.vistas.combos.Combo;
import java.util.List;

/**
 *
 * @author Valeria
 */
public interface LineaService {
    
    public List<ComboStrDTO> finAllLinea(String empresa);
    
    public List<Linea> findAllLineas( String idEmpresa );
    
    public void saveEnlaceLineas( EnlaceLineasDTO enlaceDTO );
    
}
