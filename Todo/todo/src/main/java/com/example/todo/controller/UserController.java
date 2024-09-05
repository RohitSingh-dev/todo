package com.example.todo.controller;

import com.example.todo.entity.SecUser;
import com.example.todo.service.SecUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final SecUserService secUserService;

    @Autowired
    public UserController(SecUserService secUserService){
        this.secUserService = secUserService;
    }

    @PostMapping("")
    public String createUser(@RequestBody SecUser secUser){
        return secUserService.createUser(secUser);
    }

    @GetMapping("/{id}")
    public SecUser getUser(@PathVariable int id){
        return secUserService.getUser(id);
    }

    @PutMapping("")
    public SecUser updateUser(@RequestBody SecUser secUser){
        return secUserService.updateUser(secUser);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id){
        return secUserService.deleteUser(id);
    }
}
