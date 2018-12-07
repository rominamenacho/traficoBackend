package com.nuebus.service.impl;

import com.nuebus.builders.ServicioBuilder;
import com.nuebus.dto.ServicioDTO;
import com.nuebus.model.Servicio;
import com.nuebus.model.ServicioPK;
import com.nuebus.repository.ServicioRepository;
import com.nuebus.service.ServicioService;
import com.nuebus.vistas.combos.ComboVehiculo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    
    public Servicio findServicioById( ServicioPK  servPK){
        return servicioRepository.findOne( servPK );
    }

    @Override
    public ArrayList<Servicio> findServiciosByFecha(String empresa, String linea,
            Date inicioServ, Date finServ) {

        return servicioRepository.findServiciosByFecha(empresa, linea, inicioServ, finServ);
    }

    @Override
    public ArrayList<ComboVehiculo> findVehiculosLibresByFecha(String empresa, Date inicio, Date fin) {

        return servicioRepository.findVehiculosLibresByFecha(empresa, inicio, fin);

    }

    @Override
    public List<ServicioDTO> findServiciosConHorarisoByFecha(String empresa, String linea, Date inicioServ, Date finServ) {
        
        List<Object[]> serviciosObj = servicioRepository.findServiciosByLineaAndFechas(empresa, linea, inicioServ, finServ);
        List<Object[]> choferesVehiculosObj = servicioRepository.findChoferesYVehiculosByServiciosAndLineaAndFechas(empresa, linea, inicioServ, finServ);
        
        return  new ServicioBuilder( serviciosObj, choferesVehiculosObj ).build();                
        
    }
    
    

}
