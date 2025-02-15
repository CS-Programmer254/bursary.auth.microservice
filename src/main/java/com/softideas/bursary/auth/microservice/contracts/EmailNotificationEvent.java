package com.softideas.bursary.auth.microservice.contracts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailNotificationEvent implements Serializable {

    private String emailTo;

    private String subject;

    private String message;

}

