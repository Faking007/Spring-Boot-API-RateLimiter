package com.oocl.felix.controller;

import com.oocl.felix.annotation.AccessLimit;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MyController {

    @AccessLimit(queryPerSecond = 1.0)
    @GetMapping("/test")
    public String sayHello() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " - Hello World" ;
    }
}
