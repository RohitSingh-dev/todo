package com.example.todo.service;

import com.example.todo.entity.SecUser;
import com.example.todo.repository.SecUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {

    private final SecUserRepository secUserRepository;

    @Autowired
    public AuthService(SecUserRepository secUserRepository){
        this.secUserRepository = secUserRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) {

        SecUser secUser = Optional.ofNullable(secUserRepository.findByEmail(username))
                .orElseThrow(() -> new RuntimeException("User not found"));

        return User.builder().username(secUser.getEmail()).password(secUser.getPassword()).build();
    }
}
