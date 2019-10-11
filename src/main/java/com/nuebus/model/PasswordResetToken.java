 package com.nuebus.model;


import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
public class PasswordResetToken implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private static final int EXPIRATION = 60 * 24;    
    
    @GenericGenerator(
            name = "tokenSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "token_seq")
                ,
                    @Parameter(name = "initial_value", value = "3")
                ,
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @Id  
    @GeneratedValue(generator = "tokenSequenceGenerator")
    @Column(name="id")
    private Long id;  
    @Column( length = 36)
    private String token;

    @OneToOne(targetEntity = Usuario.class, fetch = FetchType.EAGER)
    @JoinColumns({
          @JoinColumn(name="USR_PRS_EMP_CODIGO", referencedColumnName="USR_PRS_EMP_CODIGO" ),
          @JoinColumn(name="USR_PRS_LEGAJO", referencedColumnName="USR_PRS_LEGAJO")
    })
    private Usuario user;

    private Date expiryDate;
    
    private Boolean ocupado;

    public PasswordResetToken() {
        super();
    }

    public PasswordResetToken(final String token) {
        super();

        this.token = token;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    public PasswordResetToken(final String token, final Usuario user, final Boolean ocupado ) {
        super();

        this.token = token;
        this.user = user;
        this.ocupado = ocupado;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    //
    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(final String token) {
        this.token = token;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(final Usuario user) {
        this.user = user;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(final Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    private Date calculateExpiryDate(final int expiryTimeInMinutes) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

    public void updateToken(final String token) {
        this.token = token;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    //

    public Boolean getOcupado() {
        return ocupado;
    }

    public void setOcupado(Boolean ocupado) {
        this.ocupado = ocupado;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.token);
        hash = 23 * hash + Objects.hashCode(this.user);
        hash = 23 * hash + Objects.hashCode(this.expiryDate);
        hash = 23 * hash + Objects.hashCode(this.ocupado);
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
        final PasswordResetToken other = (PasswordResetToken) obj;
        if (!Objects.equals(this.token, other.token)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.expiryDate, other.expiryDate)) {
            return false;
        }
        if (!Objects.equals(this.ocupado, other.ocupado)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PasswordResetToken{" + "id=" + id + ", token=" + token + ", user=" + user + ", expiryDate=" + expiryDate + ", ocupado=" + ocupado + '}';
    }   

}
