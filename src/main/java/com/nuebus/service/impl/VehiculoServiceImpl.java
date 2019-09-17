
package com.nuebus.service.impl;

import com.nuebus.builders.VehiculoOcupacionBuilder;
import java.util.List;
import java.util.ArrayList;

import com.nuebus.dto.VehiculoDTO;
import com.nuebus.dto.VehiculoIncidenciaDTO;
import com.nuebus.dto.VehiculoOcupacionDTO;
import com.nuebus.excepciones.ResourceNotFoundException;
import com.nuebus.model.VehiculoIncidencia;
import com.nuebus.excepciones.ValidacionExcepcion;
import com.nuebus.mapper.VehiculoMapper;
import com.nuebus.model.Incidencia;
import com.nuebus.model.MapaAsiento;
import com.nuebus.model.Vehiculo;
import com.nuebus.model.VehiculoPK;
import com.nuebus.model.Vencimiento;
import com.nuebus.repository.IncidenciaRepository;
import com.nuebus.repository.MapaAsientoRepository;
import com.nuebus.repository.VehiculoRepository;
import com.nuebus.service.VehiculoService;
import com.nuebus.vencimientos.vehiculos.VencimientoVehiculoService;
import com.nuebus.vistas.MapperVistas;
import java.util.stream.Collectors;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.nuebus.vistas.combos.ComboStr;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;


/**
 *
 * @author Valeria
 */
@Service
@Transactional(readOnly = true)
public class VehiculoServiceImpl implements VehiculoService{
    
    final static Logger LOG = LoggerFactory.getLogger(VehiculoServiceImpl.class);
    
    @Autowired
    MapperVistas mapperVistas;
    
    @Autowired
    VehiculoRepository vehiculoRepository;
    
    @Autowired 
    IncidenciaRepository incidenciaRepository ;        
    
    @Autowired
    VehiculoMapper vehiculoMapper;     
    
    @Autowired
    MapaAsientoRepository mapaAsientoRepository;
    
    @Autowired
    VencimientoVehiculoService vencimientoVehiculoService;
    
    
     
    @Override
    public Page<VehiculoDTO> findVehiculos(Pageable pageable) {
        return vehiculoRepository.findAll( pageable ).map(vehiculo -> vehiculoMapper.toDTO(vehiculo));
    }

    @Override
    public VehiculoDTO getVehiculo(VehiculoPK id) {        
        Vehiculo vehiculo = getVehiculo( id.getVehEmpCodigo(), id.getVehInterno() );
        return generarDTOYAddVencimientos(vehiculo);               
    }
    
    private Vehiculo getVehiculo(  String vehEmpCodigo, int vehInterno ) {        
        VehiculoPK idOK = new VehiculoPK();
        idOK.setVehEmpCodigo(vehEmpCodigo);
        idOK.setVehInterno(vehInterno);
                
        Vehiculo vehiculo = vehiculoRepository.findById(idOK).orElse( null );
        if (vehiculo == null) {
            throw new ResourceNotFoundException( (long)vehInterno, "Vehiculo no encontrado" );
        }        
        return vehiculo;
    }

    @Override
    @Transactional( readOnly = false)
    public Vehiculo updateVehiculo( VehiculoDTO vehiculoDTO ) throws Exception {        
        Vehiculo vehiculo = getVehiculo( vehiculoDTO.getVehiculoPK().getVehEmpCodigo(), vehiculoDTO.getVehiculoPK().getVehInterno() );       
        vehiculoMapper.mapToEntity(vehiculoDTO, vehiculo);        
        MapaAsiento mapa = getMapaAsiento( vehiculo.getMapaAsiento() );
        vehiculo.setMapaAsiento(mapa);        
        return   vehiculoRepository.save(vehiculo);
    }

    @Override
    @Transactional( readOnly = false )
    public void deleteVehiculo( String vehEmpCodigo, int vehInterno ) {
        Vehiculo vehiculo = getVehiculo( vehEmpCodigo, vehInterno );        
        vehiculoRepository.delete( vehiculo );
    }

    @Override
    @Transactional(readOnly = false)
    public Vehiculo saveVehiculo(VehiculoDTO vehiculoDTO) throws Exception {
        
        Vehiculo vehiculo = vehiculoMapper.toEntity(vehiculoDTO);        
        Map<String, Set<String>> errors = new HashMap<>();
                
        if( existeInterno( vehiculo.getVehiculoPK().getVehEmpCodigo() , vehiculo.getVehiculoPK().getVehInterno()) ){           
            errors.computeIfAbsent( "vehInterno", key -> new HashSet<>()).add( "internoTomado" );             
        }         
        
        if( errors.isEmpty() ){  
        	MapaAsiento mapa = getMapaAsiento( vehiculo.getMapaAsiento() );
            vehiculo.setMapaAsiento(mapa);
            return vehiculoRepository.save(vehiculo);
        }else{            
            throw new ValidacionExcepcion( errors );            
        }        
        
    }

    @Override
    public Page<VehiculoDTO> findAllVehiculosByEmpresa(Pageable pageable, String empresa) {
               
        List<Vencimiento> vencimientos = vencimientoVehiculoService.getVencimientosActivosByVehiculos( empresa );
        Page<VehiculoDTO> page = vehiculoRepository.findVehiculosByEmpresa( empresa, pageable ).map( vehiculo -> {        	
          
            VehiculoDTO vehiculoDTO = vehiculoMapper.toDTO(vehiculo) ;
            
            vehiculoDTO.setVencimientos ( vencimientoVehiculoService.getVencimientosCalculado( vencimientos, vehiculo ) );
            
            return vehiculoDTO;
            
           });        
           
        return  page;
    }
    
    
    @Override
    public boolean existeInterno(String vehEmpCodigo, int vehInterno ) {        
       return  vehiculoRepository.existeInternoByEmpresa( vehEmpCodigo, vehInterno );
    }
    
    
    @Override
    public List<VehiculoIncidenciaDTO> getIncidenciasByVehiculo( String vehEmpCodigo, int vehInterno ){      
        
        Vehiculo vehiculo = getVehiculo( vehEmpCodigo, vehInterno );     
        
        return vehiculo.getVehiculoIncidencias().stream().
                map( vehiInc -> mapperVistas.toVehiculoIncidenciaDTO(vehiInc) ).collect(Collectors.toList()); 
        
    }
    
