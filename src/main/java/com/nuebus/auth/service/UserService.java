package com.nuebus.auth.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.nuebus.dto.GroupDTO;
import com.nuebus.dto.UsuarioDTO;
import com.nuebus.model.Usuario;
import java.util.Optional;

public interface UserService {

    public Usuario findByUsername(String login);

    public Page<UsuarioDTO> findAllUsuarioByEmpresaConDatos(String empresa, Pageable pageable);

    public Page<UsuarioDTO> findAllByEmpresaAndBusqueda(String empresa, String busqueda, Pageable pageable);

    public void updateGrupoUsuario(String username, GroupDTO groupDTO);

    public Page<UsuarioDTO> findAllUsuarioConDatos(Pageable pageable);

    public Page<UsuarioDTO> findAllByBusqueda(String busqueda, Pageable pageable);

    public Optional<Usuario> findById(String empresa, long legajo);

    public void createPasswordResetTokenForUser(String empresaMail, String empresaPersonal,
            long legajoPersonal, String emailPersonal);

    public void updatePasswordReseteada(String token, String password, String confirmPassword);
}
