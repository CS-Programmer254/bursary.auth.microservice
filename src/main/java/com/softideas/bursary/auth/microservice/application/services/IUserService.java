package com.softideas.bursary.auth.microservice.application.services;

import com.softideas.bursary.auth.microservice.application.commands.user.CreateUserCommand;
import com.softideas.bursary.auth.microservice.application.commands.user.UpdateUserCommand;
import com.softideas.bursary.auth.microservice.domain.models.User;

import java.util.Optional;

public interface IUserService {

    User createUser(CreateUserCommand createUserCommand);

    Optional<User> updateUser(String userId, UpdateUserCommand updateUserCommand);

    Optional<User> getUserById(String userId);

    Optional<User> getUserByEmailAddress(String emailAddress);

    void deleteUser(String userId);
}
