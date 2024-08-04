package com.pratham.journalApp.controller;


import com.pratham.journalApp.entity.User;
import com.pratham.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {


    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public String healthcheck(){
        return "ok";
    }

    @PostMapping("/create-user")
    public void createUser(@RequestBody User user) {
        userService.saveUser(user);
    }

}
