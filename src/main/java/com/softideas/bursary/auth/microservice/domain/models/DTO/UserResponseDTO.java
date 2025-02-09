package com.softideas.bursary.auth.microservice.domain.models.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseDTO {
    private String firstName;
    private String middleName;
    private String lastName;
    private String admissionNumber;
    private String courseName;
    private String currentYear;
}
