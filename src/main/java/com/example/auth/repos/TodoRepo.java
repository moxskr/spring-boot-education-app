package com.example.auth.repos;

import com.example.auth.domain.Todo;
import com.example.auth.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepo extends CrudRepository<Todo, Long> {
    Iterable<Todo> findTodoByAuthor(User user);
    void deleteTodoById(Integer id);
    Todo findTodoById(Integer id);
}
