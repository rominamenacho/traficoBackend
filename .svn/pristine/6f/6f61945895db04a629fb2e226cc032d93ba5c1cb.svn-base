package com.nuebus.repository;

import com.nuebus.model.Servicio;
import com.nuebus.model.ServicioPK;
import com.nuebus.vistas.combos.ComboVehiculo;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Valeria
 */
public interface ServicioRepository extends JpaRepository< Servicio, ServicioPK> {

    @Query(" Select s "
            + " from Servicio s where "
            + "  s.servicioPK.serEmpCodigo = ?1 "
            + "  and s.servicioPK.serLinCodigo = ?2 "
            + "  and trunc(s.servicioPK.serFechaHora) between ?3 and ?4 ")
    public ArrayList<Servicio> findServiciosByFecha(String empresa, String linea,
            java.util.Date inicioServ, java.util.Date finServ);

   @Query(value = "Select ComboVehiculo "
            + " ( v.vehiculoPK.vehEmpCodigo, v.vehiculoPK.vehInterno ) from Vehiculo v,  "
            + " ServiciosVehiculos x where "
            + " v.vehiculoPK.vehEmpCodigo=  x.empresa "
            + " and v.vehiculoPK.vehInterno= x.interno "
            + " and x.empresa =?1 "
            + "and trunc(x.fecha_hora) between ?2 and ?3  ", nativeQuery = true)
    public ArrayList<ComboVehiculo> findVehiculosLibresByFecha(String empresa,
            java.util.Date inicio, java.util.Date fin);

}