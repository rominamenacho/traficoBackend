
package com.nuebus.repository;

import com.nuebus.model.VueltaDiag;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Valeria
 */
public interface VueltaDiagRepository extends JpaRepository< VueltaDiag, Long> {
    
    @Query( " Select v from VueltaDiag v "
        +   "   where v.servicio.servicioPK.serEmpCodigo = :empresa "
        +   "       and  v.servicio.servicioPK.serLinCodigo = :lineaIda "   
        +   "       and trunc( v.servicio.servicioPK.serFechaHora ) between :inicio and :fin  "   )
    public List<VueltaDiag> findByFechaServiciosIda( @Param("empresa") String empresa, @Param("lineaIda") String lineaIda,
            @Param("inicio")java.util.Date inicio, @Param("fin") java.util.Date fin );
    
    
    @Query(value =  "select LINEA_IDA,to_char( SERVICIO_IDA, 'DD/MM/YYYY HH24:MI') as servicio_ida, REFUERZO_IDA, PELICULA_IDA, video_ida, CHOFERES_IDA, " 
        +" AUXILIARES_IDA, LINEA_VUELTA, to_char( SERVICIO_VUELTA, 'DD/MM/YYYY HH24:MI') as SERVICIO_VUELTA, " 
        +" REFUERZO_VUELTA, PELICULA_VUELTA, VIDEO_VUELTA, CHOFERES_VUELTA, AUXILIARES_VUELTA " 
        +" from( " 
        +" select  "
        +" vd.ser_lin_codigo_ida AS LINEA_IDA, vd.ser_fecha_hora_ida AS SERVICIO_IDA, " 
        +" vd.ser_refuerzo_ida AS REFUERZO_IDA, vd.peli_ida AS PELICULA_IDA, " 
        +" vd.video_ida AS video_ida, HS1.HRS_CHOFER1 || ',' ||  HS1.HRS_CHOFER2 AS CHOFERES_IDA, " 
        +" HS1.HRS_AUXILIAR1 || ',' || HS1.HRS_AUXILIAR2 AS AUXILIARES_IDA, " 
        +" HS2.HRS_CHOFER1 || ',' ||  HS2.HRS_CHOFER2 AS CHOFERES_VUELTA, " 
        +" HS2.HRS_AUXILIAR1 || ',' || HS2.HRS_AUXILIAR2 AS AUXILIARES_VUELTA, " 
        +" vd.SER_LIN_CODIGO_VTA AS LINEA_VUELTA,  " 
        +" vd.SER_FECHA_HORA_VTA AS SERVICIO_VUELTA, vd.SER_REFUERZO_VTA AS REFUERZO_VUELTA, " 
        +" vd.PELI_VTA AS PELICULA_VUELTA, vd.VIDEO_VTA AS VIDEO_VUELTA  " 
        +" from vuelta_diag vd,  HORARIOS_SERVICIOS HS1,  HORARIOS_SERVICIOS HS2 " 
        +" WHERE " 
        +" HS1.HRS_ser_emp_codigo    = vd.SER_EMP_CODIGO_ida " 
        +" and HS1.HRS_ser_lin_codigo = vd.SER_LIN_CODIGO_ida " 
        +" and HS1.HRS_ser_fecha_hora = vd.sER_FECHA_HORA_ida "
        +" and HS1.HRS_ser_refuerzo   = vd.SER_REFUERZO_ida " 
        +" and HS1.HRS_ACE_CODIGO     = 2 " 
        +" AND " 
        +"    HS2.HRS_ser_emp_codigo= vd.SER_EMP_CODIGO_VTA " 
        +" and HS2.HRS_ser_lin_codigo= vd.SER_LIN_CODIGO_VTA " 
        +" and HS2.HRS_ser_fecha_hora= vd.SER_FECHA_HORA_VTA "
        +" and HS2.HRS_ser_refuerzo  = vd.SER_REFUERZO_VTA  " 
        +" and HS2.HRS_ACE_CODIGO    = 4 " 
        +" AND vd.empresa            =  :empresa " 
        +" AND TRUNC(vd.SER_FECHA_HORA_ida) >=  :inicio "
        +" AND vd.SER_FECHA_HORA_ida <= :fin ) ", nativeQuery = true  )
    public List<Object[]> findAll(  @Param("empresa") String idEmpresa, 
             @Param("inicio") @DateTimeFormat(pattern = "yyyy-MM-dd") Date inicio,
             @Param("fin") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fin  );
    
    
}
