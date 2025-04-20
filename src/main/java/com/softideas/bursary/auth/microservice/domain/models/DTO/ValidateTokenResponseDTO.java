package com.softideas.bursary.auth.microservice.domain.models.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidateTokenResponseDTO {

    private String phoneNumber;

    private String message;


}
