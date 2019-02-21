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
    
   /* public PermisoDTO toDTO(Permiso per);
    public Permission toEntity(PermisoDTO permissionDTO);
    public void mapToEntity(PermisoDTO permissionDTO, @MappingTarget Permission permission); 
    
    public  Set<PermisoDTO> fromSource( Set<Permission> permissions);
    public  Set<Permission> toSource( Set<PermisoDTO> permissionsDTO);  
    
    
    public GrupoComboDTO toComboDTO(Group group);
    public Group toEntity(GrupoComboDTO grupoComboDTO);*/
    
    
}
