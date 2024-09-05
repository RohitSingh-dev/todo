package com.example.todo.repository;

import com.example.todo.entity.SecUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecUserRepository extends CrudRepository<SecUser, Integer> {
    public SecUser findByEmail(String email);
}
