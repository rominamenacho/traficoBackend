package com.nuebus.dto;

/**
 *
 * @author Valeria
 */
public class ResetPassDTO {
    
    String empresa;
    Long legajo;
    String emailRecuperacion;

    public ResetPassDTO() {
    }

    public ResetPassDTO(String empresa, Long legajo, String emailRecuperacion) {
        this.empresa = empresa;
        this.legajo = legajo;
        this.emailRecuperacion = emailRecuperacion;
    }    

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public Long getLegajo() {
        return legajo;
    }

    public void setLegajo(Long legajo) {
        this.legajo = legajo;
    }

    public String getEmailRecuperacion() {
        return emailRecuperacion;
    }

    public void setEmailRecuperacion(String emailRecuperacion) {
        this.emailRecuperacion = emailRecuperacion;
    }   
    
    
}
