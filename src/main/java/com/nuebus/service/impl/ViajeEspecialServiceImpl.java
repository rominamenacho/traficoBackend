
package com.nuebus.service.impl;

import com.nuebus.dto.ViajeEspecialDTO;
import com.nuebus.erroresJson.WrapChoferPKError;
import com.nuebus.excepciones.AtributoException;
import com.nuebus.excepciones.ValidacionAtributoException;
import com.nuebus.excepciones.ValidacionExcepcion;
import com.nuebus.excepciones.ValidacionGenerica;
import com.nuebus.mapper.ViajeEspecialMapper;
import com.nuebus.model.AuxiliarViaje;
import com.nuebus.model.Chofer;
import com.nuebus.model.ChoferPK;
import com.nuebus.model.ChoferViaje;
import com.nuebus.model.Vehiculo;
import com.nuebus.model.VehiculoPK;
import com.nuebus.model.ViajeEspecial;
import com.nuebus.repository.ChoferRepository;
import com.nuebus.repository.EscalaRepository;
import com.nuebus.repository.VehiculoRepository;
import com.nuebus.repository.ViajeEspecialRepository;
import com.nuebus.service.ViajeEspecialService;
import com.nuebus.utilidades.Utilities;
import com.nuebus.vistas.MapperVistas;
import com.nuebus.vistas.combos.ChoferesPKDet;
import com.nuebus.vistas.combos.ComboChoferes;
import com.nuebus.vistas.combos.ComboVehiculo;
import com.nuebus.vistas.combos.VehiculoPKDet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Valeria
 */

@Service
@Transactional
public class ViajeEspecialServiceImpl implements ViajeEspecialService{
    
    @Autowired
    ViajeEspecialRepository viajeEspecialRepository;
    
    @Autowired
    ViajeEspecialMapper viajeEspecialMapper;
    
    @Autowired
    VehiculoRepository vehiculoRepository;
    
    @Autowired
    ChoferRepository choferRepository;
    
    @Autowired
    EscalaRepository escalaRepository;
    

    @Override
    @Transactional
    public void saveViajeEspecial(ViajeEspecialDTO viajeEspecialDTO)throws Exception{  
        
        ViajeEspecial viaje = viajeEspecialMapper.toEntity(viajeEspecialDTO);
        Map<String, Set<String>> errors = Utilities.validarEntity( viaje );             
        if( errors.isEmpty() ){
            viajeEspecialRepository.save(viaje);        
        }else{
            throw new ValidacionExcepcion( errors );   
        }        
    }

    @Override
    @SuppressWarnings("empty-statement")
    public List<ViajeEspecialDTO> findViajesByFecha(String empresa, Date inicio, Date fin) {
        
        // return incidenciaRepository.findAll(pageable).map(incidencia -> incidenciaMapper.toDTO(incidencia));
        return viajeEspecialRepository.findViajesByFecha(  empresa,  inicio,  fin ).stream()
                .map( viajeEspecial -> viajeEspecialMapper.toDTO(viajeEspecial)).collect(Collectors.toList()); 
        
    }

    @Override
    public Page<ViajeEspecialDTO> findViajesByEmpresaByFechas(Pageable pageable, String empresa, Date inicio, Date fin) {
        
        MapperVistas mapper =  new MapperVistas();        
        
        Page<ViajeEspecialDTO> retorno = viajeEspecialRepository.findViajesByEmpresaAndFecha(empresa, inicio, fin, pageable)
            .map(viajeEspecial ->  mapper.toDTO( viajeEspecial,
                                                 getDetalleConflictos( viajeEspecial ),
                                                 getDetConflictosVehiculos(viajeEspecial),
                                                 escalaRepository.findById(viajeEspecial.getOrigen()).orElse( null ) ,
                                                 escalaRepository.findById(viajeEspecial.getDestino()).orElse( null ) ) );        
       
        
        return retorno;
    }    
    

    @Override
    public void setVehiculo( long viaje,  VehiculoPK vehiculoPK )  throws Exception {
        
        ViajeEspecial viajeEspecial =  viajeEspecialRepository.findById(viaje).orElse( null );         
        Vehiculo vehiculo = vehiculoRepository.findById( vehiculoPK ).orElse( null );                 
        List<String> disponibles = vehiculoDisponiblesByViaje( viajeEspecial );
        String claveVehiculo = vehiculo.getVehiculoPK().getVehEmpCodigo() + vehiculo.getVehiculoPK().getVehInterno();
        
         List<AtributoException> excepciones = new ArrayList<AtributoException>();
        if( !disponibles.contains( claveVehiculo ) ){
                AtributoException unExcep = new AtributoException();
                unExcep.setCampo("vehiculoPK");
                unExcep.setValor(vehiculo.getVehiculoPK());
                unExcep.getMensajes().add("No se encuentra disponible.");                
                excepciones.add( unExcep );
        } 

        if( !excepciones.isEmpty()){
            throw new ValidacionAtributoException( excepciones ); 
        }
        
        viajeEspecial.setVehiculo(vehiculo);                
        viajeEspecialRepository.save(  viajeEspecial );        
        
    }

