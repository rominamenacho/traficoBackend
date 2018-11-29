
package com.nuebus.dto;

/**
 *
 * @author Valeria
 */
public class ChoferUnidadDTO {
    
    String peliIda;
    String peliVta;
    
    ServicioDTO servIda;    
    ServicioDTO servRet;
    
    String videoIda;
    String videoVta;

    public ChoferUnidadDTO() {
        
    }   

    public String getPeliIda() {
        return peliIda;
    }

    public void setPeliIda(String peliIda) {
        this.peliIda = peliIda;
    }

    public String getPeliVta() {
        return peliVta;
    }

    public void setPeliVta(String peliVta) {
        this.peliVta = peliVta;
    }

    public ServicioDTO getServIda() {
        return servIda;
    }

    public void setServIda(ServicioDTO servIda) {
        this.servIda = servIda;
    }

    public ServicioDTO getServRet() {
        return servRet;
    }

    public void setServRet(ServicioDTO servRet) {
        this.servRet = servRet;
    }

    public String getVideoIda() {
        return videoIda;
    }

    public void setVideoIda(String videoIda) {
        this.videoIda = videoIda;
    }

    public String getVideoVta() {
        return videoVta;
    }

    public void setVideoVta(String videoVta) {
        this.videoVta = videoVta;
    }   
    
}
