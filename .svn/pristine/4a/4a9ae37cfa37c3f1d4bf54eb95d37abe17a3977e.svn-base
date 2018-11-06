/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuebus.mapper;

import com.nuebus.dto.ChoferMinDTO;
import com.nuebus.dto.DiagramacionDTO;
import com.nuebus.dto.VehiculoMinDTO;
import com.nuebus.dto.VueltaDTO;
import com.nuebus.model.Chofer;
import com.nuebus.model.Diagramacion;
import com.nuebus.model.Vehiculo;
import com.nuebus.model.Vuelta;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

/**
 *
 * @author Usuario
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VueltaMapper {

    public VueltaDTO toDTO(Vuelta vuelta);

    public Vuelta toEntity(VueltaDTO vueltaDto);

    public void mapToEntity(VueltaDTO vueltaDTO, @MappingTarget Vuelta vuelta);

    public VehiculoMinDTO toDTO(Vehiculo vehiculo);

    public Vehiculo toDTO(VehiculoMinDTO vehiculoMinDTO);

    public ChoferMinDTO toDTO(Chofer cho);

    public Chofer toDTO(ChoferMinDTO choMinDTO);

    //ref a diagr
    public DiagramacionDTO toDTO(Diagramacion d);

    public Diagramacion toDTO(DiagramacionDTO diagDTO);
}
