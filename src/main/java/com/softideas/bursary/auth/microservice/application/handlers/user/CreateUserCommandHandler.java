package com.softideas.bursary.auth.microservice.application.handlers.user;

import com.softideas.bursary.auth.microservice.application.commands.user.CreateUserCommand;
import com.softideas.bursary.auth.microservice.application.handlers.IRequestHandler;
import com.softideas.bursary.auth.microservice.application.services.IUserService;
import com.softideas.bursary.auth.microservice.domain.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateUserCommandHandler implements IRequestHandler<CreateUserCommand, User> {

    private final IUserService userService;

    @Autowired
    public CreateUserCommandHandler(IUserService userService) {

        this.userService = userService;
    }
    @Override
    public User handle(CreateUserCommand command) {

        return userService.createUser(command);

    }

    @Override
    public Class<?> getCommandType() {
        return CreateUserCommand.class;
    }
}
