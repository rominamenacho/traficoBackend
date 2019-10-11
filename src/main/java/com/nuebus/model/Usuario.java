package com.nuebus.model;


import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;



/**
 *
 * @author Valeria
 */

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    UsuarioPK usuarioPk; 
    
    @Column(name="USR_LOGIN", unique=true)
    String username;    
      
    @Column(name="USR_PASSWORD_TXT")     
    String password; 
    
    @Column(name="USR_PRS_NOMBRE")            
    String nombre;          
    
    @Column(name="USR_ESTADO")
    boolean estado;
    
    @Column( name=" USR_EMAIL")
    String email;
    
    @OneToOne()
    Group group;
    
    
    @ManyToOne
    @JoinColumnsOrFormulas(value = {
        @JoinColumnOrFormula(formula = @JoinFormula(value = "USR_PRS_EMP_CODIGO", referencedColumnName = "agn_emp_codigo")),
        @JoinColumnOrFormula(column = @JoinColumn(name = "USR_PRS_AGN_CODIGO", referencedColumnName = "agn_codigo")),        
    })    
    private Agencia agencia;
    
    
    @ManyToOne
    @JoinColumnsOrFormulas( value = {
        @JoinColumnOrFormula(formula = @JoinFormula(value = "USR_PRS_EMP_CODIGO", referencedColumnName = "prs_emp_codigo")),
        @JoinColumnOrFormula(formula = @JoinFormula(value = "USR_PRS_LEGAJO", referencedColumnName = "prs_legajo")),        
    } ) 
    private Personal personal;
    
    public UsuarioPK getUsuarioPk() {
        return usuarioPk;
    }

    public void setUsuarioPk(UsuarioPK usuarioPk) {
        this.usuarioPk = usuarioPk;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }      

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    } 

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }    

    @Override
    public String toString() {
        return "Usuario{" + "usuarioPk=" + usuarioPk + ", username=" + username + ", password=" + password + ", nombre=" + nombre + ", estado=" + estado + ", email=" + email + ", group=" + group + '}';
    }
   

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.usuarioPk);
        hash = 73 * hash + Objects.hashCode(this.username);
        hash = 73 * hash + Objects.hashCode(this.password);
        hash = 73 * hash + Objects.hashCode(this.nombre);
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
        Usuario other = (Usuario) obj;
        if (agencia != other.agencia) {
            return false;
        }
        if (group == null) {
            if (other.group != null) {
                return false;
            }
        } else if (!group.equals(other.group)) {
            return false;
        }
        if (nombre == null) {
            if (other.nombre != null) {
                return false;
            }
        } else if (!nombre.equals(other.nombre)) {
            return false;
        }
        if (password == null) {
            if (other.password != null) {
                return false;
            }
        } else if (!password.equals(other.password)) {
            return false;
        }
        if (username == null) {
            if (other.username != null) {
                return false;
            }
        } else if (!username.equals(other.username)) {
            return false;
        }
        if (usuarioPk == null) {
            if (other.usuarioPk != null) {
                return false;
            }
        } else if (!usuarioPk.equals(other.usuarioPk)) {
            return false;
        }
        return true;
    }

      
}