    @Override
    public void setChoferes(long viaje,  List<ChoferPK> choferesPK) throws Exception {        
        
        ViajeEspecial viajeEsp =  viajeEspecialRepository.findById(viaje).orElse( null );  
                
        //es estack para validar y respetar el orden del array de entrada
        Stack <ChoferViaje > lista = new Stack<>();        
        ChoferViaje choferViaje = null;
        for( ChoferPK choferPk : choferesPK ){        
            choferViaje = new ChoferViaje();
            choferViaje.setInicio( viajeEsp.getFechaHoraSalida() );
            choferViaje.setFin( viajeEsp.getFechaHoraRegreso() );
            choferViaje.setViajeEspecial(viajeEsp);            
            choferViaje.setChofer(choferRepository.findById(choferPk).orElse( null ) );
            lista.add(choferViaje);
        }        
        controlarChoferesLibres( disponiblesByViaje( viajeEsp ), lista);   
        
        viajeEsp.getChoferViaje().clear();
        viajeEsp.getChoferViaje().addAll( lista );
        
        viajeEspecialRepository.save(viajeEsp);    
     
    }
    //idem para aux
     @Override
    public void setAuxiliares(long viaje,  List<ChoferPK> auxiliaresPK) throws Exception {        
        
        ViajeEspecial viajeEsp =  viajeEspecialRepository.findById(viaje).orElse( null );                        
        //es estack para validar y respetar el orden del array de entrada
        Stack <AuxiliarViaje > lista = new Stack<>();        
        AuxiliarViaje auxiliarViaje = null;
        for( ChoferPK choferPk : auxiliaresPK ){        
            auxiliarViaje = new AuxiliarViaje();
            auxiliarViaje.setInicio( viajeEsp.getFechaHoraSalida() );
            auxiliarViaje.setFin( viajeEsp.getFechaHoraRegreso() );
            auxiliarViaje.setViajeEspecial(viajeEsp);            
            auxiliarViaje.setAuxiliar(choferRepository.findById(choferPk).orElse( null ));
            lista.add(auxiliarViaje);
        }        
        controlarAuxiliaresLibres( auxiliaresDisponiblesByViaje( viajeEsp ), lista);   
        
        viajeEsp.getAuxiliarViaje().clear();
        viajeEsp.getAuxiliarViaje().addAll( lista );
        
        viajeEspecialRepository.save(viajeEsp);    
     
    }
    
    private void controlarChoferesLibres( List<String> disponibles, Stack <ChoferViaje > lista ) throws Exception{

        WrapChoferPKError errores = new WrapChoferPKError(); 
        String claveChofer  = ""; 
        boolean tieneErrores = false;
        for( ChoferViaje unChoViaje: lista  ){
            WrapChoferPKError.ChoferPKError choErr = (new WrapChoferPKError()).new ChoferPKError();
            claveChofer =  unChoViaje.getChofer().getChoferPK().getEmpCodigo() + unChoViaje.getChofer().getChoferPK().getCodigo();            
            if( !disponibles.contains( claveChofer ) || claveChofer == null ){
                choErr.getCho_codigo().add("No se encuentra disponible " +  claveChofer);
                tieneErrores = true;
                System.out.println("**No se encuentra disponible " +  claveChofer);
            }             
            errores.getChoferes().add( choErr );
        } 
        
        if( tieneErrores ){
           throw new ValidacionGenerica( errores );  
        }
    }
     private void controlarAuxiliaresLibres( List<String> disponibles, Stack <AuxiliarViaje > lista ) throws Exception{

        WrapChoferPKError errores = new WrapChoferPKError(); 
        String claveChofer  = ""; 
        boolean tieneErrores = false;
        for( AuxiliarViaje unChoViaje: lista  ){
            WrapChoferPKError.ChoferPKError choErr = (new WrapChoferPKError()).new ChoferPKError();
            claveChofer =  unChoViaje.getAuxiliar().getChoferPK().getEmpCodigo() + unChoViaje.getAuxiliar().getChoferPK().getCodigo();            
            if( !disponibles.contains( claveChofer ) || claveChofer == null ){
                choErr.getCho_codigo().add("No se encuentra disponible " +  claveChofer);
                tieneErrores = true;
                System.out.println("**No se encuentra disponible " +  claveChofer);
            }             
            errores.getChoferes().add( choErr );
        } 
        
        if( tieneErrores ){
           throw new ValidacionGenerica( errores );  
        }
    }
    
