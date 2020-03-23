package com.example.auth.controller;

import com.example.auth.entities.User;
import com.example.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        if(!userService.ifUserAlreadyExists(user)){
            user.setActive(true);
            userService.save(user);
            return "redirect:/login";
        }else{
            model.addAttribute("error", "User already exists!");
            return "registration";
        }

    }
}
