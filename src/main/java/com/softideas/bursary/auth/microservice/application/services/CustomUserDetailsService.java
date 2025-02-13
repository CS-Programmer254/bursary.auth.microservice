package com.softideas.bursary.auth.microservice.application.services;

import com.softideas.bursary.auth.microservice.application.commands.user.CreateUserCommand;
import com.softideas.bursary.auth.microservice.application.commands.user.UpdateUserCommand;
import com.softideas.bursary.auth.microservice.domain.models.DTO.UserResponseDTO;
import com.softideas.bursary.auth.microservice.domain.models.User;
import com.softideas.bursary.auth.microservice.infrastructure.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private   JwtService jwtService;

    @Autowired
    private   OtpService otpService;

    private final PasswordEncoder passwordEncoder;

    public CustomUserDetailsService(PasswordEncoder passwordEncoder) {

        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {

        return userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with phone number: " + phoneNumber));
    }

    public String generateAuthToken(UserDetails userDetails){

        return jwtService.generateAuthToken(userDetails);
    }

    public boolean validateAuthToken(String token, UserDetails userDetails){

      return  jwtService.validateToken(token,userDetails);
    }

    public UserResponseDTO createUser(CreateUserCommand createUserCommand) {

        String hashedPassword = passwordEncoder.encode(createUserCommand.getPassword());

        User user = new User(
                createUserCommand.getFirstName(),
                createUserCommand.getMiddleName(),
                createUserCommand.getLastName(),
                createUserCommand.getEmailAddress(),
                createUserCommand.getAdmissionNumber(),
                createUserCommand.getDepartmentId(),
                createUserCommand.getCourseName(),
                createUserCommand.getCurrentYear(),
                createUserCommand.getNationalIdentificationNumber(),
                createUserCommand.getPhoneNumber(),
                createUserCommand.getGender(),
                hashedPassword,
                createUserCommand.getRole(),
                false
        );

        user.setOtp(otpService.generateOtp());
        user.setOtpExpiryTime();

        User savedUser = userRepository.save(user);

        return new UserResponseDTO(
                savedUser.getFirstName(),
                savedUser.getMiddleName(),
                savedUser.getLastName(),
                savedUser.getEmailAddress(),
                savedUser.getAdmissionNumber(),
                savedUser.getCourseName(),
                savedUser.getCurrentYear(),
                savedUser.getGender(),
                savedUser.getRole(),
                savedUser.getIsVerified()
        );
    }

    public boolean verifyOtp(String phoneNumber, String otp) {

        return otpService.verifyAndConsumeOtp(phoneNumber, otp);
    }


    public UserResponseDTO updateUser(UpdateUserCommand updateUserCommand) {

        User existingUser = userRepository.findById(updateUserCommand.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + updateUserCommand.getUserId()));

        if (updateUserCommand.getFirstName() != null && !updateUserCommand.getFirstName().isEmpty()) {
            existingUser.setFirstName(updateUserCommand.getFirstName());
        }
        if (updateUserCommand.getMiddleName() != null && !updateUserCommand.getMiddleName().isEmpty()) {
            existingUser.setMiddleName(updateUserCommand.getMiddleName());
        }
        if (updateUserCommand.getLastName() != null && !updateUserCommand.getLastName().isEmpty()) {
            existingUser.setLastName(updateUserCommand.getLastName());
        }
        if (updateUserCommand.getEmailAddress() != null && !updateUserCommand.getEmailAddress().isEmpty()) {
            existingUser.setEmailAddress(updateUserCommand.getEmailAddress());
        }
        if (updateUserCommand.getAdmissionNumber() != null && !updateUserCommand.getAdmissionNumber().isEmpty()) {
            existingUser.setAdmissionNumber(updateUserCommand.getAdmissionNumber());
        }
        if (updateUserCommand.getDepartmentId() != null && !updateUserCommand.getDepartmentId().isEmpty()) {
            existingUser.setDepartmentId(updateUserCommand.getDepartmentId());
        }
        if (updateUserCommand.getCourseName() != null && !updateUserCommand.getCourseName().isEmpty()) {
            existingUser.setCourseName(updateUserCommand.getCourseName());
        }
        if (updateUserCommand.getCurrentYear() != null && !updateUserCommand.getCurrentYear().isEmpty()) {
            existingUser.setCurrentYear(updateUserCommand.getCurrentYear());
        }
        if (updateUserCommand.getNationalIdentificationNumber() != null && !updateUserCommand.getNationalIdentificationNumber().isEmpty()) {
            existingUser.setNationalIdentificationNumber(updateUserCommand.getNationalIdentificationNumber());
        }

        if (updateUserCommand.getPhoneNumber() != null && !updateUserCommand.getPhoneNumber().isEmpty()) {
            existingUser.setPhoneNumber(updateUserCommand.getPhoneNumber());
        }
        if (updateUserCommand.getGender() != null && !updateUserCommand.getGender().isEmpty()) {
            existingUser.setGender(updateUserCommand.getGender());
        }
        if (updateUserCommand.getPassword() != null && !updateUserCommand.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(updateUserCommand.getPassword()));
        }
        if (updateUserCommand.getRole() != null) {
            existingUser.setRole(updateUserCommand.getRole());
        }

        User updatedUser = userRepository.save(existingUser);

        return new UserResponseDTO(
                updatedUser.getFirstName(),
                updatedUser.getMiddleName(),
                updatedUser.getLastName(),
                updatedUser.getEmailAddress(),
                updatedUser.getAdmissionNumber(),
                updatedUser.getCourseName(),
                updatedUser.getCurrentYear(),
                updatedUser.getGender(),
                updatedUser.getRole(),
                updatedUser.getIsVerified()
        );
    }

    public Optional<UserResponseDTO> getUserById(UUID userId) {
        return userRepository.findById(userId)
                .map(user -> new UserResponseDTO(
                        user.getFirstName(),
                        user.getMiddleName(),
                        user.getLastName(),
                        user.getEmailAddress(),
                        user.getAdmissionNumber(),
                        user.getCourseName(),
                        user.getCurrentYear(),
                        user.getGender(),
                        user.getRole(),
                        user.getIsVerified()
                ));
    }

    public Optional<UserResponseDTO> getUserByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber)
                .map(user -> new UserResponseDTO(
                        user.getFirstName(),
                        user.getMiddleName(),
                        user.getLastName(),
                        user.getEmailAddress(),
                        user.getAdmissionNumber(),
                        user.getCourseName(),
                        user.getCurrentYear(),
                        user.getGender(),
                        user.getRole(),
                        user.getIsVerified()
                ));
    }

    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }
}