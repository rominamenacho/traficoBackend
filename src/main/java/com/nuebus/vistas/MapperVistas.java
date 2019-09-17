package com.nuebus.vistas;

import com.nuebus.dto.CbMapaAsientoDTO;
import com.nuebus.dto.ChoferIncidenciaDTO;
import com.nuebus.dto.ChoferMinDTO;
import com.nuebus.dto.ComboDTO;
import com.nuebus.dto.ComboStrDTO;
import com.nuebus.dto.VehiculoDTO;
import com.nuebus.dto.VehiculoIncidenciaDTO;
import com.nuebus.dto.VehiculoMinDTO;
import com.nuebus.dto.ViajeEspecialDTO;
import com.nuebus.mapper.VehiculoMapper;
import com.nuebus.mapper.ViajeEspecialMapper;
import com.nuebus.model.AuxiliarViaje;
import com.nuebus.model.Chofer;
import com.nuebus.model.ChoferIncidencia;
import com.nuebus.model.ChoferViaje;
import com.nuebus.model.Escala;
import com.nuebus.model.MapaAsiento;
import com.nuebus.model.MapaAsientoPK;
import com.nuebus.model.Vehiculo;
import com.nuebus.model.VehiculoIncidencia;
import com.nuebus.model.ViajeEspecial;
import com.nuebus.repository.MapaAsientoRepository;
import com.nuebus.vistas.combos.CbMapaAsiento;
import com.nuebus.vistas.combos.Combo;
import com.nuebus.vistas.combos.ComboStr;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Valeria
 */
@Component
public class MapperVistas {

    @Autowired
    ViajeEspecialMapper viajeEspecialMapper;
    @Autowired
    VehiculoMapper vehiculoMapper;    
  

    public static CbMapaAsientoDTO toDTO(CbMapaAsiento mapa) {

        CbMapaAsientoDTO mapaDTO = new CbMapaAsientoDTO();
        mapaDTO.setCodigo(mapa.getCodigo());
        mapaDTO.setDescripcion(mapa.getDescripcion());

        return mapaDTO;
    }

    public static ComboDTO toDTO(Combo combo) {

        ComboDTO comboDTO = new ComboDTO();
        comboDTO.setCodigo(combo.getCodigo());
        comboDTO.setDescripcion(combo.getDescripcion());

        return comboDTO;
    }

    public static ComboStrDTO toDTO(ComboStr combo) {

        ComboStrDTO comboStrDTO = new ComboStrDTO();
        comboStrDTO.setCodigo(combo.getCodigo());
        comboStrDTO.setDescripcion(combo.getDescripcion());

        return comboStrDTO;
    }

    public static VehiculoIncidenciaDTO toVehiculoIncidenciaDTO(VehiculoIncidencia vehiculoIncidencia) {
        VehiculoIncidenciaDTO vehiculoIncidenciaDTO = new VehiculoIncidenciaDTO();
        vehiculoIncidenciaDTO.setId(vehiculoIncidencia.getId());
        vehiculoIncidenciaDTO.setIdIncidencia(vehiculoIncidencia.getIncidencia().getId());
        vehiculoIncidenciaDTO.setInicio(vehiculoIncidencia.getInicio());
        vehiculoIncidenciaDTO.setFin(vehiculoIncidencia.getFin());
        return vehiculoIncidenciaDTO;
    }

    public static ChoferIncidenciaDTO toChoferIncidenciaDTO(ChoferIncidencia choferIncidencia) {
        ChoferIncidenciaDTO choferIncidenciaDTO = new ChoferIncidenciaDTO();
        choferIncidenciaDTO.setId(choferIncidencia.getId());
        choferIncidenciaDTO.setIdIncidencia(choferIncidencia.getIncidencia().getId());
        choferIncidenciaDTO.setInicio(choferIncidencia.getInicio());
        choferIncidenciaDTO.setFin(choferIncidencia.getFin());
        return choferIncidenciaDTO;
    }

