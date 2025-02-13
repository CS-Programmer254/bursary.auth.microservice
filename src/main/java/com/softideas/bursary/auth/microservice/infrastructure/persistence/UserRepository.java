package com.softideas.bursary.auth.microservice.infrastructure.persistence;

import com.softideas.bursary.auth.microservice.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{

    Optional<User> findByPhoneNumber(String phoneNumber);

    Optional<User> findByNationalIdentificationNumber(String nationalIdentificationNumber);

    Optional<User> findByEmailAddress(String emailAddress);

    List<User> findByGender(String gender);

    List<User> findByDepartmentId(String departmentId);
}
