package com.example.todo.controller;

import com.example.todo.model.AuthenticationModel;
import com.example.todo.service.JwtService;
import com.example.todo.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    private final LoginService loginService;

    public LoginController(AuthenticationManager authenticationManager,
                           JwtService jwtService, LoginService loginService){
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationModel authenticationModel){

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationModel.getUsername(), authenticationModel.getPassword()));
        }catch (AuthenticationException e) {
            return new ResponseEntity<String>("Invalid Credentials", HttpStatus.UNAUTHORIZED);
        }
        String token = jwtService.generateToken(authenticationModel.getUsername());
        return ResponseEntity.ok().body(loginService.login(authenticationModel.getUsername(), token));
    }
}
