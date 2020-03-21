package com.example.auth.controller;

import com.example.auth.domain.Todo;
import com.example.auth.domain.User;
import com.example.auth.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    @Autowired
    private TodoService todoService;

    @GetMapping("/home")
    public String home(@AuthenticationPrincipal User user, Model model) {
        Iterable<Todo> todos = todoService.getTodosByUser(user);
        model.addAttribute("todos", todos);
        return "home";
    }

    @PostMapping("/addTodo")
    public String addTodo(@AuthenticationPrincipal User user, String text, Model model) {
        todoService.saveTodo(text, user);
        Iterable<Todo> todos = todoService.getTodosByUser(user);
        model.addAttribute("todos", todos);
        return "home";
    }
}
