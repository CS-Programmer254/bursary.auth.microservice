package com.softideas.bursary.auth.microservice.application.services;

import com.softideas.bursary.auth.microservice.application.commands.user.CreateUserCommand;
import com.softideas.bursary.auth.microservice.application.commands.user.UpdateUserCommand;
import com.softideas.bursary.auth.microservice.domain.models.DTO.UserResponseDTO;
import com.softideas.bursary.auth.microservice.domain.models.User;

import java.util.Optional;

public interface IUserService {

    UserResponseDTO createUser(CreateUserCommand createUserCommand);

    UserResponseDTO updateUser(UpdateUserCommand updateUserCommand);

    Optional<UserResponseDTO> getUserById(String userId);

    Optional<UserResponseDTO> getUserByEmailAddress(String emailAddress);

    void deleteUser(String userId);
}
