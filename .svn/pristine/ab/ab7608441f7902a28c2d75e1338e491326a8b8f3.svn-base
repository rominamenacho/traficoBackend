package com.nuebus.mapper;

import com.nuebus.dto.VehiculoMinDTO;
import com.nuebus.dto.ViajeEspecialDTO;
import com.nuebus.model.Vehiculo;
import com.nuebus.model.ViajeEspecial;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

/**
 *
 * @author Valeria
 */

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ViajeEspecialMapper {  
    
    public ViajeEspecialDTO toDTO(ViajeEspecial viajeEspecial);
    public ViajeEspecial toEntity(ViajeEspecialDTO viajeEspecial);
    public void mapToEntity(ViajeEspecialDTO viajeEspecialDTO, @MappingTarget ViajeEspecial viajeEspecial);    
    
    public VehiculoMinDTO toDTO( Vehiculo vehiculo);
    public Vehiculo toDTO( VehiculoMinDTO vehiculoMinDTO);
}
