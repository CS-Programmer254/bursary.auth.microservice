package com.softideas.bursary.auth.microservice.domain.models.DTO;

import com.softideas.bursary.auth.microservice.domain.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {

    private String token;
    private String username;
    private String phoneNumber;
    private String emailAddress;
    private String nationalIdentificationNumber;
    private Role role;



}
