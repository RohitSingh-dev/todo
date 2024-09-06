package com.example.todo.service;

import com.example.todo.entity.SecUser;
import com.example.todo.model.LoginResponse;
import com.example.todo.repository.SecUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginService {

    private final SecUserRepository secUserRepository;

    public LoginService(SecUserRepository secUserRepository){
        this.secUserRepository = secUserRepository;
    }

    @Transactional(readOnly = true)
    public LoginResponse login(String username, String token){
        SecUser secUser = secUserRepository.findByEmail(username);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUser_id(secUser.getId());
        loginResponse.setUser_name(secUser.getName());
        loginResponse.setUser_token(token);
        return loginResponse;
    }
}
