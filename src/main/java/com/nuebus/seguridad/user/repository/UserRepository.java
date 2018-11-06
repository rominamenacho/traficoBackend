package com.nuebus.seguridad.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nuebus.seguridad.entity.User;

/**
 * UserRepository
 * 
 * @author vladimir.stankovic
 *
 * Aug 16, 2016
 */
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.username=:username")
    public Optional<User> findByUsername(@Param("username") String username);
}
