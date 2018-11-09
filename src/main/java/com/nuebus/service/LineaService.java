package com.nuebus.service;

import com.nuebus.dto.ComboStrDTO;
import com.nuebus.dto.EnlaceLineasDTO;
import com.nuebus.model.EnlaceLineas;
import com.nuebus.model.Linea;
import com.nuebus.vistas.combos.Combo;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Valeria
 */
public interface LineaService {
    
    public List<ComboStrDTO> finAllLinea(String empresa);
    
    public List<Linea> findAllLineas( String idEmpresa );
    
    public void saveEnlaceLineas( EnlaceLineasDTO enlaceDTO );
    
    public Page<EnlaceLineas> finAllEnlaceLineasByEmpresa( String empCodigo, Pageable pageable );
    
    public void deleteEnlaceLineas( Long idEnlace );
}
