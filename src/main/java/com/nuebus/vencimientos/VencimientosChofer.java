package com.nuebus.vencimientos;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.nuebus.dto.ChoferConCarnetsDTO;
import com.nuebus.dto.VencimientosChoferDTO;
import com.nuebus.mapper.ChoferMapper;
import com.nuebus.model.Vencimiento;
import com.nuebus.service.ChoferService;
import com.nuebus.service.VencimientoService;

@Component
public  abstract class  VencimientosChofer {
	
	public final static String ENTIDAD_CARNET = "Carnet";
	public final static String FECHA_VENC_CAMPO_CARNET = "fechaVenc";
	public final static boolean VENCIMIENTO_ACTIVO = true;
	
	@Autowired
	VencimientoService vencimientoService;
	@Autowired
	ChoferService choferService;
	@Autowired 
	ChoferMapper choferMapper;
	@Autowired 
	VencimientosChoferCarnet vencimientosChoferCarnet;	
	
	
	public abstract VencimientosChoferDTO calcularVencimietosChofer( String empresa, int estadoChofer, Vencimiento v );	
	public abstract void calcularVencimietosChofer( Vencimiento vencimiento, List<ChoferConCarnetsDTO> choferesDTO );	

}
