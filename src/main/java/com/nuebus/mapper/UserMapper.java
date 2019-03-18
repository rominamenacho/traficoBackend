
package com.nuebus.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import com.nuebus.dto.UsuarioDTO;
import com.nuebus.model.Usuario;

/**
 *
 * @author Valeria
 */

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    
    public UsuarioDTO toDTO(Usuario user);
    public Usuario toEntity(UsuarioDTO userDTO);
    public void mapToEntity(UsuarioDTO userDTO, @MappingTarget Usuario user);
   
    
}
