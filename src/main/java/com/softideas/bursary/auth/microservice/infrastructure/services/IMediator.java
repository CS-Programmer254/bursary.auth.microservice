package com.softideas.bursary.auth.microservice.infrastructure.services;

public interface IMediator {

    <R, T> R send(T command);
}
