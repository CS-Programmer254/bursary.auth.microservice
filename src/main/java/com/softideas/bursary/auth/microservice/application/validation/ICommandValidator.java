package com.softideas.bursary.auth.microservice.application.validation;

import org.apache.kafka.common.security.oauthbearer.internals.secured.ValidateException;

public interface ICommandValidator<T>{

    void validate(T command ) throws ValidateException;

}
