package com.pm.patientservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Arrays;
import java.util.Map;
import java.util.TimeZone;

@SpringBootApplication
public class PatientServiceApplication {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(PatientServiceApplication.class, args);
    }


    @Bean
    public CommandLineRunner logEnvironment(Environment environment) {
        return args -> {
            System.out.println("---------- Environment Variables and System Properties ----------");
            System.out.println("Bootstrap Servers: " + bootstrapServers);

            // Log System Environment Variables
            System.getenv().forEach((key, value) -> System.out.println(key + ": " + value));

            // Log System Properties
            System.getProperties().forEach((key, value) -> System.out.println(key + ": " + value));

            System.out.println("Active Profiles: " + Arrays.toString(environment.getActiveProfiles()));

            System.out.println("------------------------------------------------------------------");
        };
    }


}
