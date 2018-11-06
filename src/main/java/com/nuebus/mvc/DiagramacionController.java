
package com.nuebus.mvc;

import com.nuebus.dto.ComboStrDTO;
import com.nuebus.dto.DiagramacionDTO;
import com.nuebus.dto.ListaVuelta;
import com.nuebus.dto.VueltaDTO;
import com.nuebus.model.Diagramacion;
import com.nuebus.model.Servicio;
import com.nuebus.service.DiagramacionService;
import com.nuebus.service.LineaService;
import com.nuebus.service.ServicioService;
import com.nuebus.service.VueltaService;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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


}
