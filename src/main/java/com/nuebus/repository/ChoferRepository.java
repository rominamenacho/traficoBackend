
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nuebus.repository;

import com.nuebus.model.Carnet;
import com.nuebus.model.Chofer;
import com.nuebus.model.ChoferPK;
import com.nuebus.vistas.combos.ComboChoferes;

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
public interface ChoferRepository extends JpaRepository<Chofer, ChoferPK> {
    //aca habria q agregar al where q cho_chofer = 0 y hacer un met igual q cho_chofer=1 para los auxiliares
    @Query("select c from Chofer c where choferPK.empCodigo = :empCodigo  ")
    public Page<Chofer> findChoferesByEmpresa( @Param("empCodigo") String empCodigo , Pageable pageable);
    
    @Query("select c from Chofer c where choferPK.empCodigo = :empCodigo  ")
    public Page<Chofer> findPersonalByEmpresa(String empCodigo , Pageable pageable);
    
    @Query("select max( choferPK.codigo ) from Chofer c where choferPK.empCodigo = :empCodigo  and c.tipoChofer= :tipoChofer")
    public Integer maxCodigoPersonalByEmpresa( @Param("empCodigo") String empCodigo, @Param("tipoChofer") int tipoChofer );

    //Ver cdo comprobar las incidencias y los servicios asignados        
    @Query("select new com.nuebus.vistas.combos.ComboChoferes( c.choferPK, c.nombre  ) from Chofer c "
           + " where c.choferPK.empCodigo = :empCodigo "
           + "   and c.estado = :estado " 
            + "and c.tipoChofer= :tipoChofer ")
    public List<ComboChoferes> finPersonalByEstado( @Param("empCodigo") String empCodigo, 
    												@Param("estado") int estado, @Param("tipoChofer") int tipoChofer  );    
  
     
    public interface ChoferLibre {                
        Long getCodigo();        
        String getEmpresa();        
        String getNombre();
    }  
    
    //Agregar cho_codigo en los 2 in 
    
