package com.softideas.bursary.auth.microservice.config;

import net.devh.boot.grpc.server.config.GrpcServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcServerConfig {

    @Bean
    public GrpcServerProperties grpcServerProperties() {

        GrpcServerProperties properties = new GrpcServerProperties();

        properties.setPort(9090);

        return properties;
    }
}
