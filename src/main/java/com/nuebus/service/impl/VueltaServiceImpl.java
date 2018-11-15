/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuebus.service.impl;

import com.nuebus.dto.VueltaDTO;
import com.nuebus.erroresJson.WrapChoferPKError;
import com.nuebus.excepciones.AtributoException;
import com.nuebus.excepciones.ValidacionAtributoException;
import com.nuebus.excepciones.ValidacionExcepcion;
import com.nuebus.excepciones.ValidacionGenerica;
import com.nuebus.mapper.VueltaMapper;
import com.nuebus.model.Chofer;
import com.nuebus.model.ChoferPK;
import com.nuebus.model.ServicioPK;
import com.nuebus.model.Vehiculo;
import com.nuebus.model.VehiculoPK;
import com.nuebus.model.Vuelta;
import com.nuebus.model.ServiciosChoferes;
import com.nuebus.model.ServiciosVehiculos;
import com.nuebus.repository.ChoferRepository;
import com.nuebus.repository.VehiculoRepository;
import com.nuebus.repository.VueltaRepository;
import com.nuebus.service.VueltaService;
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
 * @author Usuario
 */
@Service
@Transactional(readOnly = true)
public class VueltaServiceImpl implements VueltaService {

    @Autowired
    private VueltaRepository vueltaRepository;

    @Autowired
    private VueltaMapper vueltaMapper;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private ChoferRepository choferRepository;

    @Override
    @Transactional
    public void saveVuelta(VueltaDTO vueltaDTO) throws Exception {

        Vuelta v = getVueltaMapper().toEntity(vueltaDTO);
        Map<String, Set<String>> errors = Utilities.validarEntity(v);
        if (errors.isEmpty()) {
            getVueltaRepository().save(v);
        } else {
            throw new ValidacionExcepcion(errors);
        }
    }

    @Override
    @SuppressWarnings("empty-statement")
    public List<VueltaDTO> findViajesByDiagramacion(String empresa,
            int diagramacion_id) {

        // return incidenciaRepository.findAll(pageable).map(incidencia -> incidenciaMapper.toDTO(incidencia));
        return getVueltaRepository().findVueltasByDiagramacion(empresa, diagramacion_id).stream()
                .map(v -> getVueltaMapper().toDTO(v)).collect(Collectors.toList());

    }

    @Override
    public Page<VueltaDTO> findVueltasByEmpresaAndFecha(Pageable pageable, String empresa, Date inicio, Date fin) {

        MapperVistas mapper = new MapperVistas();

        Page<VueltaDTO> retorno = getVueltaRepository().findVueltasByEmpresaAndFecha(empresa, inicio, fin, pageable)
                .map(v -> mapper.toDTO(v,
                getDetalleConflictos(v),
                getDetConflictosVehiculos(v)));

        return retorno;
    }

    @Override
    public void setVehiculos(long v, List<VehiculoPK> vehiculosPK) throws Exception {

        Vuelta vuelta = getVueltaRepository().findOne(v);

        Stack<ServiciosVehiculos> lista = new Stack<>();
        ServiciosVehiculos vehiculosVuelta = null;

        for (VehiculoPK vehPk : vehiculosPK) {
            vehiculosVuelta = new ServiciosVehiculos();
            //  choferVuelta.setInicio( vuelta.getFechaHoraSalida() );
            //choferVuelta.setFin( vuelta.getFechaHoraRegreso() );
            vehiculosVuelta.setVuelta(vuelta);
            vehiculosVuelta.setVehiculoPK(getVehiculoRepository().findOne(vehPk).getVehiculoPK());
            lista.add(vehiculosVuelta);
        }

        List<String> disponibles = vehiculoDisponiblesByViaje(vuelta);
        List<AtributoException> excepciones = new ArrayList<AtributoException>();

        for (VehiculoPK vehiculoPK : vehiculosPK) {
            String claveVehiculo = vehiculoPK.getVehEmpCodigo() + vehiculoPK.getVehInterno();
            if (!disponibles.contains(claveVehiculo)) {
                AtributoException unExcep = new AtributoException();
                unExcep.setCampo("vehiculoPK");
                unExcep.setValor(vehiculoPK);
                unExcep.getMensajes().add("No se encuentra disponible.");
                excepciones.add(unExcep);
            }
            if (!excepciones.isEmpty()) {
                throw new ValidacionAtributoException(excepciones);
            }

        }
        vuelta.getServiciosVehiculos().clear();
        vuelta.getServiciosVehiculos().addAll(lista);

        getVueltaRepository().save(vuelta);

    }

