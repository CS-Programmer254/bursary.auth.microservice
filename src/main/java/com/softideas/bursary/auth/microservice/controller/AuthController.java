package com.softideas.bursary.auth.microservice.controller;
import com.softideas.bursary.auth.microservice.domain.models.DTO.CreateUserDTO;
import jakarta.annotation.security.PermitAll;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/users")
public class AuthController {
    @PostMapping("/register")
    @PermitAll
    public CreateUserDTO register(){

        return new CreateUserDTO();
    }

    @GetMapping("/login")
    public String login (){

        return "Logins successful";
    }
}