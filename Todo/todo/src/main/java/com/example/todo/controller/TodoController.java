package com.example.todo.controller;

import com.example.todo.entity.Todo;
import com.example.todo.model.TodoResponse;
import com.example.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> createTodo(@RequestBody Todo todo){
        return new ResponseEntity<String>(todoService.createTodo(todo), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponse> getTodo(@PathVariable int id){
        return new ResponseEntity<TodoResponse>(todoService.getTodo(id), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<Todo> updateTodo(@RequestBody Todo todo){
        return new ResponseEntity<Todo>(todoService.updateTodo(todo), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable int id){
        return new ResponseEntity<String>(todoService.deleteTodo(id), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<TodoResponse>> getTodoBySecUser(@PathVariable int id){
        return new ResponseEntity<List<TodoResponse>>(todoService.getTodoBySecUser(id), HttpStatus.OK);
    }
}
