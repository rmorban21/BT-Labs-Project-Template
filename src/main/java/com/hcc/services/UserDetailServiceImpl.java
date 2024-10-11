package com.hcc.services;

import com.hcc.entities.User;
import com.hcc.repositories.UserRepository;
import com.hcc.utils.CustomPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;  // Injecting the UserRepository

    @Autowired
    private CustomPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Retrieve user from the database using UserRepository and handle Optional
        Optional<User> userOptional = userRepository.findByUsername(username);

        // If the user is not found, throw an exception
        return userOptional.orElseThrow(() ->
                new UsernameNotFoundException("User not found with username: " + username)
        );
    }
}