    @Query( value = " Select  ch.cho_codigo as codigo, ch.cho_emp_codigo as empresa , ch.cho_nombre as nombre "
            + " from choferes ch "
            + "  where ch.cho_emp_codigo = ?1 "
            + "  and ch.CHO_CHOFER=?6 " //q sea chofer
            + "   and ch.cho_estado = ?5 "     
            + "   and ch.cho_codigo not in ( " 
            + "         Select chv.id_cho_codigo from CHOFER_VIAJE_ESP chv "
            + "             where chv.id_cho_emp_codigo = ch.cho_emp_codigo "
            + "                 and chv.id_cho_codigo = ch.cho_codigo "
            + "                 and chv.id_viaje != ?2 "
            + "                 and ?3 <= chv.fin  "
            + "                 and ?4 >= chv.inicio   )  "
            + "  and  ch.cho_codigo not in ( "
            + "         Select  chi.ID_CHO_CODIGO   from CHOFER_INCIDENCIA chi  "
            + "            Where chi.id_cho_emp_codigo = ch.cho_emp_codigo "
            + "                 and chi.ID_CHO_CODIGO = ch.cho_codigo "
            + "                 and ?3 <=  chi.fin "
            + "                 and ?4 >=  chi.inicio  ) " , nativeQuery = true )
    public List<ChoferLibre> findPersonalByViaje( String empCodigo, long idViaje, java.util.Date inicio, java.util.Date fin, int estadoChofer, int funcion );
    
    
    public interface DetConflictoChoferes { 
        String getIdChoCodigo();        
        String  getIdChoEmpCodigo();                 
        String getDetalle();           
        //Cuidado cuando los nombres tienen repetida alguna partecita  no la resuelven bien ej: id_cho_codigo y  id_cho_emp_codigo    
    } 
   /* @Query( value = " Select chesp.id_cho_codigo as idChoCodigo, chesp.id_cho_emp_codigo as idChoEmpCodigo,  "
                  + "    ( 'Viaje Esp ' || to_char( chesp.inicio,'dd/mm/yyyy hh24:mi') ||' - '|| to_char( chesp.fin,'dd/mm/yyyy hh24:mi') ) as detalle "
                  + "        from CHOFER_VIAJE_ESP chesp "
                  + "           where chesp.id_cho_emp_codigo = ?1 "
                  + "               and chesp.id_viaje != ?2 "
                  + "               and  ?3 <= chesp.fin  "
                  + "               and  ?4 >= chesp.inicio  "
                  + "               and  chesp.id_cho_codigo  in  (  Select chv.id_cho_codigo from CHOFER_VIAJE_ESP chv  "
                  + "                          where chv.id_cho_emp_codigo = chesp.id_cho_emp_codigo "                          
                  + "                               and chv.id_cho_codigo = chesp.id_cho_codigo "
                  + "                                 and chv.id_viaje = ?2 ) "    
                  + "    union       "
                  + "  Select  chi.id_cho_codigo as idChoCodigo, chi.id_cho_emp_codigo as idChoEmpCodigo,  "
                  + "    ( 'Incidencia: '  ||nvl ( (Select in_descripcion from incidencia where id = chi.id_incidencia ),'Indefinido') "
                  + "        || ' - ' ||to_char(chi.inicio,'dd/mm/yyyy hh24:mi') ||' - '|| to_char(chi.fin,'dd/mm/yyyy hh24:mi') ) "
                  + "        as detalle  "
                  + "       from CHOFER_INCIDENCIA  chi "
                  + "            Where chi.id_cho_emp_codigo = ?1 "          
                  + "               and  ?3 <=  chi.fin "
                  + "               and  ?4  >= chi.inicio "
                  + "               and  chi.id_cho_codigo in  (  Select chv.id_cho_codigo from CHOFER_VIAJE_ESP chv "
                  + "                             where chv.id_cho_emp_codigo = chi.id_cho_emp_codigo "                           
                  + "                                 and chv.id_cho_codigo = chi.id_cho_codigo "
                  + "                                 and chv.id_viaje = ?2 ) " , nativeQuery = true )    
    public List<DetConflictoChoferes> findChoferesConflictoViaje( String empCodigo, long idViaje, java.util.Date inicio, java.util.Date fin );*/
    
     
    @Query( value = " Select chesp.id_cho_codigo as idChoCodigo, chesp.id_cho_emp_codigo as idChoEmpCodigo,  "
                  + "    ( 'Viaje Esp ' || to_char( chesp.inicio,'dd/mm/yyyy hh24:mi') ||' - '|| to_char( chesp.fin,'dd/mm/yyyy hh24:mi') ) as detalle "
                  + "        from CHOFER_VIAJE_ESP chesp "
                  + "           where chesp.id_cho_emp_codigo = ?1 "
                  + "               and chesp.id_viaje != ?2 "
                  + "               and  ?3 <= chesp.fin  "
                  + "               and  ?4 >= chesp.inicio  "
                  + "               and  chesp.id_cho_codigo  in  (  Select chv.id_cho_codigo from CHOFER_VIAJE_ESP chv  "
                  + "                          where chv.id_cho_emp_codigo = chesp.id_cho_emp_codigo "                          
                  + "                               and chv.id_cho_codigo = chesp.id_cho_codigo "
                  + "                                 and chv.id_viaje = ?2 ) "    
                  + "    union       "
                  + "  Select  chi.id_cho_codigo as idChoCodigo, chi.id_cho_emp_codigo as idChoEmpCodigo,  "
                  + "    ( 'Incidencia: '  ||nvl ( (Select in_descripcion from incidencia where id = chi.id_incidencia ),'Indefinido') "
                  + "        || ' - ' ||to_char(chi.inicio,'dd/mm/yyyy hh24:mi') ||' - '|| to_char(chi.fin,'dd/mm/yyyy hh24:mi') ) "
                  + "        as detalle  "
                  + "       from CHOFER_INCIDENCIA  chi "
                  + "            Where chi.id_cho_emp_codigo = ?1 "          
                  + "               and  ?3 <=  chi.fin "
                  + "               and  ?4  >= chi.inicio "
                  + "               and  chi.id_cho_codigo in  (  Select chv.id_cho_codigo from CHOFER_VIAJE_ESP chv "
                  + "                             where chv.id_cho_emp_codigo = chi.id_cho_emp_codigo "                           
                  + "                                 and chv.id_cho_codigo = chi.id_cho_codigo "
                  + "                                 and chv.id_viaje = ?2 ) " , nativeQuery = true )    
    public List<Object[]> findChoferesConflictoViaje( String empCodigo, long idViaje, java.util.Date inicio, java.util.Date fin );
    
    
    
      
    @Query( value = "  Select   cho.cho_emp_codigo, cho.cho_codigo, cho.cho_nombre, cho.cho_chofer,  ocup.tipo, ocup.id, ocup.emp_codigo, "
                  + " ocup.ser_emp_codigo, ocup.ser_lin_codigo, ocup.ser_fecha_hora, ocup.ser_refuerzo, ocup.inicio, ocup.fin, "
                  + "  cho.cho_estado, cho_id_aux "
                  + " from choferes cho left join "
                  + "        ( Select * from ( "
                  + "      Select 1 as tipo, 0 as id, ser_emp_codigo as emp_codigo, ser_emp_codigo, ser_lin_codigo, ser_fecha_hora, "
                  + "                ser_refuerzo, CHO_UNI_CODIGO as cho_codigo,  inicio, fin  "
                  + "                 from CHOFERES_UNIDAD_SERVICIOS"
                  + "                   where IS_CHOFER = 1 "     
                  + "      union "              
                  + "      Select 2 as tipo, id_incidencia as idObj, id_cho_emp_codigo as emp_codigo,  '' as ser_emp_codigo, "
                  + "           '' as ser_lin_codigo, sysdate as ser_fecha_hora, 0 as ser_refuerzo, id_cho_codigo as cho_codigo,  inicio, fin  "
                  + "                 from chofer_incidencia "
                  + "      union  "         
                  + "      Select 3 as tipo, id_viaje as idObj, id_cho_emp_codigo as emp_codigo, '' as ser_emp_codigo, "
                  + "             '' as ser_lin_codigo, sysdate as ser_fecha_hora, 0 as ser_refuerzo, id_cho_codigo as cho_codigo,  inicio, fin "
                  + "               from CHOFER_VIAJE_ESP ) "
                  + "          where emp_codigo = ?1  "      
                  + "                          and ?2 <= fin "
                  + "                          and ?3 >= inicio) ocup "
                  + "  on ( cho.cho_emp_codigo = ocup.emp_codigo "
                  + "  and cho.cho_codigo = ocup.cho_codigo  ) "    
                  + "       where cho.cho_emp_codigo = ?1 " , nativeQuery = true)    
    public List<Object[]> ocupacionChoferes( String empCodigo, java.util.Date inicio, java.util.Date fin );
    
    
  
    
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    
    @Query(  value = "Select distinct ch.cho_emp_codigo , ch.cho_codigo,  ch.cho_nombre, ch.cho_chofer, cho_estado, cho_id_aux "                
    		 + " from horarios_servicios h, choferes ch " 
    	     + "     where h.hrs_ser_emp_codigo  =  ch.cho_emp_codigo " 
    	     + "       and  (  h.HRS_CHOFER1 = ch.cho_codigo " 
    	     + "               or  h.HRS_CHOFER2 = ch.cho_codigo "
    	     + "               or  h.HRS_AUXILIAR1 = ch.cho_codigo "
    	     + "               or  h.HRS_AUXILIAR2 = ch.cho_codigo ) "
             + " 		and h.hrs_ser_emp_codigo = :empresa "
             + " 		and h.hrs_ser_lin_codigo = :linea "
             + " 		and trunc( h.hrs_ser_fecha_hora) between  :inicioServ "
             + " 		and :finServ ", nativeQuery = true )     
    public List<Object[]> findChoferesFromHorariosServicios(  @Param("empresa") String empresa,
            @Param("linea") String linea, @Param("inicioServ") java.util.Date inicioServ, 
            @Param("finServ") java.util.Date finServ); 
    
    
    @Query("select c from Chofer c where "
    		+ "		choferPK.empCodigo = :empCodigo  "
    		+ "		and upper( c.nombre)  LIKE  CONCAT('%',:busqueda,'%') ")
    public Page<Chofer> findPersonalByBusquedaAndEmpresa( @Param(value = "busqueda") String busqueda, 
    													  @Param(value="empCodigo")String empCodigo , 
    													  Pageable pageable);   
    
