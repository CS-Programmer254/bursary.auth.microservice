package com.softideas.bursary.auth.microservice.application.services;

import com.softideas.bursary.auth.microservice.infrastructure.persistence.UserRepository;
import com.softideas.bursary.auth.microservice.domain.models.User;
import jakarta.persistence.OptimisticLockException;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

@Service
public class OtpService {

    @Autowired
    private UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(OtpService.class);

//    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int OTP_LENGTH = 6;
//    private static final SecureRandom SECURE_RANDOM = new SecureRandom();
//
//
//
//    public String generateOtp() {
//        StringBuilder otp = new StringBuilder(OTP_LENGTH);
//        for (int i = 0; i < OTP_LENGTH; i++) {
//            otp.append(CHARACTERS.charAt(SECURE_RANDOM.nextInt(CHARACTERS.length())));
//        }
//        System.out.println("Generated OTP: " + otp);
//        return otp.toString();
//    }

    public String generateOtp() {
        return RandomStringUtils.randomAlphanumeric(OTP_LENGTH);
    }

    @Retryable(value = {OptimisticLockException.class}, include = TimeoutException.class, exclude = IllegalArgumentException.class ,maxAttempts = 5, backoff = @Backoff(delay = 1000, multiplier = 2))
    @Transactional
    public boolean verifyAndConsumeOtp(String phoneNumber, String otp) {

        Optional<User> userOptional = userRepository.findByPhoneNumber(phoneNumber);

        if (userOptional.isEmpty()) {

            return false;

        }

        User user = userOptional.get();

        if (user.getOtp() != null && otp.equals(user.getOtp()) &&
                user.getOtpExpiryTime() != null &&
                LocalDateTime.now().isBefore(user.getOtpExpiryTime())) {

            user.setOtp(null);
            user.setOtpExpiryTime(null);

            userRepository.save(user);
            return true;
        }

        return false;
    }

}
