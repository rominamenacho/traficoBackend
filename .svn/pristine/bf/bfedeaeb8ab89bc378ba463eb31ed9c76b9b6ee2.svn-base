/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuebus.mapper;

import com.nuebus.dto.DiagramacionDTO;
import com.nuebus.dto.VueltaDTO;
import com.nuebus.model.Diagramacion;
import com.nuebus.model.Vuelta;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

/**
 *
 * @author Usuario
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DiagramacionMapper {

    public DiagramacionDTO toDTO(Diagramacion diagramacion);

    public Diagramacion toEntity(DiagramacionDTO diagDTO);

    public void mapToEntity(DiagramacionDTO diagDTO, @MappingTarget Diagramacion diagramacion);

    //relaciones
    public VueltaDTO toDTO(Vuelta vuelta);

    public Vuelta toEntity(VueltaDTO vueltaDTO);
}
