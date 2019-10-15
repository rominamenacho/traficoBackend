package com.nuebus.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.nuebus.auth.repository.UsuarioRepository;
import com.nuebus.auth.service.GroupService;
import com.nuebus.auth.service.UserService;
import com.nuebus.dto.GroupDTO;
import com.nuebus.dto.UsuarioDTO;
import com.nuebus.excepciones.ErrorException;
import com.nuebus.excepciones.ResourceNotFoundException;
import com.nuebus.mapper.UserMapper;
import com.nuebus.model.Group;
import com.nuebus.model.MailConfig;
import com.nuebus.model.PasswordResetToken;
import com.nuebus.model.Usuario;
import com.nuebus.model.UsuarioPK;
import com.nuebus.repository.MailSenderRepository;
import com.nuebus.repository.PasswordResetTokenRepository;
import com.nuebus.service.EmailSenderService;
import com.nuebus.utilidades.IAuthenticationFacade;
import com.nuebus.utilidades.Utilities;
import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    GroupService groupService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    EmailSenderService emailSenderService;

    @Autowired
    MailSenderRepository mailSenderRepository;
    
    @Autowired
    PasswordResetTokenRepository passwordResetTokenRepository;
    
    @Autowired
    IAuthenticationFacade authenticationFacade;     
    
    
    @Value("${ipExternaServer}")
    String ipExternaServer;

    @Override
    public Usuario findByUsername(String login) {
        return usuarioRepository.findByUsername(login);
    }

    @Override
    public Page<UsuarioDTO> findAllUsuarioByEmpresaConDatos(String empresa, Pageable pageable) {
        return usuarioRepository.findAllByEmpresa(empresa, pageable).map(u -> userMapper.toDTO(u));
    }

    @Override
    public Page<UsuarioDTO> findAllByEmpresaAndBusqueda(String empresa, String busqueda, Pageable pageable) {
        busqueda = busqueda.toUpperCase();
        return usuarioRepository.findAllByEmpresaAndBusqueda(empresa, busqueda, pageable).map(u -> userMapper.toDTO(u));
    }

    @Override
    @Transactional(readOnly = false)
    public void updateGrupoUsuario(String username, GroupDTO groupDTO) {

        Usuario usuario = findByUsername(username);
        Group grupo = groupService.getById(groupDTO.getId());

        usuario.setGroup(grupo);
        usuarioRepository.save(usuario);

    }

    @Override
    public Page<UsuarioDTO> findAllUsuarioConDatos(Pageable pageable) {
        return usuarioRepository.findAll(pageable).map(u -> userMapper.toDTO(u));
    }

    @Override
    public Page<UsuarioDTO> findAllByBusqueda(String busqueda, Pageable pageable) {
        busqueda = busqueda.toUpperCase();
        return usuarioRepository.findAllByBusqueda(busqueda, pageable).map(u -> userMapper.toDTO(u));
    }

    @Override
    public Optional<Usuario> findById(String empresa, long legajo) {
        UsuarioPK usuarioPK = new UsuarioPK(empresa, legajo);
        return usuarioRepository.findById(usuarioPK);
    }

    @Override
    @Transactional(readOnly = false)
    public void createPasswordResetTokenForUser( String empresaMail, String empresaPersonal,
                                    long legajoPersonal, String emailPersonal ) {
        
        Optional<MailConfig> mailConfig = mailSenderRepository.findById(empresaMail);
        Optional<Usuario> usuarioOp = findById(empresaMail, legajoPersonal);

        if( mailConfig.isPresent() && usuarioOp.isPresent() ) {
            
            Usuario usuario = usuarioOp.get();             
            String token = UUID.randomUUID().toString().replaceAll( "-", "" );
            PasswordResetToken myToken = new PasswordResetToken( token, usuario, false );
            passwordResetTokenRepository.save(myToken);          

            String subject = "[nuebus] Por favor resetee su contraseña";
            String fromCustomizado = "no-responder@" + empresaPersonal + ".com";

            String nombrePersonal = (usuario.getPersonal() != null
                    && usuario.getPersonal().getNombre() != null)
                    ? usuario.getPersonal().getNombre() : "Sin especificar";

            String textoMail = "Hola " + nombrePersonal + "," + Utilities.SALTO_LINEA
                    + "usuario: " + usuario.getUsername() + Utilities.SALTO_LINEA
                    + "Su contraseña se ha reseteado.Podra recuperarla accediendo al siguiente link:" + Utilities.SALTO_LINEA
                    + ipExternaServer+ "/#/change-password/" + token + Utilities.SALTO_LINEA
                    + "Por favor no responda esta mail.";

            emailSenderService.sendSimpleMessage(   mailConfig.get(),
                                                    subject,
                                                    textoMail,
                                                    emailPersonal,
                                                    fromCustomizado );
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void updatePasswordReseteada(String token, String password, String confirmPassword) {
        
        if( password == null  || confirmPassword == null 
                ||  !password.equalsIgnoreCase( confirmPassword ) ){
            
            throw new  ErrorException("Error Validacion", "Las contraseñas deben ser iguales" ); 
        }
        
        PasswordResetToken passToken =  passwordResetTokenRepository.findBytoken(token);
        Usuario usuario = validatePasswordResetToken( passToken );   
        
        passToken.setOcupado(true);
        passwordResetTokenRepository.save( passToken );        
           
        usuario.setPassword(password);
        usuarioRepository.save( usuario );
     
    }
    
    
    public Usuario validatePasswordResetToken( PasswordResetToken passToken ) {
        
        if ((passToken == null) 
                || ( passToken.getUser() == null )  ) {
            throw new  ErrorException("Error Validacion", "Token Invalido" );            
        }
        
        if ( (passToken != null) 
                &&  passToken.getOcupado()  ) {
            throw new  ErrorException("Error Validacion", " El token ya fue utilizado. Solicite nuevo reseteo de contraseña." );            
        }

        Calendar cal = Calendar.getInstance();
        if ((passToken.getExpiryDate()
            .getTime() - cal.getTime()
            .getTime()) <= 0) {
            throw new  ErrorException( "Error Validacion", "Token expirado" );                        
        }        
        return passToken.getUser();
    }

    @Override
    @Transactional(readOnly = false)
    public void updatePasswordChange(String oldPassword, String newpassword, String confirmNewPassword) {               
        
        Usuario usuario = usuarioRepository.findByUsername( authenticationFacade.getUsuario().getUsername() );           
        valdidarPasswordChange( usuario, oldPassword, newpassword, confirmNewPassword );
        
        usuario.setPassword(newpassword);
        usuarioRepository.save( usuario );   
        
    }
    
    private void valdidarPasswordChange( Usuario usuario, String oldPassword, 
                                         String newpassword, String confirmNewPassword ){
        if( usuario == null ){
            throw new ResourceNotFoundException( (long)-1, "Usuario no encontrado" );
        }        
        
        if( newpassword == null ){
            throw new  ErrorException( "Error Validacion", "Debe especificar Contraseña" );  
        }
        
        if( confirmNewPassword == null ){
            throw new  ErrorException( "Error Validacion", "Debe especificar la confirmacion de la Contraseña" );  
        }
        
        if( !newpassword.equalsIgnoreCase(confirmNewPassword) ){
            throw new  ErrorException( "Error Validacion", "La contraseña y su confirmacion deben ser iguales" );  
        }              
        
        if( !usuario.getPassword().equals( oldPassword ) ){ 
            
            System.out.println( usuario );
            
            System.out.println("Pass Actual " + usuario.getPassword() + " - Pass Vieja " + oldPassword );
            throw new  ErrorException( "Error Validacion", "La contraseña actual es incorrecta" );  
        }
    
    }   

}
