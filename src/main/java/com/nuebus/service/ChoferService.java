/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuebus.service;

import com.nuebus.dto.CarnetDTO;
import com.nuebus.dto.ChoferDTO;
import com.nuebus.dto.ChoferIncidenciaDTO;
import com.nuebus.dto.ChoferOcupacionDTO;
import com.nuebus.model.Chofer;
import com.nuebus.model.ChoferPK;
import com.nuebus.vistas.combos.ComboChoferes;

import java.util.Date;
import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Valeria
 */
public interface ChoferService {    
    
    public Page<ChoferDTO> findChoferes(Pageable pageable);
    public ChoferDTO getChofer(ChoferPK id);    
    public void updateChofer( String cho_emp_codigo, Long cho_codigo, ChoferDTO choferDTO )throws Exception;    
    public void deleteChofer( String cho_emp_codigo, Long cho_codigo );
    public void saveChofer( ChoferDTO choferDTO )throws Exception;
    public Page<ChoferDTO> findPersonalByEmpresa( Pageable pageable, String empresa );
    
    public void salvarIncidenciasByChofer( String cho_emp_codigo, Long cho_codigo,
                                        Set<ChoferIncidenciaDTO>  choferIncidencias )throws Exception;   
    
    
    public List<ChoferIncidenciaDTO> getIncidenciasByChofer( String cho_emp_codigo, long cho_codigo );       
    
    public void  salvarCarnetsByChofer( String cho_emp_codigo, Long cho_codigo, Set<CarnetDTO> carnetsDTO );
    
    public  List<CarnetDTO> getCarnetsByChofer( String cho_emp_codigo,  long cho_codigo );
    
    public List<ComboChoferes> getPersonal( String empCodigo, int estado, int funcion );
    
    public void findPersonalByViaje(long idViaje);//?? empCodigo, long idViaje, java.util.Date inicio, java.util.Date fin, int estadoChofer, int funcion
    
    public  List<ChoferOcupacionDTO> findPersonalOcupacionByEmpresa( String empresa,  java.util.Date inicio, java.util.Date fin );
     
    public List<Chofer> getChoferesConCarnetsVencidos( String empresa, int estadoChofer, Date fechaControl );
    
    public  List< ChoferDTO > findChoferesFromHorariosServicios(String empresa,  String linea, java.util.Date inicio, java.util.Date fin );
    
    public Chofer getChoferById(ChoferPK id);  
    
}
