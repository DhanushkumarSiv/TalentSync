package com.web.userservice.entity;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/*
 * UserPrincipal implements the UserDetails interface.

 * It acts as a bridge between our Users entity
   and Spring Security.

 * It provides the stored username, password hash
   and authorities required by Spring Security
   during authentication and authorization.
 */
public class UserPrincipal implements UserDetails {

    private Users user;

    public UserPrincipal(Users user) {
        this.user = user;
    }

    /**
     * Returns the authorities assigned to the user.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("CANDIDATE"));
    }

    @Override
    public @Nullable String getPassword() {
        return user.getPassword();
    }

    /**
     * Returns the username used for authentication.
     */
    @Override
    public String getUsername() {
        return user.getUserName();
    }
}
