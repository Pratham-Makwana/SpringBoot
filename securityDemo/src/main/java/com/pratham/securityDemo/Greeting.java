package com.pratham.securityDemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Greeting {

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello";
    }
}
