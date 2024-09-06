package com.example.todo.controller;

import com.example.todo.entity.SecUser;
import com.example.todo.service.SecUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class SecUserController {

    private final SecUserService secUserService;

    @Autowired
    public SecUserController(SecUserService secUserService){
        this.secUserService = secUserService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody SecUser secUser){
        return new ResponseEntity<String>(secUserService.createUser(secUser), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SecUser> getUser(@PathVariable int id){
        return new ResponseEntity<SecUser>(secUserService.getUser(id), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<SecUser> updateUser(@RequestBody SecUser secUser){
        return new ResponseEntity<SecUser>(secUserService.updateUser(secUser), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id){
        return new ResponseEntity<String>(secUserService.deleteUser(id), HttpStatus.NO_CONTENT);
    }
}
