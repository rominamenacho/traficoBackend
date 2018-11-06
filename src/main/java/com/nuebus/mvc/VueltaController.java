/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuebus.mvc;

import com.nuebus.dto.ListaChoferPK;
import com.nuebus.dto.ListaVehiculoPK;
import com.nuebus.dto.VueltaDTO;
import com.nuebus.erroresJson.WrapChoferPKError;
import com.nuebus.erroresJson.WrapVehiculoPKError;
import com.nuebus.model.ChoferPK;
import com.nuebus.model.VehiculoPK;
import com.nuebus.service.ChoferService;
import com.nuebus.service.VehiculoService;
import com.nuebus.service.VueltaService;
import com.nuebus.utilidades.Utilities;
import com.nuebus.vistas.combos.ChoferesPKDet;
import com.nuebus.vistas.combos.ComboChoferes;
import com.nuebus.vistas.combos.ComboStr;
import com.nuebus.vistas.combos.ComboVehiculo;
import com.nuebus.vistas.combos.VehiculoPKDet;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
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
 * @author Usuario
 */
@RestController
@CrossOrigin
@RequestMapping(value = "api")
public class VueltaController {

    @Inject
    private VueltaService vueltaService;
    @Inject
    private VehiculoService vehiculoService;
    @Inject
    private ChoferService choferService;

