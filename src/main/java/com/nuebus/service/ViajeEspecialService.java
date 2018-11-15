
package com.nuebus.service;

import com.nuebus.dto.ViajeEspecialDTO;
import com.nuebus.model.ChoferPK;
import com.nuebus.model.VehiculoPK;
import com.nuebus.vistas.combos.ChoferesPKDet;
import com.nuebus.vistas.combos.ComboChoferes;
import com.nuebus.vistas.combos.ComboVehiculo;
import com.nuebus.vistas.combos.VehiculoPKDet;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Valeria
 */
public interface ViajeEspecialService {
    
    public void saveViajeEspecial( ViajeEspecialDTO  viajeEspecialDTO )throws Exception;
    
    public List<ViajeEspecialDTO> findViajesByFecha( String empresa, 
            java.util.Date inicio,  java.util.Date fin );       
    
    public Page<ViajeEspecialDTO> findViajesByEmpresaByFechas(Pageable pageable, String empresa, java.util.Date inicio,  java.util.Date fin);
    
    public void setVehiculo( long viaje, VehiculoPK vehiculoPK ) throws Exception;    
    
    public void setChoferes( long viaje, List<ChoferPK> choferesPK ) throws Exception;
    
    public void setAuxiliares( long viaje, List<ChoferPK> auxiliaresPK ) throws Exception;
    
    public void deleteViajeEspecial( long idViaje );
    
    /*public List<ChoferPK> finChoferesByViaje( long idViaje);*/
    
    public List<ChoferesPKDet> finChoferesByViaje( long idViaje);       
    
    public List<ChoferesPKDet> findAuxiliaresByViaje( long idViaje);       
    
    public List<ComboChoferes> findChoferesLibreByViaje( long idViaje );
   
    public List<ComboChoferes> findAuxiliaresLibreByViaje( long idViaje );
    
    public List<ComboVehiculo> findVehiculosByEmpresa( long idViaje );
    
    public List<ComboVehiculo> findVehiculosLibresByViaje( long idViaje );
    
    public VehiculoPKDet findVehiculoByViaje( long idViaje );
    
    public void updateChofer( long idViaje, ViajeEspecialDTO  viajeEspecialDTO )throws Exception;
}
