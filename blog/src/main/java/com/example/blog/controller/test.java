package com.example.blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class test {

    @GetMapping("/test/hello")
    public String hello(){
        return "<h1>hello</h1>";
    }
}
