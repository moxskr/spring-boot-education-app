package com.example.auth.controller;

import com.example.auth.domain.Todo;
import com.example.auth.domain.User;
import com.example.auth.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
    @Autowired
    private TodoService todoService;

    @GetMapping("/")
    public String home(@AuthenticationPrincipal User user, Model model) {
        Iterable<Todo> todos = todoService.getTodosByUser(user);
        model.addAttribute("todos", todos);
        model.addAttribute("username", user.getUsername());
        return "home";
    }

    @PostMapping("/addTodo")
    public String addTodo(@AuthenticationPrincipal User user, String text, Model model) {
        todoService.saveTodo(text, user);
        return "redirect:/";
    }

    @GetMapping("/removeTodo/{id}")
    public String removeTodo(@AuthenticationPrincipal User user, @PathVariable("id") Integer id, Model model) {
        todoService.deleteTodoById(id);
        return "redirect:/";
    }

    @GetMapping("/doneTodo/{id}")
    public String doneTodo(@AuthenticationPrincipal User user, @PathVariable("id") Integer id, Model model) {
        todoService.doneTodoById(id);
        Iterable<Todo> todos = todoService.getTodosByUser(user);
        model.addAttribute("todos", todos);
        return "redirect:/";
    }

}
