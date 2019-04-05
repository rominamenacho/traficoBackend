package com.nuebus.repository;

import com.nuebus.model.Servicio;
import com.nuebus.model.ServicioPK;
import com.nuebus.vistas.combos.ComboVehiculo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


/**
 *
 * @author Valeria
 */
public interface ServicioRepository extends JpaRepository< Servicio, ServicioPK> {
    
	
	/*public Servicio findServiciosByIdWithHorariosWithEtapa(Long id);*/
	

    @Query(" Select s "
            + " from Servicio s where "
            + "  s.servicioPK.serEmpCodigo = ?1 "
            + "  and s.servicioPK.serLinCodigo = ?2 "
            + "  and trunc(s.servicioPK.serFechaHora) between ?3 and ?4 ")
    public ArrayList<Servicio> findServiciosByFecha( @Param("empresa") String empresa, 
    												 @Param("linea") String linea, 
    												 @Param("inicioServ") java.util.Date inicioServ, 
    												 @Param("finServ")java.util.Date finServ);

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
            + "        and eta_codigo = hlleg.hrs_eta_codigo  ) as escala_llegada, "      
            + "    hida.hrs_eta_codigo as eta_inicio, hlleg.hrs_eta_codigo as eta_fin "
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
    
    
    
    @Query( value = "Select ser_emp_codigo, ser_lin_codigo, ser_fecha_hora, ser_refuerzo, cho_uni_codigo, " 
            +  " inicio, fin, is_chofer, eta_inicio, eta_fin,    "
            +  "   ( case when is_chofer = 1 then ch.cho_nombre else '' end ) as cho_nombre, "
            +  "   ( case when is_chofer = 1 then ch.CHO_CHOFER else -1 end ) as CHO_CHOFER  "
            +  "      from CHOFERES_UNIDAD_SERVICIOS  left outer join choferes ch "
            +  "                    on (  ser_emp_codigo  =  ch.cho_emp_codigo "
            +  "                          and cho_uni_codigo = ch.cho_codigo ) "
            +  "         where ser_emp_codigo = :empresa " 
            +  "             and ser_lin_codigo = :linea " 
            +  "             and trunc( ser_fecha_hora ) between :inicioServ and :finServ "            
            +  "             and cho_uni_codigo is not null ", nativeQuery = true )
    public List< Object[] > findChoferesYVehiculosByServiciosAndLineaAndFechas( @Param("empresa") String empresa,
                            @Param("linea") String linea, @Param("inicioServ") java.util.Date inicioServ, 
                            @Param("finServ") java.util.Date finServ );    
      
    
    
  
    

}