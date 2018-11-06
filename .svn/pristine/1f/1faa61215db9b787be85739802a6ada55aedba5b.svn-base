/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuebus.service;

import com.nuebus.dto.VueltaDTO;
import com.nuebus.model.ChoferPK;
import com.nuebus.model.ServicioPK;
import com.nuebus.model.VehiculoPK;
import com.nuebus.vistas.combos.ChoferesPKDet;
import com.nuebus.vistas.combos.ComboChoferes;
import com.nuebus.vistas.combos.ComboVehiculo;
import com.nuebus.vistas.combos.VehiculoPKDet;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Usuario
 */
public interface VueltaService {

    public void saveVuelta(VueltaDTO vueltaDTO) throws Exception;

    public List<VueltaDTO> findViajesByDiagramacion(String empresa,
            int diagramacion_id);

    public Page<VueltaDTO> findVueltasByEmpresaAndFecha(Pageable pageable, String empresa, java.util.Date inicio, java.util.Date fin);

    public void setServicios(long vuelta, List<ServicioPK> serviciosPK) throws Exception;

    public void setVehiculos(long vuelta, List<VehiculoPK> vehiculoPK) throws Exception;

    public void setChoferes(long vuelta, List<ChoferPK> choferesPK) throws Exception;

    public void deleteVuelta(long idVuelta);

    /*public List<ChoferPK> finChoferesByViaje( long idViaje);*/
    public List<ChoferesPKDet> finChoferesByVuelta(long idViaje);

    public List<ComboChoferes> findChoferesLibreByVuelta(long idViaje);

    public List<ComboVehiculo> findChoferesByEmpresa(long idViaje);

    public List<ComboVehiculo> findVehiculosLibresByVuelta(long idViaje);

    public VehiculoPKDet findVehiculoByVuelta(long idViaje);

    public void updateChofer(long idVuelta, VueltaDTO vueltaDTO) throws Exception;
}
