package com.example.todo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private int user_id;
    private String user_name;
    private String user_token;
}
