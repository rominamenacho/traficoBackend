package com.nuebus.service.impl;

import com.nuebus.dto.ChoferEtapasDTO;
import com.nuebus.dto.ServicioDTO;
import com.nuebus.dto.VehiculoEtapaDTO;
import com.nuebus.dto.VehiculoOcupacionDTO;
import com.nuebus.dto.VueltaDiagDTO;
import com.nuebus.excepciones.ResourceNotFoundException;
import static com.nuebus.model.Chofer.AUXILIAR;
import com.nuebus.model.Servicio;
import com.nuebus.model.ServicioPK;
import com.nuebus.model.VueltaDiag;
import com.nuebus.repository.ServicioRepository;
import com.nuebus.repository.VueltaDiagRepository;
import com.nuebus.service.ServicioService;
import com.nuebus.service.VehiculoService;
import com.nuebus.service.VueltaDiagService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Valeria
 */

@Service
@Transactional( readOnly = true )
public class VueltaDiagServiceImpl implements VueltaDiagService {

    @Autowired
    VueltaDiagRepository vueltaDiagRepository;
    
    @Autowired
    ServicioService servicioService; 
    
    @Autowired
    ServicioRepository servicioRepository;
    
    @Autowired
    VehiculoService vehiculoService;
    
    @Transactional( readOnly = false)
    @Override    
    public VueltaDiag saveVueltaDiag( VueltaDiagDTO vueltaDiagDTO ) {        
        
        Servicio servIda = servicioService.findServicioById( vueltaDiagDTO
                                                             .getServIda()
                                                             .getServicioPK() );
        
        Servicio serVt = servicioService.findServicioById( vueltaDiagDTO
                                                           .getServRet()
                                                           .getServicioPK() );  
        
        
        
        //Actualizo Choferes Ida        
        updateChoferesYAuxiliares( vueltaDiagDTO.getServIda(), vueltaDiagDTO.getServIda().getChoferes() );
        
        //Actualizo Choferes Vta        
        updateChoferesYAuxiliares( vueltaDiagDTO.getServRet(), vueltaDiagDTO.getServRet().getChoferes() );
        
        //Actualizo Vehiculo Ida        
        updateUnidad( vueltaDiagDTO.getServIda().getServicioPK(), new ArrayList<>( vueltaDiagDTO.getServIda().getVehiculos()) );
        
        //Actualizo Vehiculo Vta        
        updateUnidad( vueltaDiagDTO.getServRet().getServicioPK(), new ArrayList<>( vueltaDiagDTO.getServRet().getVehiculos()) );
        
        VueltaDiag vuelta = new VueltaDiag();        
        vuelta.setEmpresa( vueltaDiagDTO.getEmpresa() );
        vuelta.setPeliIda( vueltaDiagDTO.getPeliIda() );
        vuelta.setVideoIda(vueltaDiagDTO.getVideoIda());
        vuelta.setPeliVta( vueltaDiagDTO.getPeliVta());
        vuelta.setVideoVta(vueltaDiagDTO.getVideoVta());
        vuelta.setServicio(servIda);
        vuelta.setServicioRet(serVt);
        
        vueltaDiagRepository.save( vuelta );    
        
        return vuelta;
    }
    
    
    @Transactional( readOnly = false)    
    @Override
    public VueltaDiag modificarVueltaDiag(Long id, VueltaDiagDTO vueltaDiagDTO) {
        
        VueltaDiag vuelta = vueltaDiagRepository.findOne( id );  
        
        if( vuelta == null  ){
            throw new ResourceNotFoundException(id,"Vuelta No encontrada"); 
        }   
        
         
        Servicio serVt = servicioService.findServicioById( vueltaDiagDTO
                                                           .getServRet()
                                                           .getServicioPK() );  
        
                //Actualizo Choferes Ida        
        updateChoferesYAuxiliares( vueltaDiagDTO.getServIda(), vueltaDiagDTO.getServIda().getChoferes() );
        
        //Actualizo Choferes Vta        
        updateChoferesYAuxiliares( vueltaDiagDTO.getServRet(), vueltaDiagDTO.getServRet().getChoferes() );
        
        //Actualizo Vehiculo Ida        
        updateUnidad( vueltaDiagDTO.getServIda().getServicioPK(), new ArrayList<>( vueltaDiagDTO.getServIda().getVehiculos()) );
        
        //Actualizo Vehiculo Vta        
        updateUnidad( vueltaDiagDTO.getServRet().getServicioPK(), new ArrayList<>( vueltaDiagDTO.getServRet().getVehiculos()) );
        
        
        vuelta.setEmpresa( vueltaDiagDTO.getEmpresa() );
        vuelta.setPeliIda( vueltaDiagDTO.getPeliIda() );
        vuelta.setVideoIda(vueltaDiagDTO.getVideoIda());
        vuelta.setPeliVta( vueltaDiagDTO.getPeliVta());
        vuelta.setVideoVta(vueltaDiagDTO.getVideoVta());
        vuelta.setServicioRet(serVt);        
        vueltaDiagRepository.save( vuelta );
     
        return vuelta;       
    }
    
    @Transactional( readOnly = false)    
    @Override
    public VueltaDiag deleteVueltaDiag(Long id) {
        VueltaDiag vuelta = vueltaDiagRepository.findOne(id);
        
        if( vuelta == null  ){
            throw new ResourceNotFoundException(id,"Vuelta No encontrada"); 
        }        
        vueltaDiagRepository.delete(id);        
        return vuelta;
    }

    
    
