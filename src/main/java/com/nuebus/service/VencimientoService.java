package com.nuebus.service;

import java.util.List;

import com.nuebus.dto.ChoferConCarnetsDTO;
import com.nuebus.dto.VencimientosChoferDTO;
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
	List<VencimientosChoferDTO> calcularVencimientosChoferes( String empresa, int estadoChofer );
	void calcularVencimietosChofer( String empresa,  List<ChoferConCarnetsDTO> choferesDTO);

}
