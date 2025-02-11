package com.softideas.bursary.auth.microservice.domain.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id", length = 36, nullable = false, unique = true)
    private UUID userId;

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "middle_name", length = 50)
    private String middleName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Column(name = "email_address", length = 100, nullable = false, unique = true)
    private String emailAddress;

    @Column(name = "admission_number", length = 20, unique = true)
    private String admissionNumber;

    @Column(name = "department_id", length = 36, nullable = false)
    private String departmentId;

    @Column(name = "course_name", length = 100)
    private String courseName;

    @Column(name = "current_year", length = 10)
    private String currentYear;

    @Column(name = "national_identification_number", length = 15, nullable = false, unique = true)
    private int nationalIdentificationNumber;

    @Column(name = "phone_number", length = 15, nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "gender", length = 10, nullable = false)
    private String gender;

    @Column(name = "password", length = 255, nullable = false)
    private String password;


    @Column(name = "role", length =10, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Column(name = "is_Verified",nullable = false)
    private boolean isVerified;

    public User(
            String firstName,
            String middleName,
            String lastName,
            String emailAddress,
            String admissionNumber,
            String departmentId,
            String courseName,
            String currentYear,
            int nationalIdentificationNumber,
            String phoneNumber,
            String gender,
            String password,
            Role role,
            boolean isVerified)
    {
        this.userId = UUID.randomUUID();
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.admissionNumber = admissionNumber;
        this.departmentId = departmentId;
        this.courseName = courseName;
        this.currentYear = currentYear;
        this.nationalIdentificationNumber = nationalIdentificationNumber;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.password = password;
        this.role = role;
        this.isVerified=isVerified;
    }
    public static User addNewUser(
            String firstName,
            String middleName,
            String lastName,
            String emailAddress,
            String admissionNumber,
            String departmentId,
            String courseName,
            String currentYear,
            int nationalIdentificationNumber,
            String phoneNumber,
            String gender,
            String password,
            Role role,
            Boolean isVerified) {

        return new User(
                firstName,
                middleName,
                lastName,
                emailAddress,
                admissionNumber,
                departmentId,
                courseName,
                currentYear,
                nationalIdentificationNumber,
                phoneNumber,
                gender,
                password,
                role,
                isVerified
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(getRole().name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }
    @Override
    public boolean isEnabled() {

        return getIsVerified();
    }
    @Override
    public String getUsername() {

        return getFirstName().concat(" ")+ getLastName() ;
    }
}