    private void limpiarChoferesYUnidad( ServicioDTO serv ){
        
        ServicioPK servPk = serv.getServicioPK();         
        int total = servicioRepository.limpiarChoferesYUnidad( servPk.getSerEmpCodigo(), 
                                                   servPk.getSerLinCodigo(), 
                                                   servPk.getSerFechaHora(), 
                                                   servPk.getSerRefuerzo(), 
                                                   serv.getEtaInicio(), 
                                                   serv.getEtaFin() );        
    }
    
    private void updateUnidad( ServicioPK servPk , List<VehiculoEtapaDTO> vehiculos ){
        
        if( vehiculos != null &&  vehiculos.size() >= 1 ){                
            VehiculoEtapaDTO vehUno = vehiculos.get(0);           
            
            int veh1 = servicioRepository.updateUnidad( vehUno.getVehiculoPK().getVehInterno(),
                                              servPk.getSerEmpCodigo(), 
                                              servPk.getSerLinCodigo(), 
                                              servPk.getSerFechaHora(), 
                                              servPk.getSerRefuerzo(), 
                                              vehUno.getEtaDesde(), 
                                              vehUno.getEtaHasta() );            
        }
    
    }
    
    private void updateChoferesYAuxiliares( ServicioDTO serv, Set<ChoferEtapasDTO> choferes ){ 
        limpiarChoferesYUnidad( serv );
        updateChoferes(  serv,  choferes );
        updateAuxiliares(  serv,  choferes );
    }
    
    private void updateChoferes( ServicioDTO serv,  Set<ChoferEtapasDTO> choferes   ){
        
         ServicioPK servPk = serv.getServicioPK(); 
         List< ChoferEtapasDTO > soloChoferes = choferes.stream()
                                                   .filter( c -> c.getTipoChofer() == 0  )
                                                   .collect( Collectors.toList() );

        ///Actualiza Chofer Uno  y dos                
        //Lo hago individualmente porque pueden tener cho entre desde y hasta distintos       
        
        if( soloChoferes != null && soloChoferes.size() >= 1 && soloChoferes.get(0) != null  ){ 
            ChoferEtapasDTO choUno = soloChoferes.get(0);
            int cho1 = servicioRepository.updateChofer1( choUno.getChoferPK().getCho_codigo(),
                                              servPk.getSerEmpCodigo(), 
                                              servPk.getSerLinCodigo(), 
                                              servPk.getSerFechaHora(), 
                                              servPk.getSerRefuerzo(), 
                                              choUno.getEtaDesde(), 
                                              choUno.getEtaHasta() );            
        }        
        
        if( soloChoferes != null && soloChoferes.size() >= 2 && soloChoferes.get(1) != null  ){ 
            ChoferEtapasDTO choDos = soloChoferes.get(1);
            int cho2 = servicioRepository.updateChofer2(choDos.getChoferPK().getCho_codigo(),
                                              servPk.getSerEmpCodigo(), 
                                              servPk.getSerLinCodigo(), 
                                              servPk.getSerFechaHora(), 
                                              servPk.getSerRefuerzo(), 
                                              choDos.getEtaDesde(), 
                                              choDos.getEtaHasta() );             
        }         
        
    }
    
    private void updateAuxiliares( ServicioDTO serv,  Set<ChoferEtapasDTO> choferes   ){
        
        ServicioPK servPk = serv.getServicioPK();                 
        List< ChoferEtapasDTO > soloAux = choferes.stream()
                                                  .filter( c -> c.getTipoChofer() == AUXILIAR  )
                                                  .collect( Collectors.toList() );        
        //Actualiza Aux Uno  y dos        
        //Lo hago individualmente porque pueden tener cho entre desde y hasta distintos           
        if( soloAux !=  null && soloAux.size() >= 1 && soloAux.get(0) != null  ){ 
            ChoferEtapasDTO auxUno = soloAux.get(0);
            int aux1 = servicioRepository.updateAuxiliar1(auxUno.getChoferPK().getCho_codigo(),
                                              servPk.getSerEmpCodigo(), 
                                              servPk.getSerLinCodigo(), 
                                              servPk.getSerFechaHora(), 
                                              servPk.getSerRefuerzo(), 
                                              auxUno.getEtaDesde(), 
                                              auxUno.getEtaHasta() );             
        }    
        
        if( soloAux !=  null && soloAux.size() >= 2 && soloAux.get(1) != null  ){ 
            ChoferEtapasDTO auxDos = soloAux.get(1);
            int aux2 = servicioRepository.updateAuxiliar2(auxDos.getChoferPK().getCho_codigo(),
                                              servPk.getSerEmpCodigo(), 
                                              servPk.getSerLinCodigo(), 
                                              servPk.getSerFechaHora(), 
                                              servPk.getSerRefuerzo(), 
                                              auxDos.getEtaDesde(), 
                                              auxDos.getEtaHasta() );            
        }    
    
    }

    @Override
    public List<VueltaDiag> getVueltas(String empresa, String linea, Date inicio, Date fin) {
        
        return vueltaDiagRepository.findByFechaServiciosIda(empresa, linea, inicio, fin);
        
    }

    @Override
    public void checkVueltaDiag( VueltaDiagDTO vueltaDiagDTO ) {
                
        Date inicio = vueltaDiagDTO.getServIda().getFechaHoraSalida();
        Date fin = vueltaDiagDTO.getServRet().getFechaHoraLlegada();
        
        List<VehiculoOcupacionDTO> vehiOcupacion 
                =  vehiculoService.findVehiculosOcupacionByEmpresa( vueltaDiagDTO.getEmpresa(),
                                                                    inicio, 
                                                                    fin );
        //vehiOcupacion.stream().filter( veh ->  )
     
    }
   
   
    
}
