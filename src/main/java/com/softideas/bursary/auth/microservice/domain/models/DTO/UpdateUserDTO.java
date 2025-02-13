package com.softideas.bursary.auth.microservice.domain.models.DTO;

import com.softideas.bursary.auth.microservice.domain.models.Role;
import lombok.Data;

@Data
public class UpdateUserDTO {

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
