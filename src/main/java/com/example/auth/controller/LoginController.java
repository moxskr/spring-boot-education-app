package com.example.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("error", "Wrong username or password!");
        return "login";
    }
}
