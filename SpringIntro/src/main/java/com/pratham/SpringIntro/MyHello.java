package com.pratham.SpringIntro;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyHello {

   @GetMapping("hello")
   public String hello()
    {
        return  "Hello";
    }
}
