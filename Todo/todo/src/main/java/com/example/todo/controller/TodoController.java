package com.example.todo.controller;

import com.example.todo.entity.Todo;
import com.example.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService){
        this.todoService = todoService;
    }

    @PostMapping("")
    public String createTodo(@RequestBody Todo todo){
        return todoService.createTodo(todo);
    }

    @GetMapping("/{id}")
    public Todo getTodo(@PathVariable int id){
        return todoService.getTodo(id);
    }

    @PutMapping("")
    public Todo updateTodo(@RequestBody Todo todo){
        return todoService.updateTodo(todo);
    }

    @DeleteMapping("/{id}")
    public String deleteTodo(@PathVariable int id){
        return todoService.deleteTodo(id);
    }

    @GetMapping("/user/{id}")
    public List<Todo> getTodoBySecUser(@PathVariable int id){
        return todoService.getTodoBySecUser(id);
    }
}
