package com.softideas.bursary.auth.microservice.domain.models.DTO;

import com.softideas.bursary.auth.microservice.domain.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseDTO {

    private String firstName;
    private String middleName;
    private String lastName;
    private String emailAddress;
    private String admissionNumber;
    private String courseName;
    private String currentYear;
    private String gender;
    private Role role;
    private Boolean isVerified;

}
