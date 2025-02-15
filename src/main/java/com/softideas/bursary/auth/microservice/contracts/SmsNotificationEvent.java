package com.softideas.bursary.auth.microservice.contracts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsNotificationEvent implements Serializable {

    private String phoneNumber;

    private String message;

    private String status;

    private LocalDateTime sendDate;

    public SmsNotificationEvent(String phoneNumber, String message) {

        this.phoneNumber = phoneNumber;

        this.message = message;

        this.status = "PENDING";

        this.sendDate = LocalDateTime.now();

    }
}