    @Override
    public void setChoferes(long v, List<ChoferPK> choferesPK) throws Exception {

        Vuelta vuelta = getVueltaRepository().findOne(v);
        //es estack para validar y respetar el orden del array de entrada
        Stack<ServiciosChoferes> lista = new Stack<>();
        ServiciosChoferes choferVuelta = null;
        for (ChoferPK choferPk : choferesPK) {
            choferVuelta = new ServiciosChoferes();
            //  choferVuelta.setInicio( vuelta.getFechaHoraSalida() );
            //choferVuelta.setFin( vuelta.getFechaHoraRegreso() );
            choferVuelta.setVuelta(vuelta);
            choferVuelta.setChofer(getChoferRepository().findOne(choferPk));
            lista.add(choferVuelta);
        }
        controlarChoferesLibres(disponiblesByViaje(vuelta), lista);
        
        vuelta.getServiciosChoferes().clear();
        vuelta.getServiciosChoferes().addAll(lista);

        getVueltaRepository().save(vuelta);

    }
    
    @Override
    public void setAuxiliares(long v, List<ChoferPK> choferesPK) throws Exception {

        Vuelta vuelta = getVueltaRepository().findOne(v);
        //es estack para validar y respetar el orden del array de entrada
        Stack<ServiciosChoferes> lista = new Stack<>();
        ServiciosChoferes choferVuelta = null;
        for (ChoferPK choferPk : choferesPK) {
            choferVuelta = new ServiciosChoferes();
            //  choferVuelta.setInicio( vuelta.getFechaHoraSalida() );
            //choferVuelta.setFin( vuelta.getFechaHoraRegreso() );
            choferVuelta.setVuelta(vuelta);
            choferVuelta.setChofer(getChoferRepository().findOne(choferPk));
            lista.add(choferVuelta);
        }
        controlarChoferesLibres(auxiliaresDisponiblesByViaje(vuelta), lista);
        
        vuelta.getServiciosChoferes().clear();
        vuelta.getServiciosChoferes().addAll(lista);

        getVueltaRepository().save(vuelta);

    }

    private void controlarChoferesLibres(List<String> disponibles, Stack<ServiciosChoferes> lista) throws Exception {

        WrapChoferPKError errores = new WrapChoferPKError();
        String claveChofer = "";
        boolean tieneErrores = false;
        for (ServiciosChoferes unChoViaje : lista) {
            WrapChoferPKError.ChoferPKError choErr = (new WrapChoferPKError()).new ChoferPKError();
            claveChofer = unChoViaje.getChofer().getChoferPK().getCho_emp_codigo() + unChoViaje.getChofer().getChoferPK().getCho_codigo();
            if (!disponibles.contains(claveChofer) || claveChofer == null) {
                choErr.getCho_codigo().add("No se encuentra disponible " + claveChofer);
                tieneErrores = true;
                System.out.println("**No se encuentra disponible " + claveChofer);
            }
            errores.getChoferes().add(choErr);
        }

        if (tieneErrores) {
            throw new ValidacionGenerica(errores);
        }
    }
//choferes
    private List<String> disponiblesByViaje(Vuelta v) {

        final int HABILITADO = 0;
        final int DESHABILITADO = 1;

        List<String> disponibles = getChoferRepository().findPersonalByViaje(v.getEmpCodigo(),
                v.getId(),
                v.getFechaHoraSalida(),
                v.getFechaHoraLlegada(),
                HABILITADO, 0).stream().map(choLibre -> {
                    return (choLibre.getEmpresa() + choLibre.getCodigo());
                }).collect(Collectors.toList());
        return disponibles;
    }

    //auxiliares
     private List<String> auxiliaresDisponiblesByViaje(Vuelta v) {

        final int HABILITADO = 0;
        final int DESHABILITADO = 1;

        List<String> disponibles = getChoferRepository().findPersonalByViaje(v.getEmpCodigo(),
                v.getId(),
                v.getFechaHoraSalida(),
                v.getFechaHoraLlegada(),
                HABILITADO, 1).stream().map(choLibre -> {
                    return (choLibre.getEmpresa() + choLibre.getCodigo());
                }).collect(Collectors.toList());
        return disponibles;
    }
    
    private List<String> vehiculoDisponiblesByViaje(Vuelta v) {

        final int HABILITADO = 0;
        final int DESHABILITADO = 1;

        List<String> disponibles = getVehiculoRepository().findVehiculosByViajeDisp(v.getEmpCodigo(),
                v.getId(),
                v.getFechaHoraSalida(),
                v.getFechaHoraLlegada(),
                HABILITADO).
                stream().map(obj -> {
                    java.math.BigDecimal interno = (java.math.BigDecimal) obj[1];
                    return (String) obj[0] + interno.intValue();
                }).collect(Collectors.toList());
        return disponibles;
    }

