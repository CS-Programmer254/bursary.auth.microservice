package com.softideas.bursary.auth.microservice.infrastructure.services;
import com.softideas.bursary.auth.microservice.application.handlers.IRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MediatorImplementation implements IMediator {

    // ConcurrentHashMap ensures thread-safe access to handlers in a concurrent environment
    private final Map<Class<?>, IRequestHandler<?, ?>> handlers = new ConcurrentHashMap<>();

    @Autowired
    public MediatorImplementation(ApplicationContext applicationContext) {

        // Automatically register all IRequestHandler implementations in the Spring context
        Map<String, IRequestHandler> handlerBeans = applicationContext.getBeansOfType(IRequestHandler.class);

        handlerBeans.values().forEach(handler -> {
            // Register each handler using its command type (Class) as the key
            Class<?> commandType = handler.getCommandType();
            handlers.put(commandType, handler);
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public <R, T> R send(T command) {
        // Retrieve the handler based on the command class
        IRequestHandler<T, R> handler = (IRequestHandler<T, R>) handlers.get(command.getClass());

        // Check if the handler exists, if not throw a custom exception
        if (handler != null) {
            return handler.handle(command);
        }

        throw new IllegalArgumentException("Command handler not found for command: " + command.getClass());

    }
}