    @Override
    @Transactional(readOnly = false)
    public void  salvarIncidenciasByVehiculo( String vehEmpCodigo, int vehInterno, Set<VehiculoIncidenciaDTO> incidencias ){      
            
            Vehiculo vehiculo = getVehiculo( vehEmpCodigo, vehInterno );     
            
            VehiculoIncidencia vehInc;
            List<VehiculoIncidencia> lista = new ArrayList<>();
            Incidencia incidencia;
            for( VehiculoIncidenciaDTO incid : incidencias ){
            	
            	incidencia =  incidenciaRepository.findById(incid.getIdIncidencia()).orElse( null );
            	
            	if( incidencia == null) {
                    throw new ResourceNotFoundException( (long)incid.getIdIncidencia(), "Incidencia no encontrada" );
                }  
            	
                vehInc = new VehiculoIncidencia();
                vehInc.setId( incid.getId() );
                vehInc.setVehiculo(vehiculo);
                vehInc.setIncidencia( incidencia );
                vehInc.setInicio( incid.getInicio() );
                vehInc.setFin( incid.getFin() );
                lista.add(vehInc);
            }   
            
            vehiculo.getVehiculoIncidencias().clear();          
            vehiculo.getVehiculoIncidencias().addAll(lista );            
            vehiculoRepository.save( vehiculo );    
    }     

    @Override
    public List<ComboStr> vehiculosByEmpresa(String vehEmpCodigo) {
        //return vehiculoRepository.vehiculosByEmpresa(vehEmpCodigo);
        return new ArrayList<ComboStr>();
    }

    @Override
    public List<VehiculoOcupacionDTO> findVehiculosOcupacionByEmpresa(String empresa, Date inicio, Date fin) {
        
        List<Object[]> objVehiculos = vehiculoRepository.ocupacionVehiculos(empresa, inicio, fin);        
        return  new VehiculoOcupacionBuilder( objVehiculos ).build();       
    }

    @Override
    public List<VehiculoDTO> getVehiculosConVencimientos(String empresa, int estado) {        
                     
        GregorianCalendar fechaControl = new GregorianCalendar();
        fechaControl.add( GregorianCalendar.DATE , 60 );     
        
        List<VehiculoDTO> vehiculos =  vehiculoRepository.getVencimientosByVerificacionTecnica(  
                                             empresa, 
                                             estado,
                                             fechaControl.getTime())
                        .stream().map( vehiculo -> {
                        	  return vehiculoMapper.toDTO(vehiculo); 
                        }).collect( Collectors.toList());
        
        
        return vehiculos;
    }

	@Override
	public List<VehiculoDTO> getVehiculosConVencimientosByFechaVerificacion(String empresa, int estadoVehiculo, Date fechaControl) {
        List<VehiculoDTO> vehiculos =  vehiculoRepository.getVencimientosByVerificacionTecnica(  
                                             empresa, 
                                             estadoVehiculo,
                                             fechaControl )
                        .stream().map( vehiculo -> {                               
                            return vehiculoMapper.toDTO(vehiculo) ;                            		
                        }).collect( Collectors.toList());
        
        
        return vehiculos;
	}

	public VehiculoDTO generarDTOYAddVencimientos( Vehiculo vehiculo ){
            
            String empresa =  vehiculo.getVehiculoPK().getVehEmpCodigo();		
	    List<Vencimiento> vencimientos = vencimientoVehiculoService.getVencimientosActivosByVehiculos( empresa );           
	    VehiculoDTO vehiculoDTO = 	vehiculoMapper.toDTO(vehiculo) ;	     
	    vehiculoDTO.setVencimientos ( vencimientoVehiculoService.getVencimientosCalculado( vencimientos, vehiculo ) );	 
	     
	    return vehiculoDTO;
	}
	
	@Override
	public Page<VehiculoDTO> findByEmpresaAndBusqueda( String empresa, String busqueda, Pageable pageable ) {
		busqueda = busqueda.toUpperCase();		
		List<Vencimiento> vencimientos = vencimientoVehiculoService.getVencimientosActivosByVehiculos( empresa );
                Page<VehiculoDTO> page = vehiculoRepository.findAllByEmpresaAndBusqueda( empresa, busqueda, pageable )
        		.map( vehiculo -> {        	
          
	            VehiculoDTO vehiculoDTO = vehiculoMapper.toDTO(vehiculo);
	            
	            vehiculoDTO.setVencimientos( vencimientoVehiculoService.getVencimientosCalculado( vencimientos, vehiculo ) );	            
	            return vehiculoDTO;            
         });        
           
        return  page;
	}
	
	MapaAsiento getMapaAsiento( MapaAsiento mapaAsiento ){
		MapaAsiento mapa = mapaAsientoRepository.findById( mapaAsiento.getMapaAsientoPK() ).orElse(null);
		if( mapa == null ) {
			throw new ResourceNotFoundException((long)-1,"El mapa asiento no existe");
		}
		return  mapa;
	}

    
   
}
