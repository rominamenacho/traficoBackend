package com.nuebus.repository;

import com.nuebus.model.Vehiculo;
import com.nuebus.model.VehiculoPK;
import com.nuebus.vistas.combos.ComboVehiculo;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Valeria
 */
public interface VehiculoRepository extends JpaRepository< Vehiculo, VehiculoPK>, VehiculoRepositoryCustom {

    @Query("select v from Vehiculo v where v.vehiculoPK.vehEmpCodigo = ?1 ")
    public Page<Vehiculo> findVehiculosByEmpresa(String vehEmpCodigo , Pageable pageable);    

    @Query( " Select CASE WHEN COUNT( v ) > 0 THEN true ELSE false END from Vehiculo v "
            + " where v.vehiculoPK.vehEmpCodigo = ?1 "
            + "   and v.vehiculoPK.vehInterno = ?2 " )           
    public boolean existeInternoByEmpresa( String vehEmpCodigo, int vehInterno);

       
    
    //Los habilitados
    @Query( " Select new com.nuebus.vistas.combos.ComboVehiculo( v.vehiculoPK, v.vehiculoPK.vehInterno ) from Vehiculo v "
            + " where v.vehiculoPK.vehEmpCodigo = ?1 "
            + "   and v.vehEstado = 0 " )           
    public List<ComboVehiculo> findVehiculosByEmpresa( String vehEmpCodigo );

    
    @Query( value = " Select ve.veh_emp_codigo, ve.veh_interno from vehiculos ve "
            + "  where ve.veh_emp_codigo = ?1 "
            + "   and ve.veh_estado = ?5 "
            + "   and ve.veh_interno not in ( "
            + "     Select vinc.id_veh_interno from vehiculo_incidencia vinc "
            + "          where  vinc.id_veh_emp_codigo = ve.veh_emp_codigo "
            + "            and vinc.id_veh_interno = ve.veh_interno "
            + "            and ?3 <=  vinc.fin "
            + "            and ?4 >=  vinc.inicio )  "
            + "   and ve.veh_interno not in ( "
            + "       Select via.veh_interno from viajes_especiales via "
            + "          where via.emp_codigo = ve.veh_emp_codigo "
            + "               and via.veh_emp_codigo = ve.veh_emp_codigo "
            + "               and via.veh_interno = ve.veh_interno "
            + "               and via.id != ?2 "
            + "               and  ?3 < = via.fecha_hora_regreso "
           + "               and  ?4 >=  via.fecha_hora_salida  ) " , nativeQuery = true  )    
    public List<Object[]> findVehiculosByViajeDisp( String empCodigo, long idViaje, java.util.Date inicio, java.util.Date fin, 
            int estadoVehiculo );

    
    @Query( value = "Select  viajes.veh_emp_codigo, viajes.veh_interno,"
            + " ( 'Viaje Esp ' || to_char( viajes.fecha_hora_salida,'dd/mm/yyyy hh24:mi') ||' - '|| "
            + " to_char( viajes.fecha_hora_regreso,'dd/mm/yyyy hh24:mi') ) as detalle  "
            + "   from viajes_especiales viajes  "
            + "     where viajes.emp_codigo = ?1 "
            + "         and viajes.veh_emp_codigo = ?1 "
            + "         and viajes.id != ?2 "
            + "         and  ?3 < = viajes.fecha_hora_regreso "
            + "         and  ?4 >= viajes.fecha_hora_salida "
            + "         and viajes.veh_interno in ( "
            + "               Select viajesInt.veh_interno from viajes_especiales viajesInt "
            + "                    where viajesInt.emp_codigo = viajes.emp_codigo "
            + "                        and viajesInt.veh_emp_codigo = viajes.veh_emp_codigo "
            + "                        and viajesInt.veh_interno = viajes.veh_interno "
            + "                        and viajesInt.id = ?2   ) "
            + " UNION "
            + " Select   vinc.id_veh_emp_codigo, vinc.id_veh_interno, "
            + " ( 'Incidencia: '  ||nvl ( (Select in_descripcion from incidencia where id = vinc.id_incidencia ),'Indefinido') "
            + "   || ' - ' ||to_char(vinc.inicio,'dd/mm/yyyy hh24:mi') ||' - '|| to_char(vinc.fin,'dd/mm/yyyy hh24:mi') )  as detalle "
            + "     from vehiculo_incidencia vinc  "
            + "         where vinc.id_veh_emp_codigo = ?1 "
            + "            and  ?3 <= vinc.fin "
            + "            and  ?4 >= vinc.inicio "
            + "            and  vinc.id_veh_interno  in ( "
            + "                  Select via.veh_interno from viajes_especiales via "
            + "                         where via.emp_codigo = vinc.id_veh_emp_codigo "
            + "                                 and via.veh_emp_codigo = vinc.id_veh_emp_codigo "
            + "                                 and via.veh_interno = vinc.id_veh_interno "
            + "                                 and via.id = ?2 ) "  , nativeQuery = true )
    public List<Object[]> findVehiculosConflictoByViaje( String empCodigo, long idViaje, java.util.Date inicio, java.util.Date fin );

    
    
    
}
