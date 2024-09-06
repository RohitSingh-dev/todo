package com.example.todo.service;

import com.example.todo.entity.Todo;
import com.example.todo.model.TodoResponse;
import com.example.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }

    @Transactional
    public String createTodo(Todo todo){
        todoRepository.save(todo);
        return "ToDo Added Successfully";
    }

    @Transactional(readOnly = true)
    public TodoResponse getTodo(int id){
        Todo todo = todoRepository.findById(id).orElseThrow(()-> new RuntimeException("Todo Not Found"));
        TodoResponse todoResponse = new TodoResponse();
        todoResponse.setDescription(todo.getDescription());
        todoResponse.setDueDate(todo.getDueDate());
        todoResponse.setCompleted(todo.isCompleted());
        todoResponse.setStatus(todo.getStatus());
        return todoResponse;
    }

    @Transactional
    public Todo updateTodo(Todo todo){
        Todo existingTodo = todoRepository.findById(todo.getId()).orElseThrow(()-> new RuntimeException("Todo Not Found"));
        existingTodo.setDescription(todo.getDescription());
        existingTodo.setDueDate(todo.getDueDate());
        existingTodo.setCompleted(todo.isCompleted());
        existingTodo.setStatus(todo.getStatus());
        todoRepository.save(existingTodo);
        return existingTodo;
    }

    @Transactional
    public String deleteTodo(int id){
        todoRepository.deleteById(id);
        return "ToDo Deleted Successfully";
    }

    @Transactional(readOnly = true)
    public List<TodoResponse> getTodoBySecUser(int id){
        List<Todo> todos = todoRepository.findBySecUser_Id(id);
        List<TodoResponse> todoResponses = new ArrayList<>();
        for(Todo todo : todos){
            TodoResponse todoResponse = new TodoResponse();
            todoResponse.setDescription(todo.getDescription());
            todoResponse.setDueDate(todo.getDueDate());
            todoResponse.setCompleted(todo.isCompleted());
            todoResponse.setStatus(todo.getStatus());
            todoResponses.add(todoResponse);
        }
        return todoResponses;
    }
}
