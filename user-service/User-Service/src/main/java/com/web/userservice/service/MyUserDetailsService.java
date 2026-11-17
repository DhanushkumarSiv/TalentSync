package com.web.userservice.service;

import com.web.userservice.entity.UserPrincipal;
import com.web.userservice.entity.Users;
import com.web.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/*
 * Custom implementation of UserDetailsService.

 * It retrieves the user from the database using
   the username provided during authentication.

 * If the user exists, it returns a UserPrincipal object.
   Otherwise, it throws UsernameNotFoundException.
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Users user = userRepository.findByUserName(userName);

        if(user == null) {
            throw new UsernameNotFoundException("User Not Found");
        }

        return new UserPrincipal(user);
    }
}
