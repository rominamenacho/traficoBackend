/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuebus.mapper;

import com.nuebus.dto.IncidenciaDTO;
import com.nuebus.model.Incidencia;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.TargetType;


/**
 *
 * @author Usuario
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IncidenciaMapper {

    public IncidenciaDTO toDTO(Incidencia incidencia);

    public Incidencia toEntity(IncidenciaDTO incidencia);

    public void mapToEntity(IncidenciaDTO incidenciaDTO, @MappingTarget Incidencia incidencia);
}