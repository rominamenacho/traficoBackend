package com.nuebus.helpers;

import com.nuebus.dto.IncidenciaOcupacionDTO;
import com.nuebus.dto.ServicioOcupacionDTO;
import com.nuebus.dto.ViajeOcupacionDTO;
import com.nuebus.dto.VueltaDiagDTO;
import com.nuebus.model.Incidencia;
import com.nuebus.model.ServicioPK;
import com.nuebus.service.IncidenciaService;
import com.nuebus.utilidades.Utilities;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Valeria
 */
@Component
public abstract class CheckVuelta {
    
    @Autowired 
    IncidenciaService   incidenciaService;       
    
    VueltaDiagDTO vuelta;
    Map< String, Set<String>> errores = new HashMap<>();

    public CheckVuelta() {
    }

    abstract void check( VueltaDiagDTO vuelta, Map< String, Set<String>> errores );
    
    public VueltaDiagDTO getVuelta() {
        return vuelta;
    }

    public void setVuelta(VueltaDiagDTO vuelta) {
        this.vuelta = vuelta;
    }

    public Map<String, Set<String>> getErrores() {
        return errores;
    }

    public void setErrores(Map<String, Set<String>> errores) {
        this.errores = errores;
    }
    
    protected void addError( String keyError, String valueError  ){         
      this.errores.computeIfAbsent(  keyError, key -> new HashSet<>() ); 
      this.errores.computeIfPresent( keyError,( key, value ) -> { value.add( valueError ); return value; } );
    }
    
    protected List<ServicioOcupacionDTO> getServiciosConConflicto( ServicioPK servicioPK,
                    Date inicio,
                    Date fin,
                    List<ServicioOcupacionDTO> todosServicios ){               

        List<ServicioOcupacionDTO> servConflictos = todosServicios
            .stream().filter( 
                s ->  (   inicio.getTime() <= s.getFechaHoraLlegada().getTime()
                      &&  fin.getTime() >= s.getFechaHoraSalida().getTime() 
                      &&  !servicioPK.equals( s.getServicioPK() )  )
                     ).collect( Collectors.toList() );
        return servConflictos;
    }
    
    protected List<IncidenciaOcupacionDTO> getIncidConConflicto(
                                              Date inicio, 
                                              Date fin, 
                                              List<IncidenciaOcupacionDTO> todasIncidencias ){
        
        List<IncidenciaOcupacionDTO> incConflictos 
                = todasIncidencias.stream()
                        .filter( i -> ( inicio.getTime() <= i.getFin().getTime()
                                        &&  fin.getTime() >= i.getInicio().getTime() ))
                        .collect( Collectors.toList() );        
        
        return incConflictos;        
    }
    
    protected void generarErroresIncidencia( String sujeto,
                                           List<IncidenciaOcupacionDTO> incConflictos ){ 
        
        incConflictos.forEach( i -> {
            Incidencia incidencia = incidenciaService.getIncidencia( i.getIdIncidencia() );
            String detalle = " Con Incidencia " + incidencia.getDescripcion() 
                           + " Desde "  + Utilities.dateToString( i.getInicio(), Utilities.FORMAT_DATEHOUR)
                           + " Hasta " + Utilities.dateToString( i.getFin(), Utilities.FORMAT_DATEHOUR);
            addError( sujeto, detalle  );                
        });
    
    }
    
    protected void generarErroresViajeEspecial( String sujeto,
                                                List<ViajeOcupacionDTO> vConflictos ){        
        vConflictos.forEach( v -> {            
            String detalle = " En Viaje Especial "
                           + " Desde "  + Utilities.dateToString( v.getInicio(), Utilities.FORMAT_DATEHOUR)
                           + " Hasta " + Utilities.dateToString( v.getFin(), Utilities.FORMAT_DATEHOUR);
            addError(  sujeto,  detalle  );                
        });    
    } 
    
     protected void generarErroresServicio( String sujeto,                 
            List<ServicioOcupacionDTO> servConflictos ){

         servConflictos.forEach( ( serv ) -> { 
            String detalle = serv.getServicioPK().getSerEmpCodigo()+ " "
            + serv.getServicioPK().getSerLinCodigo()+ " "
            + Utilities.dateToString( serv.getServicioPK().getSerFechaHora(), Utilities.FORMAT_DATEHOUR)+ "/"
            + serv.getServicioPK().getSerRefuerzo();
            addError( sujeto, "Ocupado en el servicio " + detalle  );                                                      
        } );

    }
     
    protected List<ViajeOcupacionDTO> getViajesConConflicto( Date inicio, 
                           Date fin,
                           List<ViajeOcupacionDTO> todosViajes ){        
        
        List<ViajeOcupacionDTO> viajesConflictos=
                todosViajes.stream().filter( v -> 
                                                ( ( inicio.getTime() <= v.getFin().getTime()
                                                 &&  fin.getTime() >= v.getInicio().getTime() )))
                                    .collect( Collectors.toList() );        
        return viajesConflictos;   
    }
    
}
