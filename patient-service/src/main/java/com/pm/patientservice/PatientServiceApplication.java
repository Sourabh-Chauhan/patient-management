package com.pm.patientservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.Map;
import java.util.TimeZone;

@SpringBootApplication
public class PatientServiceApplication {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

//        System.out.println("--- Starting Environment Variable Dump ---");
//        Map<String, String> envVariables = System.getenv();
//        for (Map.Entry<String, String> entry : envVariables.entrySet()) {
//            // NOTE: Be cautious with logging sensitive information like passwords or API keys.
//            System.out.println(entry.getKey() + ": " + entry.getValue());
//        }
//        System.out.println("--- Ending Environment Variable Dump ---");

        SpringApplication.run(PatientServiceApplication.class, args);
    }


//    @Bean
//    public CommandLineRunner logEnvironment(Environment environment) {
//        return args -> {
//            System.out.println("---------- Environment Variables and System Properties ----------");
//
//            // Log System Environment Variables
//            System.getenv().forEach((key, value) ->
//                    System.out.println(key + ": " + value)
//            );
//
//            // Log System Properties
//            System.getProperties().forEach((key, value) ->
//                    System.out.println(key + ": " + value)
//            );
//
//            System.out.println("Active Profiles: " + Arrays.toString(environment.getActiveProfiles()));
//
//            System.out.println("------------------------------------------------------------------");
//        };
//    }

}