    public ViajeEspecialDTO toDTO(ViajeEspecial viajeEspecial, Map<String, Set<String>> listaConf,
            Map<String, Set<String>> listaConfVehi, Escala origen, Escala destino) {

        if (viajeEspecial == null) {
            return null;
        }

        ViajeEspecialDTO viajeEspecialDTO = new ViajeEspecialDTO();

        viajeEspecialDTO.setId(viajeEspecial.getId());
        viajeEspecialDTO.setAgenciaContratante(viajeEspecial.getAgenciaContratante());
        viajeEspecialDTO.setFechaHoraSalida(viajeEspecial.getFechaHoraSalida());

        String origenDet = viajeEspecial.getOrigen();
        origenDet += (origen != null) ? " - " + origen.getEscNombre() : "";
        viajeEspecialDTO.setOrigen(origenDet);

        String destinoStr = viajeEspecial.getDestino();
        destinoStr += (destino != null) ? " - " + destino.getEscNombre() : "";
        viajeEspecialDTO.setDestino(destinoStr);

        viajeEspecialDTO.setFechaHoraRegreso(viajeEspecial.getFechaHoraRegreso());
        viajeEspecialDTO.setObservaciones(viajeEspecial.getObservaciones());
        viajeEspecialDTO.setEmpCodigo(viajeEspecial.getEmpCodigo());

        Set<ChoferMinDTO> choferes = new HashSet<>();
        ChoferMinDTO unEstado;
        String claveChofer = "";

        final int HABILITADO = 0;
        final int DESHABILITADO = 1;

        for (ChoferViaje chViaje : viajeEspecial.getChoferViaje()) {
            if(chViaje != null && chViaje.getChofer() != null && chViaje.getChofer().getCho_chofer()== 0){

            claveChofer = chViaje.getChofer().getChoferPK().getCho_emp_codigo() + chViaje.getChofer().getChoferPK().getCho_codigo();

            int estado = (chViaje.getChofer().getCho_estado() == Chofer.DESHABILITADO
                    || (listaConf.get(claveChofer) != null
                    && listaConf.get(claveChofer).size() > 0)) ? DESHABILITADO : HABILITADO;

            unEstado = new ChoferMinDTO();
            unEstado.setChoferPK(chViaje.getChofer().getChoferPK());
            unEstado.setCho_nombre(chViaje.getChofer().getCho_nombre());
            unEstado.setCho_estado(estado);

            choferes.add(unEstado);
        }
        }
        viajeEspecialDTO.setChoferes(choferes);
        
        Set<ChoferMinDTO> auxiliares = new HashSet<>();
        ChoferMinDTO unEstado2;
        String claveAux = "";


        for (AuxiliarViaje auxViaje : viajeEspecial.getAuxiliarViaje()) {
            if( auxViaje!= null && auxViaje.getAuxiliar() != null  && auxViaje.getAuxiliar().getCho_chofer()== 1){
            claveAux = auxViaje.getAuxiliar().getChoferPK().getCho_emp_codigo() + auxViaje.getAuxiliar().getChoferPK().getCho_codigo();

            int estado2 = (auxViaje.getAuxiliar().getCho_estado() == Chofer.DESHABILITADO
                    || (listaConf.get(claveAux) != null
                    && listaConf.get(claveAux).size() > 0)) ? DESHABILITADO : HABILITADO;

            unEstado2 = new ChoferMinDTO();
            unEstado2.setChoferPK(auxViaje.getAuxiliar().getChoferPK());
            unEstado2.setCho_nombre(auxViaje.getAuxiliar().getCho_nombre());
            unEstado2.setCho_estado(estado2);

            auxiliares.add(unEstado2);
        }
        }
        
        viajeEspecialDTO.setAuxiliares(auxiliares);
        
        if (viajeEspecial.getVehiculo() != null) {

            String claveVehiculo = viajeEspecial.getVehiculo().getVehiculoPK().getVehEmpCodigo()
                    + viajeEspecial.getVehiculo().getVehiculoPK().getVehInterno();

            VehiculoMinDTO veh = new VehiculoMinDTO();
            veh.setVehiculoPK(viajeEspecial.getVehiculo().getVehiculoPK());

            int estado = (viajeEspecial.getVehiculo().getVehEstado() == Vehiculo.DESHABILITADO
                    || (listaConfVehi.get(claveVehiculo) != null
                    && listaConfVehi.get(claveVehiculo).size() > 0)) ? DESHABILITADO : HABILITADO;

            veh.setVehEstado(estado);
            viajeEspecialDTO.setVehiculo(veh);
        }

        return viajeEspecialDTO;
    } 

}
