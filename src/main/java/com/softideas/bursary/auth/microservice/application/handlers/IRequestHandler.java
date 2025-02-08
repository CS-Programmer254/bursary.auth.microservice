package com.softideas.bursary.auth.microservice.application.handlers;

public interface IRequestHandler<T, R> {

    R handle(T command);

    Class<?> getCommandType();

}

