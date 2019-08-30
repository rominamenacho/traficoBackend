package com.nuebus.repository;

import com.nuebus.model.Usuario;
import com.nuebus.model.Vehiculo;
import com.nuebus.model.VehiculoPK;
import com.nuebus.vistas.combos.ComboVehiculo;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Valeria
 */
public interface VehiculoRepository extends JpaRepository< Vehiculo, VehiculoPK> {

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

    
    
    
    @Query( value = "Select  veh.veh_emp_codigo, veh.veh_interno, veh.veh_patente, veh.veh_estado,  "
            + " ocup.tipo, ocup.id, ocup.emp_codigo, ocup.ser_emp_codigo, ocup.ser_lin_codigo, "
            + " ocup.ser_fecha_hora, ocup.ser_refuerzo, ocup.inicio, ocup.fin "
            + "        from VEHICULOS veh left join " 
            + " ( Select * from (    " 
            + "        Select 1 as tipo, 0 as id, ser_emp_codigo as emp_codigo, ser_emp_codigo, "
            + "               ser_lin_codigo, ser_fecha_hora, ser_refuerzo, CHO_UNI_CODIGO as veh_codigo, "
            + "               inicio, fin  " 
            + "         from CHOFERES_UNIDAD_SERVICIOS " 
            + "            where  IS_CHOFER = 0 "
            + "                  and cho_uni_codigo is not null " 
            + "        union      "
            + "        Select 2 as tipo, id_incidencia as idObj, id_veh_emp_codigo as emp_codigo, "
            + "               '' as ser_emp_codigo, '' as ser_lin_codigo, null as ser_fecha_hora, "
            + "               0 as ser_refuerzo, id_veh_interno as veh_codigo, inicio, fin "
            + "        from vehiculo_incidencia  " 
            + "        union "
            + "        Select  3 as tipo, id as idObj, emp_codigo, '' as ser_emp_codigo, "
            + "                '' as ser_lin_codigo, null as ser_fecha_hora, 0 as ser_refuerzo, "
            + "                veh_interno as veh_codigo, fecha_hora_salida as inicio, " 
            + "                fecha_hora_regreso as fin   "
            + "        from VIAJES_ESPECIALES      "
            + "                  where veh_interno is not null "
            + "        ) "
            + "   where emp_codigo = :empCodigo  "
            + "          and :inicio <= fin "
            + "          and :fin >= inicio ) ocup "
            + "  on ( veh.VEH_EMP_CODIGO = ocup.emp_codigo "
            + "       and veh.VEH_INTERNO = ocup.veh_codigo  )"
            + " where    veh.VEH_EMP_CODIGO = :empCodigo   ", nativeQuery = true)    
    public List<Object[]> ocupacionVehiculos( @Param("empCodigo") String empCodigo, 
            @Param("inicio") java.util.Date inicio, @Param("fin") java.util.Date fin );
    
    
    /////////////////////////// De acuerdo a los criterios de Vencimiento ////////////////////////////
    
    @Query( "  Select v from Vehiculo v "
            + "  where v.vehiculoPK.vehEmpCodigo = ?1 "
            + "    and v.vehEstado =?2 "
            + "    and vehVerificacionTecnicaVto <= ?3 ")
    public List<Vehiculo> getVencimientosByVerificacionTecnica( @Param("empresa") String empresa, 
                                           @Param("estado") Integer estado,
                                           @Param("fecha") Date fechaControl );   
    
     	
	@Query( " Select v from Vehiculo v where"
			+ "  v.vehiculoPK.vehEmpCodigo = :empresa "
			+ "	 and CAST( v.vehiculoPK.vehInterno as string ) LIKE  CONCAT('%',:busqueda,'%')  ")
    Page<Vehiculo> findAllByEmpresaAndBusqueda( @Param(value="empresa") String empresa,
    								  @Param(value="busqueda") String busqueda,Pageable pageable);
    
    
}
