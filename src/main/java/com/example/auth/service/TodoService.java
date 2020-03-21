package com.example.auth.service;

import com.example.auth.domain.Todo;
import com.example.auth.domain.User;
import com.example.auth.repos.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
    @Autowired
    private TodoRepo todoRepo;

    public void saveTodo(String text, User user) {
        Todo todo = new Todo(text, user);
        todoRepo.save(todo);
    }

    public Iterable<Todo> getTodosByUser(User user) {
        return todoRepo.findTodoByAuthor(user);
    }
}
