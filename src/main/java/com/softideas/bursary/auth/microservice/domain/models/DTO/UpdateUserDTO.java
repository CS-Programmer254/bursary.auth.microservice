package com.softideas.bursary.auth.microservice.domain.models.DTO;

import lombok.Data;

@Data
public class UpdateUserDTO {

    private String userId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String emailAddress;
    private String admissionNumber;
    private String departmentId;
    private String courseName;
    private String currentYear;
    private String course;
    private int nationalIdentificationNumber;
    private String phoneNumber;
    private String gender;
    private String password;
    private String roleId;
}
