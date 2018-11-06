package com.nuebus.dto;

import com.nuebus.model.VehiculoPK;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Valeria
 */
public class VehiculoDTO{
    
    @Valid
    @EmbeddedId
    private VehiculoPK vehiculoPK;    
    @Size( max = 18)
    private String vehMotor = new String();
    @Size( max = 18)
    private String vehChasis = new String();
    @NotNull
    @Size(min = 6, max = 10)
    private String vehPatente = new String();    
    @Size( max = 20)
    private String vehCarroceria = new String();
    @Column (nullable = true )
    @Digits(integer=12, fraction=0)
    private long vehMovilGps = 0;   
    @Digits(integer=3, fraction=0)
    private int vehMpaCodigo = 0;    
    private java.util.Date vehVerificacionTecnica = null;      
    private int vehEstado = 0;
    
    private String vehMpaDesc = "";    
    
    
    public VehiculoDTO(){
        
    }

    public String getVehMotor() {
        return vehMotor;
    }

    public void setVehMotor(String vehMotor) {
        this.vehMotor = vehMotor;
    }

    public String getVehChasis() {
        return vehChasis;
    }

    public void setVehChasis(String vehChasis) {
        this.vehChasis = vehChasis;
    }

    public String getVehPatente() {
        return vehPatente;
    }

    public void setVehPatente(String vehPatente) {
        this.vehPatente = vehPatente;
    }   

    public String getVehCarroceria() {
        return vehCarroceria;
    }

    public void setVehCarroceria(String vehCarroceria) {
        this.vehCarroceria = vehCarroceria;
    }

    public long getVehMovilGps() {
        return vehMovilGps;
    }

    public void setVehMovilGps(long vehMovilGps) {
        this.vehMovilGps = vehMovilGps;
    }
    
    public int getVehMpaCodigo() {
        return vehMpaCodigo;
    }

    public void setVehMpaCodigo(int vehMpaCodigo) {
        this.vehMpaCodigo = vehMpaCodigo;
    }

    public Date getVehVerificacionTecnica() {
        return vehVerificacionTecnica;
    }

    public void setVehVerificacionTecnica(Date vehVerificacionTecnica) {
        this.vehVerificacionTecnica = vehVerificacionTecnica;
    }    

    public VehiculoPK getVehiculoPK() {
        return vehiculoPK;
    }

    public void setVehiculoPK(VehiculoPK vehiculoPK) {
        this.vehiculoPK = vehiculoPK;
    }

    public int getVehEstado() {
        return vehEstado;
    }

    public void setVehEstado(int vehEstado) {
        this.vehEstado = vehEstado;
    }    

    public String getVehMpaDesc() {
        return vehMpaDesc;
    }

    public void setVehMpaDesc(String vehMpaDesc) {
        this.vehMpaDesc = vehMpaDesc;
    }
    
    
}
