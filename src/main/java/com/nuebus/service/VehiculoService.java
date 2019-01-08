package com.nuebus.service;

import com.nuebus.dto.VehiculoDTO;
import com.nuebus.dto.VehiculoIncidenciaDTO;
import com.nuebus.dto.VehiculoOcupacionDTO;
import com.nuebus.model.VehiculoIncidencia;
import com.nuebus.model.VehiculoPK;
import com.nuebus.vistas.combos.ComboStr;
import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Valeria
 */
public interface VehiculoService {
    
    public Page<VehiculoDTO> findVehiculos(Pageable pageable);
    public VehiculoDTO getVehiculo(VehiculoPK id);    
    public void updateVehiculo(VehiculoDTO vehiculoDTO)throws Exception;    
    public void deleteVehiculo( String vehEmpCodigo, int vehInterno );
    public void saveVehiculo(VehiculoDTO vehiculoDTO)throws Exception;
    public Page<VehiculoDTO> findVehiculosByEmpresa(Pageable pageable, String empresa);
    public boolean existeInterno(String vehEmpCodigo, int vehInterno ); 
    
    public List<VehiculoIncidenciaDTO> getIncidenciasByVehiculo( String vehEmpCodigo, int vehInterno );    
    public void  salvarIncidenciasByVehiculo( String vehEmpCodigo, int vehInterno, Set<VehiculoIncidenciaDTO> incidencias );
    
    public List<ComboStr> vehiculosByEmpresa( String vehEmpCodigo );
    public List<VehiculoOcupacionDTO> findVehiculosOcupacionByEmpresa( String empresa,  java.util.Date inicio, java.util.Date fin );
    
}
