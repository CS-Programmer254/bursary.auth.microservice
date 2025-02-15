package com.softideas.bursary.auth.microservice.contracts;

import com.softideas.bursary.auth.microservice.domain.models.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreatedEvent implements Serializable {

    private String firstName;

    private String emailAddress;

    private String phoneNumber;

    private String otp;

    private LocalDateTime createdAt = LocalDateTime.now();

    public UserCreatedEvent(@NotNull String firstName, @NotNull @Email String emailAddress, @NotNull String phoneNumber, String otp) {

        this.firstName=firstName;

        this.emailAddress=emailAddress;

        this.phoneNumber=phoneNumber;

        this.otp =otp;

    }
}