    //para choferes
    private List<String>  disponiblesByViaje( ViajeEspecial viajeEsp ){
        
        final int HABILITADO = 0;
        final int DESHABILITADO = 1;     
        
        List<String> disponibles = choferRepository.findPersonalByViaje( viajeEsp.getEmpCodigo(), 
                                              viajeEsp.getId(),
                                              viajeEsp.getFechaHoraSalida(),
                                              viajeEsp.getFechaHoraRegreso(), 
                                              HABILITADO, 0).stream().map( choLibre  -> {
                                                                             return ( choLibre.getEmpresa()+ choLibre.getCodigo());                                                                            
                                                                          } ).collect(Collectors.toList());              
        return disponibles;
    }
    
    //para aux
     private List<String>  auxiliaresDisponiblesByViaje( ViajeEspecial viajeEsp ){
        
        final int HABILITADO = 0;
        final int DESHABILITADO = 1;     
        
        List<String> disponibles = choferRepository.findPersonalByViaje( viajeEsp.getEmpCodigo(), 
                                              viajeEsp.getId(),
                                              viajeEsp.getFechaHoraSalida(),
                                              viajeEsp.getFechaHoraRegreso(), 
                                              HABILITADO, 1).stream().map( choLibre  -> {
                                                                             return ( choLibre.getEmpresa()+ choLibre.getCodigo());                                                                            
                                                                          } ).collect(Collectors.toList());              
        return disponibles;
    }
    
    private List<String>  vehiculoDisponiblesByViaje( ViajeEspecial viajeEsp ){
        
        final int HABILITADO = 0;
        final int DESHABILITADO = 1;          
        
        List<String> disponibles = vehiculoRepository.findVehiculosByViajeDisp( viajeEsp.getEmpCodigo(), 
                                                         viajeEsp.getId(),
                                                         viajeEsp.getFechaHoraSalida(),
                                                         viajeEsp.getFechaHoraRegreso(), 
                                                         HABILITADO  ).
                                                         stream().map( obj -> {
                                                                      java.math.BigDecimal interno = (java.math.BigDecimal) obj[1];      
                                                                      return (String) obj[0] + interno.intValue() ;     
                                                                  }).collect( Collectors.toList() );                
        return disponibles;
    }

    @Override
    public void deleteViajeEspecial(long idViaje) {
        
        ViajeEspecial viajeEsp = viajeEspecialRepository.getOne(idViaje);        
        viajeEspecialRepository.delete(viajeEsp);
        
    }
   
    
    @Override
    public List<ChoferesPKDet> finChoferesByViaje(long idViaje) {        
        ///////////hay q validar q sea chofer y no aux
        ViajeEspecial viajeEsp = viajeEspecialRepository.getOne(idViaje);         
        
        List<ChoferesPKDet> lista = new ArrayList<>();
        ChoferesPKDet unChofer;
        String nombreChofer;  
        String claveChofer;
        int esChofer;
        Map<String, Set<String>> listaConf = getDetalleConflictos( viajeEsp );
        
        for( ChoferViaje choferVieja: viajeEsp.getChoferViaje() ){           
            
            unChofer =  new ChoferesPKDet();            
            unChofer.setChoferPK( choferVieja.getChofer().getChoferPK() );
            nombreChofer = choferVieja.getChofer().getChoferPK().getCodigo() + " - " + choferVieja.getChofer().getNombre();
            unChofer.setNombreChofer(nombreChofer);
            claveChofer = choferVieja.getChofer().getChoferPK().getEmpCodigo() + choferVieja.getChofer().getChoferPK().getCodigo();
            esChofer=choferVieja.getChofer().getTipochofer();
            
            if( choferVieja.getChofer().getEstado() != Chofer.HABILITADO ){
                unChofer.getDetalles().add( "El conductor se encuentra deshabilitado."  );
            }
            
            if( listaConf.get(claveChofer) != null ){
                unChofer.getDetalles().addAll( listaConf.get(claveChofer) );
            }      
            if(esChofer == 0){
            lista.add(unChofer);
            }
        }
        
        return lista;         
    }
    
