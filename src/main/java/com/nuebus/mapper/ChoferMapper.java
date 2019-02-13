package com.nuebus.mapper;

import java.util.Set;
import com.nuebus.dto.CarnetDTO;
import com.nuebus.dto.ChoferConCarnetsDTO;
import com.nuebus.dto.ChoferDTO;
import com.nuebus.dto.ChoferIncidenciaDTO;
import com.nuebus.dto.ChoferPKDTO;
import com.nuebus.dto.IncidenciaDTO;
import com.nuebus.model.Carnet;
import com.nuebus.model.Chofer;
import com.nuebus.model.ChoferIncidencia;
import com.nuebus.model.ChoferPK;
import com.nuebus.model.Incidencia;
import java.util.HashSet;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

/**
 *
 * @author Valeria
 */

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ChoferMapper {
    
    public ChoferDTO toDTO(Chofer chofer);

    public Chofer toEntity(ChoferDTO choferDTO);

    public void mapToEntity(ChoferDTO choferDTO, @MappingTarget Chofer chofer);
    
    public Set<CarnetDTO> carnetsToCarnetsDTO( Set<Carnet> carnets);
    
    public CarnetDTO carnetToCarnetDTO( Carnet carnet);
    
    public Set<Carnet> carnetsDTOToCarnets( Set<CarnetDTO> carnetsDTO);
    
    public Carnet carnetDTOTOCarnet( CarnetDTO carnetDTO );
    
    public ChoferPK ChoferPKDTOTOChoferPK( ChoferPKDTO choferPKDTO );
    
    public ChoferPKDTO ChoferPKTOChoferPKDTO( ChoferPK choferPK );
       
    public ChoferConCarnetsDTO choferToChoferConCarnet(  Chofer chofer );
    
    public List<ChoferConCarnetsDTO> choferToChoferConCarnet(  List<Chofer> choferes );
    
    public Set<IncidenciaDTO> incidenciasToIncidenciasDTO(Set<Incidencia> incidencias);
    public IncidenciaDTO incidenciaToIncidenciaDTO( Incidencia incidencia );
    public Incidencia incidenciaDTOToIncidencia(IncidenciaDTO incidenciaDTO);
    public Set<Incidencia> incidenciasDTOToIncidencias( Set<IncidenciaDTO> incidenciasDTO );
          
    public Set<ChoferIncidenciaDTO> choferIncidenciasToChoferIncidenciasDTO( Set<ChoferIncidencia> choferIncidencias );
    public ChoferIncidenciaDTO  choferIncidenciaToChoferIncidenciaDTO( ChoferIncidencia choferIncidencia );
    public ChoferIncidencia  choferIncidenciaDTOToChoferIncidencia( ChoferIncidenciaDTO choferIncidenciaDTO );
    public Set<ChoferIncidencia> choferIncidenciasDTOToChoferIncidencias( Set<ChoferIncidenciaDTO> choferIncidenciasDTO );
    
    
}
