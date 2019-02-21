package com.nuebus.dto;

/**
 *
 * @author Valeria
 */
public class UserDTO {
    
    private Long id;        
    private String username;    
    private int enabled;
    private String enabledDesc;
    private Long grupoId;
    private String grupoDesc;
    
    private String nombre;
    private String apellido;
    private String celular;

    public UserDTO() {
    }   
    

    public UserDTO(Long id, String username, int enabled, Long grupoId, String grupoDesc, String nombre, 
            String apellido, String celular) {
        this.id = id;
        this.username = username;
        this.enabled = enabled;        
        this.grupoId = grupoId;
        this.grupoDesc = grupoDesc;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
    }
  
   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }   

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public Long getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(Long grupoId) {
        this.grupoId = grupoId;
    }  

    public String getEnabledDesc() {
        return enabledDesc;
    }

    public void setEnabledDesc(String enabledDesc) {
        this.enabledDesc = enabledDesc;
    }

    public String getGrupoDesc() {
        return grupoDesc;
    }

    public void setGrupoDesc(String grupoDesc) {
        this.grupoDesc = grupoDesc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }   

    @Override
    public String toString() {
        return "UserDTO{" + "id=" + id + ", username=" + username + ", enabled=" + enabled + ", enabledDesc=" + enabledDesc + ", grupoId=" + grupoId + ", grupoDesc=" + grupoDesc + ", nombre=" + nombre + ", apellido=" + apellido + ", celular=" + celular + '}';
    }
        
    
}
