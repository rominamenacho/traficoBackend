/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nuebus.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Usuario
 */
public class IncidenciaDTO extends AbstractDTO {
    
    @Size( max = 60)
    String  in_descripcion;
    @Digits(integer=1, fraction=0)
    int in_tipo;
    @Size( max = 60)
    String  in_color;
    @NotNull
    @NotBlank
    @Size( max = 4)
    String  in_empresa;
    @NotBlank
    @Size( max = 10, message = "limite maximo 10 caracteres.")
    String codigo;
    
   // int in_id;
  //  private UserContext userContext;

  /*  public int getIn_id() {
        return in_id;
    }

    public void setIn_id(int in_id) {
        this.in_id = in_id;
    }*/
      
    
    public String getIn_descripcion() {
        return in_descripcion;
    }

    public void setIn_descripcion(String in_descripcion) {
        this.in_descripcion = in_descripcion;
    }

    public int getIn_tipo() {
        return in_tipo;
    }
    
    public String getIn_tipoString() {
          String in_tipoString="";
        /*0 unidades , 1 choferes */
        if(in_tipo==0){
        in_tipoString= "Unidades";
        }else if(in_tipo ==1){
         in_tipoString= "Personal ";
        }
        return in_tipoString;
    }

    public void setIn_tipo(int in_tipo) {
        this.in_tipo = in_tipo;
    }

    public String getIn_color() {
        return in_color;
    }

    public void setIn_color(String in_color) {
        this.in_color = in_color;
    }

    public String getIn_empresa() {
        return in_empresa;
    }

    public void setIn_empresa(String in_empresa) {
       if(!in_empresa.equals("")){
        this.in_empresa = in_empresa;
        }else{
       // this.userContext.getEmpresa();
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
}
