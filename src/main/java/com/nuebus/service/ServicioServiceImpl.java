package com.nuebus.service;

import com.nuebus.model.Servicio;
import com.nuebus.repository.ServicioRepository;
import com.nuebus.vistas.combos.ComboVehiculo;
import java.util.ArrayList;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Valeria
 */
@Service
@Transactional
public class ServicioServiceImpl implements ServicioService {

    final static Logger LOG = LoggerFactory.getLogger(ServicioServiceImpl.class);

    @Autowired
    private ServicioRepository servicioRepository;

    @Override
    public ArrayList<Servicio> findServiciosByFecha(String empresa, String linea,
            Date inicioServ, Date finServ) {

        return servicioRepository.findServiciosByFecha(empresa, linea, inicioServ, finServ);
    }

    @Override
    public ArrayList<ComboVehiculo> findVehiculosLibresByFecha(String empresa, Date inicio, Date fin) {

        return servicioRepository.findVehiculosLibresByFecha(empresa, inicio, fin);

    }

}
