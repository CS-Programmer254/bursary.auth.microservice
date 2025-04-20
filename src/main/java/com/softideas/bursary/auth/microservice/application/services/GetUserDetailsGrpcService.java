package com.softideas.bursary.auth.microservice.application.services;

import com.softideas.bursary.auth.microservice.grpc.AuthServiceGrpc;
import com.softideas.bursary.auth.microservice.grpc.UserRequest;
import com.softideas.bursary.auth.microservice.grpc.UserResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;


@GrpcService
public class GetUserDetailsGrpcService extends AuthServiceGrpc.AuthServiceImplBase {

    @Override
    public void getUser(UserRequest request, StreamObserver<UserResponse> responseObserver) {
       // String phoneNumber = request.getPhoneNumber();
        UserResponse response = UserResponse.newBuilder()
                .setUserId(request.getPhoneNumber())
                .setFirstName("John")
                .setMiddleName("Michael")
                .setLastName("Doe")
                .setEmailAddress("john.doe@example.com")
                .setAdmissionNumber("ADM12345")
                .setNationalIdentificationNumber("123456789")
                .setSchoolName("Maseno University")
                .setDepartmentName("Computer Science")
                .setEnrolledCourse("BSc. Computer Science")
                .setYearOfStudy("3")
                .setSponsorshipType("Government Sponsored")
                .setCounty("Kisumu")
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
