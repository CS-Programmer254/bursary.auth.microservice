//package com.softideas.bursary.auth.microservice.infrastructure.services;
//
//import com.softideas.bursary.auth.microservice.application.commands.user.CreateUserCommand;
//import com.softideas.bursary.auth.microservice.application.handlers.IRequestHandler;
//import com.softideas.bursary.auth.microservice.application.handlers.user.CreateUserCommandHandler;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Component
//public class MediatorImplementation1 implements IMediator {
//    private final Map<Class<?>, IRequestHandler<?, ?>> handlers = new HashMap<>();
//
//    @Autowired
//    public MediatorImplementation1(CreateUserCommandHandler createUserCommandHandler) {
//
//        // Register command handlers
//
//        handlers.put(CreateUserCommand.class, createUserCommandHandler);
//
//        // Register other handlers as needed
//    }
//
//    @SuppressWarnings("unchecked")
//    @Override
//    public <R, T> R send(T command) {
//        IRequestHandler<T, R> handler = (IRequestHandler<T, R>) handlers.get(command.getClass());
//
//        if (handler != null) {
//            return handler.handle(command);
//        }
//
//        throw new IllegalArgumentException("Command handler not found for command: " + command.getClass());
//    }
//}
