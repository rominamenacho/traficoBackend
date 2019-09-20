package com.nuebus.service;

import java.util.List;
import com.nuebus.dto.VencimientosChoferDTO;
import com.nuebus.dto.VencimientosVehiculoDTO;
import com.nuebus.model.TipoVencimiento;
import com.nuebus.model.Vencimiento;

public interface VencimientoService {
	
	List<Vencimiento> getVencimientos( String empresa );
	List<TipoVencimiento> getTiposVencimientos( );
	Vencimiento saveVencimiento( Vencimiento vencimiento );
	Vencimiento deleteVencimiento( Long id );
	Vencimiento getVencimiento( Long id );		
	Vencimiento updateVencimiento( Long id, Vencimiento vencimiento );	
	List<Vencimiento> getVencimientosByEmpresaAndNombreEntidad( String empresa, String nombreEntidad, boolean activo );	
	
	List<VencimientosVehiculoDTO> getVehiculosConVencimientos( String vehEmpCodigo,  int vehEstado );	
	List<VencimientosChoferDTO> getChoferesConVencimientos( String cho_emp_codigo, int cho_estado ); 

}
