package com.oocl.felix.controller;

import com.oocl.felix.annotation.AccessLimit;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MyController {

    @AccessLimit
    @GetMapping("/test")
    public String sayHello() {
        return "Hello World";
    }
}
