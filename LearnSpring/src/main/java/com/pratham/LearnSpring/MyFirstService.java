package com.pratham.LearnSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MyFirstService {


    private MyFirstClass myFirstClass;

    // Method Dependency Injection
    @Autowired
    public void injectDependencies(@Qualifier("second") MyFirstClass myFirstClass) {
        this.myFirstClass = myFirstClass;
    }

    // Constructor Dependency Injection
//    @Autowired
//    public MyFirstService(@Qualifier("bean2") MyFirstClass myFirstClass) {
//        this.myFirstClass = myFirstClass;
//    }

    public String tellStory() {
        return "the dependency is saying :" + myFirstClass.sayHello();
    }
}
