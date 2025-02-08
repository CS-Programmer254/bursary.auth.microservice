package com.softideas.bursary.auth.microservice.presentation.api.controllers;

import com.softideas.bursary.auth.microservice.application.commands.user.CreateUserCommand;
import com.softideas.bursary.auth.microservice.application.commands.user.UpdateUserCommand;
import com.softideas.bursary.auth.microservice.domain.models.DTO.CreateUserDTO;
import com.softideas.bursary.auth.microservice.domain.models.DTO.UpdateUserDTO;
import com.softideas.bursary.auth.microservice.domain.models.User;
import com.softideas.bursary.auth.microservice.infrastructure.services.IMediator;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth/users")
public class UserController {

    private final IMediator mediator;

    @Autowired
    public UserController(IMediator mediator) {
        this.mediator = mediator;
    }
    @PostMapping("/register")
    @PermitAll
    public ResponseEntity<?> createUser(@RequestBody CreateUserDTO createUserDTO) {
        try {
            CreateUserCommand command = new CreateUserCommand(
                    createUserDTO.getFirstName(),
                    createUserDTO.getMiddleName(),
                    createUserDTO.getLastName(),
                    createUserDTO.getEmailAddress(),
                    createUserDTO.getAdmissionNumber(),
                    createUserDTO.getDepartmentId(),
                    createUserDTO.getCourseName(),
                    createUserDTO.getCurrentYear(),
                    createUserDTO.getCourse(),
                    createUserDTO.getNationalIdentificationNumber(),
                    createUserDTO.getPhoneNumber(),
                    createUserDTO.getGender(),
                    createUserDTO.getPassword(),
                    createUserDTO.getRoleId()
            );

            User createdUser = mediator.send(command);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating user: " + e.getMessage());
        }
    }

    @PutMapping("updateProfiles/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable String userId, @RequestBody UpdateUserDTO updateUserDTO) {
        try {

            UpdateUserCommand command = new UpdateUserCommand(
                    updateUserDTO.getFirstName(),
                    updateUserDTO.getMiddleName(),
                    updateUserDTO.getLastName(),
                    updateUserDTO.getEmailAddress(),
                    updateUserDTO.getAdmissionNumber(),
                    updateUserDTO.getDepartmentId(),
                    updateUserDTO.getCourseName(),
                    updateUserDTO.getCurrentYear(),
                    updateUserDTO.getCourse(),
                    updateUserDTO.getNationalIdentificationNumber(),
                    updateUserDTO.getPhoneNumber(),
                    updateUserDTO.getGender(),
                    updateUserDTO.getPassword(),
                    updateUserDTO.getRoleId()
            );

            User updatedUser = mediator.send(command);

            return ResponseEntity.ok(updatedUser);

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user: " + e.getMessage());
        }
    }
}
