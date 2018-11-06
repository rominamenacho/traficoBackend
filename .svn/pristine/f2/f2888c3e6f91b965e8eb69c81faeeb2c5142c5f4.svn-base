/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuebus.mapper;

import com.nuebus.dto.UsuarioDTO;
import com.nuebus.dto.UsuarioPKDTO;
import com.nuebus.model.Usuario;
import com.nuebus.model.UsuarioPK;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

/**
 *
 * @author Valeria
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsuarioMapper {
    
     public UsuarioDTO toDTO(Usuario usuario);

    public Usuario toEntity(UsuarioDTO usuarioDTO);

    public void mapToEntity(UsuarioDTO usuarioDTO, @MappingTarget Usuario usuario);    
   
    public UsuarioPK UsuarioPKDTOTOUsuarioPK( UsuarioPKDTO usuarioPKDTO );
    
    public UsuarioPKDTO UsuarioPKTOUsuarioPKDTO( UsuarioPK usuarioPK );
    
}
