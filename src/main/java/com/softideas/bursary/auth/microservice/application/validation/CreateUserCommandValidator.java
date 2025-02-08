//package com.softideas.bursary.auth.microservice.application.validation;
//
//import com.softideas.bursary.auth.microservice.application.commands.user.CreateUserCommand;
//import org.apache.kafka.common.security.oauthbearer.internals.secured.ValidateException;
//
//public class CreateUserCommandValidator implements ICommandValidator<CreateUserCommand>{
//    @Override
//    public void validate (CreateUserCommand createUserCommand) throws ValidateException{
//        if(createUserCommand.getFirstName() == null || createUserCommand.getFirstName().isEmpty()){
//            throw new ValidateException("User's name cannot be empty");
//        }
//        if(createUserCommand.getEmail() == null || createUserCommand.getEmail().isEmpty()){
//            throw new ValidateException("User's email cannot be empty");
//        }
//        if(createUserCommand.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\\\.[a-zA-Z]{2,}$\n")){
//            throw new ValidateException("Invalid email address");
//        }
//
//    }
//
//}
