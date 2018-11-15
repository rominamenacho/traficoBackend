package com.nuebus.service;

import com.nuebus.dto.ServicioDTO;
import com.nuebus.model.Servicio;
import com.nuebus.vistas.combos.ComboVehiculo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Valeria
 */
public interface ServicioService {

    public ArrayList<Servicio> findServiciosByFecha(String empresa, String linea,
            java.util.Date inicioServ, java.util.Date finServ);
    
    public List<ServicioDTO> findServiciosConHorarisoByFecha(String empresa, String linea,
            java.util.Date inicioServ, java.util.Date finServ);

    public ArrayList<ComboVehiculo> findVehiculosLibresByFecha(String empresa,
            java.util.Date inicio, java.util.Date fin);
}
