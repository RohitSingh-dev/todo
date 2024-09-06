package com.example.todo.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class TodoResponse {
    private String description;
    private Date dueDate;
    private boolean isCompleted;
    private String status;
}