     @Override
    public List<ChoferesPKDet> findAuxiliaresByViaje(long idViaje) {        
        ///////////hay q validar q sea chofer y no aux
        ViajeEspecial viajeEsp = viajeEspecialRepository.getOne(idViaje);         
        
        List<ChoferesPKDet> lista = new ArrayList<>();
        ChoferesPKDet unChofer;
        String nombreChofer;  
        String claveChofer;
        int esChofer;
        Map<String, Set<String>> listaConf = getDetalleConflictos( viajeEsp );
        
        for( AuxiliarViaje auxViaje: viajeEsp.getAuxiliarViaje() ){           
            
            unChofer =  new ChoferesPKDet();            
            unChofer.setChoferPK(auxViaje.getAuxiliar().getChoferPK() );
            nombreChofer = auxViaje.getAuxiliar().getChoferPK().getCodigo() + " - " + auxViaje.getAuxiliar().getNombre();
            unChofer.setNombreChofer(nombreChofer);
            claveChofer = auxViaje.getAuxiliar().getChoferPK().getEmpCodigo() + auxViaje.getAuxiliar().getChoferPK().getCodigo();
            esChofer=auxViaje.getAuxiliar().getTipochofer();
            
            if( auxViaje.getAuxiliar().getEstado() != Chofer.HABILITADO ){
                unChofer.getDetalles().add( "El auxiliar se encuentra deshabilitado."  );
            }
            
            if( listaConf.get(claveChofer) != null ){
                unChofer.getDetalles().addAll( listaConf.get(claveChofer) );
            }      
            if(esChofer == 1){
            lista.add(unChofer);
            }
        }
        
        return lista;         
    }
    
    private  Map<String, Set<String>> getDetalleConflictos(  ViajeEspecial viajeEspecial ){
        
         List<Object[]>  conflictos = choferRepository.findChoferesConflictoViaje( viajeEspecial.getEmpCodigo(), viajeEspecial.getId(),
                                viajeEspecial.getFechaHoraSalida(), viajeEspecial.getFechaHoraRegreso() );      
        
        String  choferPK;        
        Map<String, Set<String>> listaConf = new HashMap<>();
        
        for( Object[] unConflicto : conflictos ){                     
            
            
            java.math.BigDecimal codigo = (java.math.BigDecimal) unConflicto[0];           
            
            choferPK = ((String) unConflicto[1]).trim() + String.valueOf( codigo.longValue());  
            listaConf.computeIfPresent( choferPK, ( key , value )  -> { value.add( (String) unConflicto[2] ); return value; } ) ;  
            listaConf.computeIfAbsent( choferPK, key -> new HashSet<>()).add( (String) unConflicto[2] );            
        }   
        
        return listaConf;
    }
    
    
     private  Map<String, Set<String>> getDetConflictosVehiculos(  ViajeEspecial viajeEspecial ){
        
         List<Object[]>  conflictos = vehiculoRepository.findVehiculosConflictoByViaje( viajeEspecial.getEmpCodigo(), viajeEspecial.getId(),
                                viajeEspecial.getFechaHoraSalida(), viajeEspecial.getFechaHoraRegreso() );              
        
        Map<String, Set<String>> listaConf = new HashMap<>();
        java.math.BigDecimal interno;
        String vehiculoPK;
        
        for( Object[] unConflicto : conflictos ){ 
            interno = (java.math.BigDecimal) unConflicto[1];                       
            vehiculoPK = ( (String) unConflicto[0] + String.valueOf( interno.intValue() ) );                                  
            listaConf.computeIfPresent( vehiculoPK, ( key , value )  -> { value.add( (String) unConflicto[2] ); return value; } ) ;  
            listaConf.computeIfAbsent( vehiculoPK, key -> new HashSet<>()).add( (String) unConflicto[2] );            
        }                                            
        
        return listaConf;
    }
    
    

    @Override
    public List<ComboChoferes> findChoferesLibreByViaje( long idViaje ) {    
       
        final int HABILITADO = 0;
        final int DESHABILITADO = 1;
        
        ViajeEspecial viajeEsp = viajeEspecialRepository.getOne(idViaje);        
        
        List<ChoferRepository.ChoferLibre> libres = choferRepository.findPersonalByViaje( viajeEsp.getEmpCodigo(), 
                                                                      viajeEsp.getId(),
                                                                      viajeEsp.getFechaHoraSalida(),
                                                                      viajeEsp.getFechaHoraRegreso(), 
                                                                      HABILITADO, 0 );       
        
        List<ComboChoferes> combo = new ArrayList<>();
        ComboChoferes unCombo;
                
        for( ChoferRepository.ChoferLibre choLibre: libres ){            
            unCombo = new ComboChoferes( choLibre.getEmpresa(), choLibre.getCodigo(), choLibre.getNombre());
            combo.add(unCombo);
        }
        
        return  combo;       
       
    } 
    
