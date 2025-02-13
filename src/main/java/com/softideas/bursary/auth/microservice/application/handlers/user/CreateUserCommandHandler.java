package com.softideas.bursary.auth.microservice.application.handlers.user;

import com.softideas.bursary.auth.microservice.application.commands.user.CreateUserCommand;
import com.softideas.bursary.auth.microservice.application.handlers.IRequestHandler;
import com.softideas.bursary.auth.microservice.application.services.CustomUserDetailsService;
import com.softideas.bursary.auth.microservice.domain.models.DTO.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateUserCommandHandler implements IRequestHandler<CreateUserCommand, UserResponseDTO> {

    @Autowired
    private final CustomUserDetailsService userService;

    public CreateUserCommandHandler(CustomUserDetailsService userService) {

        this.userService = userService;
    }

    @Override
    public UserResponseDTO handle(CreateUserCommand command) {

        return userService.createUser(command);

    }

    @Override
    public Class<?> getCommandType() {
        return CreateUserCommand.class;
    }
}