    @Override
    public void deleteVuelta(long idViaje) {

        Vuelta v = getVueltaRepository().getOne(idViaje);
        getVueltaRepository().delete(idViaje);

    }

    @Override
    public List<ChoferesPKDet> finChoferesByVuelta(long idViaje) {

        Vuelta v = getVueltaRepository().getOne(idViaje);

        List<ChoferesPKDet> lista = new ArrayList<>();
        ChoferesPKDet unChofer;
        String nombreChofer;
        String claveChofer;

        Map<String, Set<String>> listaConf = getDetalleConflictos(v);

        for (ServiciosChoferes choferViaje : v.getServiciosChoferes()) {

            unChofer = new ChoferesPKDet();
            unChofer.setChoferPK(choferViaje.getChofer().getChoferPK());
            nombreChofer = choferViaje.getChofer().getChoferPK().getCho_codigo() + " - " + choferViaje.getChofer().getCho_nombre();
            unChofer.setNombreChofer(nombreChofer);
            claveChofer = choferViaje.getChofer().getChoferPK().getCho_emp_codigo() + choferViaje.getChofer().getChoferPK().getCho_codigo();

            if (choferViaje.getChofer().getCho_estado() != Chofer.HABILITADO) {
                unChofer.getDetalles().add("El chofer se encuentra deshabilitado.");
            }

            if (listaConf.get(claveChofer) != null) {
                unChofer.getDetalles().addAll(listaConf.get(claveChofer));
            }

            lista.add(unChofer);
        }

        return lista;
    }

    private Map<String, Set<String>> getDetalleConflictos(Vuelta v) {

        List<Object[]> conflictos = getChoferRepository().findChoferesConflictoViaje(v.getEmpCodigo(), v.getId(),
                v.getFechaHoraSalida(), v.getFechaHoraLlegada());

        String choferPK;
        Map<String, Set<String>> listaConf = new HashMap<>();

        for (Object[] unConflicto : conflictos) {

            java.math.BigDecimal codigo = (java.math.BigDecimal) unConflicto[0];

            choferPK = ((String) unConflicto[1]).trim() + String.valueOf(codigo.longValue());
            listaConf.computeIfPresent(choferPK, (key, value) -> {
                value.add((String) unConflicto[2]);
                return value;
            });
            listaConf.computeIfAbsent(choferPK, key -> new HashSet<>()).add((String) unConflicto[2]);
        }

        return listaConf;
    }

    private Map<String, Set<String>> getDetConflictosVehiculos(Vuelta v) {

        List<Object[]> conflictos = getVehiculoRepository().findVehiculosConflictoByViaje(v.getEmpCodigo(), v.getId(),
                v.getFechaHoraSalida(), v.getFechaHoraLlegada());

        Map<String, Set<String>> listaConf = new HashMap<>();
        java.math.BigDecimal interno;
        String vehiculoPK;

        for (Object[] unConflicto : conflictos) {
            interno = (java.math.BigDecimal) unConflicto[1];
            vehiculoPK = ((String) unConflicto[0] + String.valueOf(interno.intValue()));
            listaConf.computeIfPresent(vehiculoPK, (key, value) -> {
                value.add((String) unConflicto[2]);
                return value;
            });
            listaConf.computeIfAbsent(vehiculoPK, key -> new HashSet<>()).add((String) unConflicto[2]);
        }

        return listaConf;
    }

    @Override
    public List<ComboChoferes> findChoferesLibreByVuelta(long idViaje) {

        final int HABILITADO = 0;
        final int DESHABILITADO = 1;

        Vuelta v = getVueltaRepository().getOne(idViaje);

        List<ChoferRepository.ChoferLibre> libres = getChoferRepository().findPersonalByViaje(v.getEmpCodigo(),
                v.getId(),
                v.getFechaHoraSalida(),
                v.getFechaHoraLlegada(),
                HABILITADO,0);

        List<ComboChoferes> combo = new ArrayList<>();
        ComboChoferes unCombo;

        for (ChoferRepository.ChoferLibre choLibre : libres) {
            unCombo = new ComboChoferes(choLibre.getEmpresa(), choLibre.getCodigo(), choLibre.getNombre());
            combo.add(unCombo);
        }

        return combo;

    }

