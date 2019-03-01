package com.nuebus.mapper;


import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.nuebus.dto.GroupDTO;
import com.nuebus.model.Group;



/**
 *
 * @author Valeria
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GroupMapper {
    
    public GroupDTO toDTO(Group group);
    public Group toEntity(GroupDTO groupDTO);
    public void mapToEntity(GroupDTO groupDTO, @MappingTarget Group group);   
    
    
}
