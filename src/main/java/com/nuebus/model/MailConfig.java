
package com.nuebus.model;

import com.nuebus.enumeraciones.SiNo;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Valeria
 */

@Entity
@Table(name= "mail_sender")
public class MailConfig implements Serializable{
    
    @Id 
    @Column( name="mls_emp_codigo", length = 4 )
    private String empresa;
    
    @Column( name="mls_user" )
    private String user = new String();
    
    @Column ( name = "mls_password")
    private String password = new String();        
    
    @Column( name = "mls_smtp_server" )
    private String smtpServer = new String();
    
    @Column( name = "mls_detalle" )
    private String detalle = new String();
    
    @Column( name = "mls_smtp_port" )
    private int smtpPort = 25;
    
    @Column( name = "mls_requiere_ssl" )
    private String requiereSsl = SiNo.NO.valor;
    
    @Column( name = "mls_criterio_copy" )
    private String criterioCopy;   
    
    @Column( name = "mls_email_copy" )
    private String emailCopy;

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSmtpServer() {
        return smtpServer;
    }

    public void setSmtpServer(String smtpServer) {
        this.smtpServer = smtpServer;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public int getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(int smtpPort) {
        this.smtpPort = smtpPort;
    }

    public String getRequiereSsl() {
        return requiereSsl;
    }

    public void setRequiereSsl(String requiereSsl) {
        this.requiereSsl = requiereSsl;
    }

    public String getCriterioCopy() {
        return criterioCopy;
    }

    public void setCriterioCopy(String criterioCopy) {
        this.criterioCopy = criterioCopy;
    }

    public String getEmailCopy() {
        return emailCopy;
    }

    public void setEmailCopy(String emailCopy) {
        this.emailCopy = emailCopy;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.empresa);
        hash = 17 * hash + Objects.hashCode(this.user);
        hash = 17 * hash + Objects.hashCode(this.password);
        hash = 17 * hash + Objects.hashCode(this.smtpServer);
        hash = 17 * hash + Objects.hashCode(this.detalle);
        hash = 17 * hash + this.smtpPort;
        hash = 17 * hash + Objects.hashCode(this.requiereSsl);
        hash = 17 * hash + Objects.hashCode(this.criterioCopy);
        hash = 17 * hash + Objects.hashCode(this.emailCopy);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MailConfig other = (MailConfig) obj;
        if (this.smtpPort != other.smtpPort) {
            return false;
        }
        if (!Objects.equals(this.empresa, other.empresa)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.smtpServer, other.smtpServer)) {
            return false;
        }
        if (!Objects.equals(this.detalle, other.detalle)) {
            return false;
        }
        if (!Objects.equals(this.requiereSsl, other.requiereSsl)) {
            return false;
        }
        if (!Objects.equals(this.criterioCopy, other.criterioCopy)) {
            return false;
        }
        if (!Objects.equals(this.emailCopy, other.emailCopy)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MailSender{" + "empresa=" + empresa + ", user=" + user + ", password=" + password + ", smtpServer=" + smtpServer + ", detalle=" + detalle + ", smtpPort=" + smtpPort + ", requiereSsl=" + requiereSsl + ", criterioCopy=" + criterioCopy + ", emailCopy=" + emailCopy + '}';
    }    
}
