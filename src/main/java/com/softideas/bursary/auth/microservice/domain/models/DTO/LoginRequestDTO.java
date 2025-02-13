package com.softideas.bursary.auth.microservice.domain.models.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class LoginRequestDTO {

    private String phoneNumber;
    private String password;
}
