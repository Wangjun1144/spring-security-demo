package com.example.demo.controller;


import com.example.demo.entity.UsersDemo;
import com.example.demo.mapper.UsersDemoMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class UsersDemoController {

    @Resource
    UsersDemoMapper usersDemoMapper;

    @GetMapping("/login")
    String login(Model model) {
        return "user/login";
    }

    @GetMapping("/register")
    String register() {
        return "user/register";
    }

    @PostMapping("/register")
    String registerDone(Model model, String username, String password) {
        UsersDemo usersDemo = usersDemoMapper.selectById(username);
        if (usersDemo != null) {
            model.addAttribute("msg", "用户名已存在！");
            return "user/register";
        } else {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            usersDemo = new UsersDemo(username, passwordEncoder.encode(password), true);
            usersDemoMapper.insert(usersDemo);
            return "redirect:/user/login";
        }
    }
}