    /// Erroneo
    /*@Query( " Select DISTINCT ch from Chofer ch join fetch ch.carnets car "
    		+ " where ch.choferPK.cho_emp_codigo = :empresa "	
    	 	+ " and ch.cho_estado = :estadoChofer "    		
    		+ " and car.fechaVenc <= :fechaControl ")
    public List<Chofer> getChoferesConCarnetsVencidos( @Param("empresa") String empresa,
    											       @Param("estadoChofer") int estadoChofer,    											      
    											       @Param("fechaControl") Date fechaControl ); */
    
    @Query(" Select carnet from Carnet carnet "
    		+ "	where carnet.chofer.choferPK.empCodigo = :empresa "
    		+ "     and  carnet.tipo = :tipo "
    		+ "  	and  carnet.chofer.estado = :estadoChofer "    		
    		+ "     and  carnet.fechaVenc <= :fechaControl ")
    public List<Carnet>  getCarnetsVencidosByTipo( @Param("empresa") String empresa,		       								 
		       									  @Param("tipo") Integer tipo,
		       									  @Param("estadoChofer") int estadoChofer,
		       									  @Param("fechaControl") Date fechaControl );
    
    
    
    @Query( " Select DISTINCT ch from Chofer ch join fetch ch.carnets car "
		+ " where ch.choferPK.empCodigo = :empresa "	
	 	+ " and ch.estado = :estadoChofer "    		
		+ " and car.fechaVenc <= :fechaControl ")
	public List<Chofer> getChoferesConCarnetsVencidos( @Param("empresa") String empresa,
											       @Param("estadoChofer") int estadoChofer,											       
											       @Param("fechaControl") Date fechaControl ); 
	    
    
    
    @Query( " Select DISTINCT ch from Chofer ch join fetch ch.carnets car "
    		+ " where ch.choferPK.empCodigo = :empresa "	
    	 	+ " and ch.estado = :estadoChofer "
    		+ " and car.tipo = :tipo "
    		+ " and car.fechaVenc <= :fechaControl ")
    public List<Chofer> getChoferesConCarnetsVencidosByTipo( @Param("empresa") String empresa,
    											       @Param("estadoChofer") int estadoChofer,
    											       @Param("tipo") Integer tipo, 
    											       @Param("fechaControl") Date fechaControl ); 
    
    
}