    @RequestMapping(value = "/Vueltas", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void saveVuelta(@RequestBody VueltaDTO vuelta) throws Exception {
        getVueltaService().saveVuelta(vuelta);
    }

    @RequestMapping(value = "/Vueltas/empresa/{empCodigo}/vehiculos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ComboStr> findVehiculoByEmpresa(@PathVariable String empCodigo) {
        return getVehiculoService().vehiculosByEmpresa(empCodigo);
    }

    @RequestMapping(value = "/Vueltas/empresa/{empCodigo}/fechaInicio/{inicio}/fechaFin/{fin}/vueltas",
            method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<VueltaDTO>> findVueltasByFecha(@PathVariable String empCodigo,
            @PathVariable("inicio") @DateTimeFormat(pattern = "yyyy-MM-dd") Date inicio,
            @PathVariable("fin") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fin,
            Pageable pageable) {

        Page<VueltaDTO> page = getVueltaService().findVueltasByEmpresaAndFecha(pageable, empCodigo, inicio, fin);

        return new ResponseEntity<>(page, HttpStatus.OK);

    }

    //no se si es correcto,desde aca asociar una vuelta a una diagramacion
    /*@RequestMapping(value = "/Vueltas/{idVuelta}/diagramacion/{idDiagramacion}", 
            method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void setVehiculo( @PathVariable long idVuelta, @PathVariable long idDiagramacion)  throws Exception{
        
        vueltaService.setDiagramacion(idVuelta, idDiagramacion);
    }*/
    @RequestMapping(value = "/Vueltas/{idVuelta}/vehiculos",
            method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> setVehiculos(@PathVariable long idVuelta, @Valid @RequestBody ListaVehiculoPK listaVehiculosPK, BindingResult result) throws Exception {

        if (result.hasErrors()) {
            return new ResponseEntity<>(validarVehiculos(listaVehiculosPK.getVehiculosPK()), HttpStatus.CONFLICT);
        }

        getVueltaService().setVehiculos(idVuelta, listaVehiculosPK.getVehiculosPK());

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private WrapVehiculoPKError validarVehiculos(List<VehiculoPK> vehiculosPK) {

        WrapVehiculoPKError errores = new WrapVehiculoPKError();
        for (VehiculoPK vehPK : vehiculosPK) {
            errores.getVehiculos().add((WrapVehiculoPKError.VehiculoPKError) Utilities.validarEntityError(vehPK, (new WrapVehiculoPKError()).new VehiculoPKError()));
        }
        return errores;
    }

    @RequestMapping(value = "/Vueltas/empresa/{empCodigo}/choferes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ComboChoferes>> findChoferesByEmpresa(@PathVariable String empCodigo) {
        final int HABILITADO = 0;
        final int DESHABILITADO = 1;

        return new ResponseEntity<>(getChoferService().getChoferes(empCodigo, HABILITADO), HttpStatus.OK);
    }

    @RequestMapping(value = "/Vueltas/{idVuelta}/choferes",
            method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> setChoferes(@PathVariable long idVuelta, @Valid @RequestBody ListaChoferPK listaChoferesPK, BindingResult result) throws Exception {

        if (result.hasErrors()) {
            return new ResponseEntity<>(validarChoferes(listaChoferesPK.getChoferesPK()), HttpStatus.CONFLICT);
        }

        getVueltaService().setChoferes(idVuelta, listaChoferesPK.getChoferesPK());

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private WrapChoferPKError validarChoferes(List<ChoferPK> choferesPK) {

        WrapChoferPKError errores = new WrapChoferPKError();
        for (ChoferPK choPK : choferesPK) {
            errores.getChoferes().add((WrapChoferPKError.ChoferPKError) Utilities.validarEntityError(choPK, (new WrapChoferPKError()).new ChoferPKError()));
        }
        return errores;
    }

    @RequestMapping(value = "/Vueltas/{idVuelta}/comentarios",
            method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> setComentarios(@PathVariable long idVuelta, @RequestBody VueltaDTO vuelta) throws Exception {

        getVueltaService().updateChofer(idVuelta, vuelta);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/Vueltas/{idVuelta}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void saveVuelta(@PathVariable long idVuelta) {
        getVueltaService().deleteVuelta(idVuelta);
    }

    @RequestMapping(value = "/Vueltas/{idVuelta}/choferes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ChoferesPKDet>> findChoferesByVuelta(@PathVariable long idVuelta) {
        return new ResponseEntity<>(getVueltaService().finChoferesByVuelta(idVuelta), HttpStatus.OK);
    }

    @RequestMapping(value = "/Vueltas/{idVuelta}/choferesDisp", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ComboChoferes>> findChoferesByEmpresa(@PathVariable long idVuelta) {

        return new ResponseEntity<>(getVueltaService().findChoferesLibreByVuelta(idVuelta), HttpStatus.OK);

    }

    @RequestMapping(value = "/Vueltas/{idVuelta}/vehiculosDisp", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ComboVehiculo>> findVehiculosLibresByVuelta(@PathVariable long idVuelta) {

        return new ResponseEntity<>(getVueltaService().findVehiculosLibresByVuelta(idVuelta), HttpStatus.OK);

    }

    @RequestMapping(value = "/Vueltas/{idVuelta}/vehiculos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ComboVehiculo>> findVehiculosByEmpresas(@PathVariable long idVuelta) {

        return new ResponseEntity<>(getVueltaService().findChoferesByEmpresa(idVuelta), HttpStatus.OK);

    }

    @RequestMapping(value = "/Vueltas/{idVuelta}/vehiculo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehiculoPKDet> findVehiculoByVuelta(@PathVariable long idVuelta) {

        return new ResponseEntity<>(getVueltaService().findVehiculoByVuelta(idVuelta), HttpStatus.OK);

    }

    /**
     * @return the vueltaService
     */
    public VueltaService getVueltaService() {
        return vueltaService;
    }

    /**
     * @param vueltaService the vueltaService to set
     */
    public void setVueltaService(VueltaService vueltaService) {
        this.vueltaService = vueltaService;
    }

    /**
     * @return the vehiculoService
     */
    public VehiculoService getVehiculoService() {
        return vehiculoService;
    }

    /**
     * @param vehiculoService the vehiculoService to set
     */
    public void setVehiculoService(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    /**
     * @return the choferService
     */
    public ChoferService getChoferService() {
        return choferService;
    }

    /**
     * @param choferService the choferService to set
     */
    public void setChoferService(ChoferService choferService) {
        this.choferService = choferService;
    }

}
