package com.example.todo.service;

import com.example.todo.entity.SecUser;
import com.example.todo.repository.SecUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class SecUserService {

    private final SecUserRepository secUserRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecUserService(SecUserRepository secUserRepository, PasswordEncoder passwordEncoder){
        this.secUserRepository = secUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public String createUser(SecUser secUser){
        SecUser checkUser = secUserRepository.findByEmail(secUser.getEmail());
        if(Objects.nonNull(checkUser)){
            throw new RuntimeException("User already exists, Enter different email");
        }
        secUser.setPassword(passwordEncoder.encode(secUser.getPassword()));
        secUserRepository.save(secUser);
        return "User Created Successfully";
    }

    @Transactional(readOnly = true)
    public SecUser getUser(int id) {
        return secUserRepository.findById(id).orElseThrow(()-> new RuntimeException("User Not Found"));
    }

    @Transactional
    public SecUser updateUser(SecUser secUser) {
        SecUser existingSecUser = secUserRepository.findById(secUser.getId()).orElseThrow(()-> new RuntimeException("User Not Found"));
        existingSecUser.setName(secUser.getName());
        existingSecUser.setEmail(secUser.getEmail());
        existingSecUser.setPhoneNo(secUser.getPhoneNo());
        secUserRepository.save(existingSecUser);
        return existingSecUser;
    }

    @Transactional
    public String deleteUser(int id) {
        secUserRepository.deleteById(id);
        return "User Deleted Successfully";
    }
}
