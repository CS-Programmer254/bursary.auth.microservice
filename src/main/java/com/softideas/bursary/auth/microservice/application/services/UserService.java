package com.softideas.bursary.auth.microservice.application.services;

import com.softideas.bursary.auth.microservice.application.commands.user.CreateUserCommand;
import com.softideas.bursary.auth.microservice.application.commands.user.UpdateUserCommand;
import com.softideas.bursary.auth.microservice.domain.models.User;
import com.softideas.bursary.auth.microservice.infrastructure.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public User createUser(CreateUserCommand command) {


        User user = User.addNewUser(

                command.getFirstName(),
                command.getMiddleName(),
                command.getLastName(),
                command.getEmailAddress(),
                command.getAdmissionNumber(),
                command.getDepartmentId(),
                command.getCourseName(),
                command.getCurrentYear(),
                command.getNationalIdentificationNumber(),
                command.getPhoneNumber(),
                command.getGender(),
                command.getPassword(),
                command.getRoleId()
        );

        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(String userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> getUserByEmailAddress(String emailAddress) {
        return userRepository.findByEmailAddress(emailAddress);
    }

    @Transactional
    @Override
    public Optional<User> updateUser(String userId, UpdateUserCommand command) {

        return userRepository.findById(userId)
                .map(existingUser -> {
                    existingUser.setFirstName(command.getFirstName());
                    existingUser.setLastName(command.getLastName());
                    existingUser.setEmailAddress(command.getEmailAddress());
                    existingUser.setPhoneNumber(command.getPhoneNumber());
                    return Optional.of(userRepository.save(existingUser));
                })
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

    }

    @Transactional
    @Override
    public void deleteUser(String userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
        } else {
            throw new RuntimeException("User not found with ID: " + userId);
        }
    }
}
