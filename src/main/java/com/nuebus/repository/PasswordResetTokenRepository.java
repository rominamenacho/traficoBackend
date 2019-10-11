package com.nuebus.repository;

import com.nuebus.model.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Valeria
 */
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    
    PasswordResetToken findBytoken( String token );
    
}
