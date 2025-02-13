package com.softideas.bursary.auth.microservice.application.handlers.user;

import com.softideas.bursary.auth.microservice.application.commands.user.UpdateUserCommand;
import com.softideas.bursary.auth.microservice.application.handlers.IRequestHandler;
import com.softideas.bursary.auth.microservice.application.services.CustomUserDetailsService;
import com.softideas.bursary.auth.microservice.domain.models.DTO.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserCommandHandler implements IRequestHandler<UpdateUserCommand, UserResponseDTO> {

    private final CustomUserDetailsService userService;

    @Autowired
    public UpdateUserCommandHandler(CustomUserDetailsService userService) {

        this.userService = userService;
    }

    @Override
    public UserResponseDTO handle(UpdateUserCommand command) {

        return userService.updateUser(command);
    }

    @Override
    public Class<?> getCommandType() {
        return UpdateUserCommand.class;
    }
}
