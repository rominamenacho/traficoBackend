package com.nuebus.seguridad.security;

import java.util.Optional;

import com.nuebus.seguridad.entity.User;

/**
 * 
 * @author vladimir.stankovic
 *
 * Aug 17, 2016
 */
public interface UserService {
    public Optional<User> getByUsername(String username);
}
