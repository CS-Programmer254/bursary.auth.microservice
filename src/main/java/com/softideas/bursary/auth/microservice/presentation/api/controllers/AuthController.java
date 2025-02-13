package com.softideas.bursary.auth.microservice.presentation.api.controllers;

import com.softideas.bursary.auth.microservice.application.commands.user.CreateUserCommand;
import com.softideas.bursary.auth.microservice.application.commands.user.UpdateUserCommand;
import com.softideas.bursary.auth.microservice.application.services.CustomUserDetailsService;
import com.softideas.bursary.auth.microservice.domain.models.DTO.*;
import com.softideas.bursary.auth.microservice.domain.models.User;
import com.softideas.bursary.auth.microservice.infrastructure.services.IMediator;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

    private final IMediator mediator;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsService userService;

    @Autowired
    public AuthController(IMediator mediator) {

        this.mediator = mediator;
    }

    @PostMapping("/register")
    @PermitAll
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDTO createUserDTO) {
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
                    createUserDTO.getRole()
            );

            UserResponseDTO createdUser = mediator.send(command);

            if (createdUser == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User could not be created.");
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating user: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        try {

            User userDetails =  (User) userService.loadUserByUsername(loginRequestDTO.getPhoneNumber());

            if (!passwordEncoder.matches(loginRequestDTO.getPassword(), userDetails.getPassword())) {

                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid phone number or password");
            }

            if (!userDetails.isEnabled()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not verified. Please verify your account.");
            }

            String jwt = userService.generateAuthToken(userDetails);

            return ResponseEntity.ok(new LoginResponseDTO(jwt, userDetails.getFirstName() + " logged in successfully"));

        } catch (UsernameNotFoundException e) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid phone number or password");
        }
    }

    @PostMapping("/verify-otp")
    @PermitAll
    public ResponseEntity<?> verifyOtp(@Valid @RequestBody VerifyOtpDTO verifyOtpDTO) {
        try {
            boolean isOtpValid = userService.verifyOtp(verifyOtpDTO.getPhoneNumber(), verifyOtpDTO.getOtp());

            if (!isOtpValid) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired OTP");
            }

            return ResponseEntity.ok("OTP verified successfully. You can now log in.");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error verifying OTP: " + e.getMessage());
        }
    }


    @PutMapping("/users/updateProfiles/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable UUID userId, @Valid @RequestBody UpdateUserDTO updateUserDTO){
        try {
            UpdateUserCommand command = new UpdateUserCommand(
                    userId,
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
                    updateUserDTO.getRole()
            );

            UserResponseDTO updatedUser = mediator.send(command);

            if (updatedUser == null) {

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
            }

            return ResponseEntity.ok(updatedUser);

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user: " + e.getMessage());
        }
    }
}
