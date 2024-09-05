package com.example.todo.repository;

import com.example.todo.entity.Todo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Integer> {
    public List<Todo> findBySecUser_Id(int id);
}
