package com.nuebus.repository;

import com.nuebus.model.Servicio;
import com.nuebus.model.ServicioPK;
import com.nuebus.vistas.combos.ComboVehiculo;
import java.util.ArrayList;
import java.util.List;
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
        
    
    @Query(value = " Select ser_emp_codigo, ser_lin_codigo, ser_fecha_hora, ser_refuerzo, ser_esr_codigo, "
            + " hida.hrs_fecha_hora as fecha_hora_salida, "
            + " ( Select eta_esc_codigo from etapas "
            + "  where eta_emp_codigo = ser_emp_codigo "
            + "        and eta_lin_codigo = ser_lin_codigo "
            + "        and eta_codigo =  hida.hrs_eta_codigo  ) as  escala_salida, "
            + "    hlleg.hrs_fecha_hora as fecha_hora_llegada, "
            + " ( Select eta_esc_codigo from etapas  "
            + "        where eta_emp_codigo = ser_emp_codigo  "
            + "        and eta_lin_codigo = ser_lin_codigo  "
            + "        and eta_codigo = hlleg.hrs_eta_codigo  ) as escala_llegada "      
            + "    from servicios, horarios_servicios hida, horarios_servicios hlleg "
            + "   where ser_emp_codigo = ?1 "
            + "       and ser_lin_codigo =  ?2 "
            + "       and hida.hrs_ser_emp_codigo = ser_emp_codigo "
            + "       and hida.hrs_ser_lin_codigo = ser_lin_codigo "
            + "       and hida.hrs_ser_fecha_hora = ser_fecha_hora "
            + "       and hida.hrs_ser_refuerzo = ser_refuerzo  "
            + "       and hida.hrs_ace_codigo = 2 "
            + "       and hlleg.hrs_ser_emp_codigo = ser_emp_codigo "
            + "       and hlleg.hrs_ser_lin_codigo = ser_lin_codigo "
            + "       and hlleg.hrs_ser_fecha_hora = ser_fecha_hora "
            + "       and hlleg.hrs_ser_refuerzo = ser_refuerzo  "
            + "       and hlleg.hrs_ace_codigo = 4   "
            + "       and trunc( ser_fecha_hora ) between   ?3 and ?4 ", nativeQuery = true)  
    public List< Object[] > findServiciosByLineaAndFechas(String empresa, String linea,
            java.util.Date inicioServ, java.util.Date finServ);

}