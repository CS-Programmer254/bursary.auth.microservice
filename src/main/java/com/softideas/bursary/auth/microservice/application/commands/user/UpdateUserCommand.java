package com.softideas.bursary.auth.microservice.application.commands.user;

import com.softideas.bursary.auth.microservice.domain.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserCommand {
    private UUID userId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String emailAddress;
    private String admissionNumber;
    private String departmentId;
    private String courseName;
    private String currentYear;
    private String course;
    private String nationalIdentificationNumber;
    private String phoneNumber;
    private String gender;
    private String password;
    private Role role;
}
