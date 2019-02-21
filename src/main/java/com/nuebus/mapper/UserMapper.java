
package com.nuebus.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.nuebus.dto.UserDTO;
import com.nuebus.model.Usuario;

/**
 *
 * @author Valeria
 */

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    
    public UserDTO toDTO(Usuario user);
    public Usuario toEntity(UserDTO userDTO);
    public void mapToEntity(UserDTO userDTO, @MappingTarget Usuario user);
   
    
}
