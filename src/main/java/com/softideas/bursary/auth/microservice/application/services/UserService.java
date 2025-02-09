package com.softideas.bursary.auth.microservice.application.services;

import com.softideas.bursary.auth.microservice.application.commands.user.CreateUserCommand;
import com.softideas.bursary.auth.microservice.application.commands.user.UpdateUserCommand;
import com.softideas.bursary.auth.microservice.domain.models.DTO.UserResponseDTO;
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
    public UserResponseDTO createUser(CreateUserCommand command) {


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
                command.getRoleId(),
                false
        );

        User savedUser= userRepository.save(user);

        return new UserResponseDTO(
                savedUser.getFirstName(),
                savedUser.getMiddleName(),
                savedUser.getLastName(),
                savedUser.getAdmissionNumber(),
                savedUser.getCourseName(),
                savedUser.getCurrentYear()
        );
    }

    @Override
    public Optional<UserResponseDTO> getUserById(String userId) {

        return userRepository.findById(userId)

                .map(user -> new UserResponseDTO(
                        user.getFirstName(),
                        user.getMiddleName(),
                        user.getLastName(),
                        user.getAdmissionNumber(),
                        user.getCourseName(),
                        user.getCurrentYear()
                ));
    }

    @Override
    public Optional<UserResponseDTO> getUserByEmailAddress(String emailAddress) {
        return userRepository.findByEmailAddress(emailAddress)
                .map(user -> new UserResponseDTO(
                        user.getFirstName(),
                        user.getMiddleName(),
                        user.getLastName(),
                        user.getAdmissionNumber(),
                        user.getCourseName(),
                        user.getCurrentYear()
                ));
    }
    @Transactional
    @Override
    public UserResponseDTO updateUser(UpdateUserCommand command) {

        return userRepository.findById(command.getUserId())
                .map(existingUser -> {
                    existingUser.setFirstName(command.getFirstName());
                    existingUser.setMiddleName(command.getMiddleName());
                    existingUser.setLastName(command.getLastName());
                    existingUser.setEmailAddress(command.getEmailAddress());
                    existingUser.setAdmissionNumber(command.getAdmissionNumber());
                    existingUser.setDepartmentId(command.getDepartmentId());
                    existingUser.setCourseName(command.getCourseName());
                    existingUser.setCurrentYear(command.getCurrentYear());
                    existingUser.setNationalIdentificationNumber(command.getNationalIdentificationNumber());
                    existingUser.setPhoneNumber(command.getPhoneNumber());
                    existingUser.setGender(command.getGender());
                    existingUser.setPassword(command.getPassword());
                    existingUser.setRoleId(command.getRoleId());

                    User updatedUser = userRepository.save(existingUser);

                    return new UserResponseDTO(
                            updatedUser.getFirstName(),
                            updatedUser.getMiddleName(),
                            updatedUser.getLastName(),
                            updatedUser.getAdmissionNumber(),
                            updatedUser.getCourseName(),
                            updatedUser.getCurrentYear()
                    );
                })
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + command.getUserId()));
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
