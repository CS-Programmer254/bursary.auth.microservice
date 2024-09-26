package com.softideas.bursary.auth.microservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @GetMapping("/hello")
    public String hello(){
        return "Hello,Stanley ..I am SpringBoot AI";
    }
}