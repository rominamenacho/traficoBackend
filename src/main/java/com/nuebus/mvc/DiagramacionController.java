
package com.nuebus.mvc;

import com.nuebus.dto.ChoferDTO;
import com.nuebus.dto.ComboStrDTO;
import com.nuebus.dto.ListaVuelta;
import com.nuebus.dto.ServicioDTO;
import com.nuebus.dto.VehiculoDTO;
import com.nuebus.dto.VueltaDTO;
import com.nuebus.model.Diagramacion;
import com.nuebus.model.Servicio;
import com.nuebus.service.ChoferService;
import com.nuebus.service.DiagramacionService;
import com.nuebus.service.LineaService;
import com.nuebus.service.ServicioService;
import com.nuebus.service.VehiculoService;
import com.nuebus.service.VueltaService;
import com.nuebus.vistas.combos.ChoferesPKDet;
import com.nuebus.vistas.combos.VehiculoPKDet;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Valeria
 */

@RestController
@CrossOrigin
@RequestMapping(value = "api")
public class DiagramacionController {

   
    @Inject
    private DiagramacionService diagramacionService;

    @Inject
    ServicioService servicioService;

    @Inject
     LineaService lineaService;
    
    @Inject
    VueltaService vueltaService;
    
    
    @Inject
    ChoferService choferService;
    
    @Autowired
    VehiculoService vehiculoService;

    @RequestMapping(value = "/diagr/empresa/{idEmpresa}/lineas", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ComboStrDTO>> finAllLinea( @PathVariable String idEmpresa ) {             
        List<ComboStrDTO> lista = lineaService.finAllLinea( idEmpresa );
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @RequestMapping(value = "/diagr/empresa/{idEmpresa}/linea/{idLinea}/fechaInicio/{inicio}/fechaFin/{fin}/servicios", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Servicio>> finAllServiciosPorLineaYFecha(@PathVariable String idEmpresa, @PathVariable String idLinea,
            @PathVariable("inicio") @DateTimeFormat(pattern = "yyyy-MM-dd") Date inicio,
            @PathVariable("fin") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fin) {
        //     @PathVariable java.util.Date inicio, @PathVariable java.util.Date fin             
        List<Servicio> lista =servicioService.findServiciosByFecha(idEmpresa, idLinea, inicio, fin);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
    
    
    @RequestMapping(value = "/diagr/empresa/{idEmpresa}/linea/{idLinea}/fechaInicio/{inicio}/fechaFin/{fin}/serviciosConHorarios", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ServicioDTO>> finAllServiciosYHorariosPorLineaYFecha(@PathVariable String idEmpresa, @PathVariable String idLinea,
            @PathVariable("inicio") @DateTimeFormat(pattern = "yyyy-MM-dd") Date inicio,
            @PathVariable("fin") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fin) {        
        List<ServicioDTO> lista =servicioService.findServiciosConHorarisoByFecha(idEmpresa, idLinea, inicio, fin);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @RequestMapping(value = "/diagr/{idDiagramacion}/empresa/{idEmpresa}/vueltas", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VueltaDTO>> findAllVueltas(@PathVariable int idDiagramacion, @PathVariable String idEmpresa) {
        List<VueltaDTO> lista = vueltaService.findViajesByDiagramacion(idEmpresa, idDiagramacion);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @RequestMapping(value = "/diagr/{idDiagramacion}/vueltas",
            method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> setVueltas(@PathVariable long idViaje, @Valid @RequestBody ListaVuelta listaVueltas, BindingResult result) throws Exception {

        diagramacionService.setVueltas(idViaje, listaVueltas.getVueltas());

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/diagr", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void saveDiagramacion(@RequestBody Diagramacion diagramacionDTO) throws Exception {
        diagramacionService.saveDiagramacion(diagramacionDTO);
    }

    @RequestMapping(value = "/diagr/{idDiagramacion}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteDiagramacion(@PathVariable long idDiagramacion) {
        diagramacionService.deleteDiagramacion(idDiagramacion);
    }
    
    
    @RequestMapping(value = "/diagr/empresa/{idEmpresa}/choferes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ChoferesPKDet>> finAllChoferes( @PathVariable String idEmpresa ) {             
        
        Page<ChoferDTO> lista = choferService.findChoferesByEmpresa( null, idEmpresa );
        List<ChoferesPKDet> listaCho = lista.getContent().stream().map( cho -> new ChoferesPKDet( cho.getChoferPK(), cho.getCho_nombre() ) )
                .collect( Collectors.toList() );     
        return new ResponseEntity<>(listaCho, HttpStatus.OK);
        
    }
    
    
    @RequestMapping(value = "/diagr/empresa/{idEmpresa}/internos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> finAllInternos( @PathVariable String idEmpresa ) {             
        
        Page<VehiculoDTO> page = vehiculoService.findVehiculosByEmpresa( null, idEmpresa );
        
        List<VehiculoPKDet> internos = page.getContent().stream()
                                            .map( v -> new VehiculoPKDet( v.getVehiculoPK(), v.getVehiculoPK().getVehInterno()) )
                                            .collect( Collectors.toList() );
         
        return new ResponseEntity<>(internos, HttpStatus.OK);
        
    }
    


}