    @Override
    public List<ComboChoferes> findAuxiliaresLibreByVuelta(long idViaje) {

        final int HABILITADO = 0;
        final int DESHABILITADO = 1;

        Vuelta v = getVueltaRepository().getOne(idViaje);

        List<ChoferRepository.ChoferLibre> libres = getChoferRepository().findPersonalByViaje(v.getEmpCodigo(),
                v.getId(),
                v.getFechaHoraSalida(),
                v.getFechaHoraLlegada(),
                HABILITADO, 1);

        List<ComboChoferes> combo = new ArrayList<>();
        ComboChoferes unCombo;

        for (ChoferRepository.ChoferLibre choLibre : libres) {
            unCombo = new ComboChoferes(choLibre.getEmpresa(), choLibre.getCodigo(), choLibre.getNombre());
            combo.add(unCombo);
        }

        return combo;

    }
    
    @Override
    public List<ComboVehiculo> findVehiculosLibresByVuelta(long idViaje) {
        //por ahora solo de las empresa    

        final int HABILITADO = 0;
        final int DESHABILITADO = 1;

        Vuelta v = getVueltaRepository().getOne(idViaje);
        List<Object[]> vehiculos = getVehiculoRepository().findVehiculosByViajeDisp(v.getEmpCodigo(),
                v.getId(),
                v.getFechaHoraSalida(),
                v.getFechaHoraLlegada(),
                HABILITADO);

        List<ComboVehiculo> comboVehi = new ArrayList<>();
        java.math.BigDecimal interno;
        VehiculoPK unVehiculo;
        for (Object[] unVehi : vehiculos) {
            interno = (java.math.BigDecimal) unVehi[1];
            unVehiculo = new VehiculoPK((String) unVehi[0], interno.intValue());
            comboVehi.add(new ComboVehiculo(unVehiculo, interno.intValue()));
        }

        return comboVehi;

    }

    @Override
    public VehiculoPKDet findVehiculoByVuelta(long idViaje) {

        Vuelta v = getVueltaRepository().getOne(idViaje);
        Map<String, Set<String>> listaConf = getDetConflictosVehiculos(v);
        String claveVehiculo;
        VehiculoPKDet vehiculoPKDet = new VehiculoPKDet();

        for (ServiciosVehiculos servVeh : v.getServiciosVehiculos()) {

            vehiculoPKDet.setVehiculoPK(servVeh.getVehiculoPK());
            vehiculoPKDet.setInterno(servVeh.getVehiculoPK().getVehInterno());

            if (servVeh.encontrarVehiculoXPK().getVehEstado() != Vehiculo.HABILITADO) {
                vehiculoPKDet.getDetalles().add(" El interno "
                        + servVeh.getVehiculoPK().getVehInterno()
                        + " se encuentra deshabilitado");
            }

            claveVehiculo = servVeh.getVehiculoPK().getVehEmpCodigo() + servVeh.getVehiculoPK().getVehInterno();

            if (listaConf.get(claveVehiculo) != null) {
                vehiculoPKDet.getDetalles().addAll(listaConf.get(claveVehiculo));
            }
        }
        return vehiculoPKDet;

    }

    @Override
    public List<ComboVehiculo> findVehiculosByEmpresa(long idViaje) {
        //por ahora solo de las empresa        
        Vuelta v = getVueltaRepository().getOne(idViaje);
        return getVehiculoRepository().findVehiculosByEmpresa(v.getEmpCodigo());
    }

    // @Override
    @Transactional
    public void updateChofer(long idViaje, VueltaDTO vueltaDTO) throws Exception {

        Vuelta vuelta = getVueltaRepository().getOne(idViaje);
        vuelta.setObservaciones(vueltaDTO.getObservaciones());
        getVueltaRepository().save(vuelta);

    }

    @Override
    public void setServicios(long vuelta, List<ServicioPK> serviciosPK) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the vueltaRepository
     */
    public VueltaRepository getVueltaRepository() {
        return vueltaRepository;
    }

    /**
     * @param vueltaRepository the vueltaRepository to set
     */
    public void setVueltaRepository(VueltaRepository vueltaRepository) {
        this.vueltaRepository = vueltaRepository;
    }

    /**
     * @return the vueltaMapper
     */
    public VueltaMapper getVueltaMapper() {
        return vueltaMapper;
    }

    /**
     * @param vueltaMapper the vueltaMapper to set
     */
    public void setVueltaMapper(VueltaMapper vueltaMapper) {
        this.vueltaMapper = vueltaMapper;
    }

    /**
     * @return the vehiculoRepository
     */
    public VehiculoRepository getVehiculoRepository() {
        return vehiculoRepository;
    }

    /**
     * @param vehiculoRepository the vehiculoRepository to set
     */
    public void setVehiculoRepository(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    /**
     * @return the choferRepository
     */
    public ChoferRepository getChoferRepository() {
        return choferRepository;
    }

    /**
     * @param choferRepository the choferRepository to set
     */
    public void setChoferRepository(ChoferRepository choferRepository) {
        this.choferRepository = choferRepository;
    }

}
