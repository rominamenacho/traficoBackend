/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuebus.mapper;

import com.nuebus.dto.VehiculoDTO;
import com.nuebus.model.Vehiculo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

/**
 *
 * @author Valeria
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VehiculoMapper {
    
    public VehiculoDTO toDTO(Vehiculo vehiculo);
    public Vehiculo toEntity(VehiculoDTO vehiculo);
    public void mapToEntity(VehiculoDTO vehiculoDTO, @MappingTarget Vehiculo vehiculo);
    
}
