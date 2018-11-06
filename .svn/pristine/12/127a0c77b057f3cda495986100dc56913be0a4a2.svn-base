/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuebus.service;

import com.nuebus.dto.CarnetDTO;
import com.nuebus.dto.ChoferDTO;
import com.nuebus.dto.ChoferIncidenciaDTO;
import com.nuebus.model.ChoferPK;
import com.nuebus.vistas.combos.ComboChoferes;
import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
    public Page<ChoferDTO> findChoferesByEmpresa( Pageable pageable, String empresa );
    
    public void salvarIncidenciasByChofer( String cho_emp_codigo, Long cho_codigo,
                                        Set<ChoferIncidenciaDTO>  choferIncidencias )throws Exception;   
    
    
    public List<ChoferIncidenciaDTO> getIncidenciasByChofer( String cho_emp_codigo, long cho_codigo );       
    
    public void  salvarCarnetsByChofer( String cho_emp_codigo, Long cho_codigo, Set<CarnetDTO> carnetsDTO );
    
    public  List<CarnetDTO> getCarnetsByChofer( String cho_emp_codigo,  long cho_codigo );
    
    public List<ComboChoferes> getChoferes( String empCodigo, int estado );
    
    public void findChoferesByViaje(long idViaje);
    
}