    //aux
    @Override
    public List<ComboChoferes> findAuxiliaresLibreByViaje( long idViaje ) {    
       
        final int HABILITADO = 0;
        final int DESHABILITADO = 1;
        
        ViajeEspecial viajeEsp = viajeEspecialRepository.getOne(idViaje);        
        
        List<ChoferRepository.ChoferLibre> libres = choferRepository.findPersonalByViaje( viajeEsp.getEmpCodigo(), 
                                                                      viajeEsp.getId(),
                                                                      viajeEsp.getFechaHoraSalida(),
                                                                      viajeEsp.getFechaHoraRegreso(), 
                                                                      HABILITADO, 1 );       
        
        List<ComboChoferes> combo = new ArrayList<>();
        ComboChoferes unCombo;
                
        for( ChoferRepository.ChoferLibre choLibre: libres ){            
            unCombo = new ComboChoferes( choLibre.getEmpresa(), choLibre.getCodigo(), choLibre.getNombre());
            combo.add(unCombo);
        }
        
        return  combo;       
       
    } 
    

    @Override
    public List<ComboVehiculo> findVehiculosLibresByViaje(long idViaje) {     
        //por ahora solo de las empresa    
        
        final int HABILITADO = 0;
        final int DESHABILITADO = 1;
        
        ViajeEspecial viajeEsp = viajeEspecialRepository.getOne(idViaje);                 
        List<Object[]>  vehiculos = vehiculoRepository.findVehiculosByViajeDisp( viajeEsp.getEmpCodigo(), 
                                                                      viajeEsp.getId(),
                                                                      viajeEsp.getFechaHoraSalida(),
                                                                      viajeEsp.getFechaHoraRegreso(), 
                                                                      HABILITADO  );
        
        List<ComboVehiculo> comboVehi = new ArrayList<>();        
        java.math.BigDecimal interno;                
        VehiculoPK unVehiculo;
        for( Object[] unVehi: vehiculos ){            
            interno = (java.math.BigDecimal) unVehi[1];      
            unVehiculo = new VehiculoPK( (String) unVehi[0],  interno.intValue() );            
            comboVehi.add( new ComboVehiculo( unVehiculo, interno.intValue() ) );            
        }        
        
        return comboVehi;      
        
    }

    @Override
    public VehiculoPKDet findVehiculoByViaje(long idViaje) {
        
        ViajeEspecial viajeEsp = viajeEspecialRepository.getOne(idViaje);          
        Map<String, Set<String>> listaConf = getDetConflictosVehiculos(viajeEsp );
        String claveVehiculo;
        
        VehiculoPKDet vehiculoPKDet = new VehiculoPKDet();
        vehiculoPKDet.setVehiculoPK(viajeEsp.getVehiculo().getVehiculoPK());
        vehiculoPKDet.setInterno( viajeEsp.getVehiculo().getVehiculoPK().getVehInterno() );
        
        if( viajeEsp.getVehiculo().getVehEstado() != Vehiculo.HABILITADO ){
            vehiculoPKDet.getDetalles().add( " El interno " 
                                             + viajeEsp.getVehiculo().getVehiculoPK().getVehInterno()
                                             + " se encuentra deshabilitado" );
        }
        
        claveVehiculo = viajeEsp.getVehiculo().getVehiculoPK().getVehEmpCodigo() + viajeEsp.getVehiculo().getVehiculoPK().getVehInterno();
        
        if( listaConf.get(claveVehiculo) != null ){
            vehiculoPKDet.getDetalles().addAll( listaConf.get(claveVehiculo) );
        }   
        
        return vehiculoPKDet;          
        
    }

    @Override
    public List<ComboVehiculo> findVehiculosByEmpresa(long idViaje) {
          //por ahora solo de las empresa        
        ViajeEspecial viajeEsp = viajeEspecialRepository.getOne(idViaje);                 
        return vehiculoRepository.findVehiculosByEmpresa( viajeEsp.getEmpCodigo() ); 
    }

    @Override
    @Transactional
    public void updateChofer( long idViaje, ViajeEspecialDTO  viajeEspecialDTO )throws Exception {      
        
        ViajeEspecial viajeEsp = viajeEspecialRepository.getOne(idViaje); 
        viajeEsp.setObservaciones( viajeEspecialDTO.getObservaciones()  );        
        viajeEspecialRepository.save( viajeEsp);
        
    }
    
}
