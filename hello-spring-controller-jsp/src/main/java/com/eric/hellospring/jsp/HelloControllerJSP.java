package com.eric.hellospring.jsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class HelloControllerJSP {
    @GetMapping("/")
    public String hello() {
        return "hello";
    }
}
