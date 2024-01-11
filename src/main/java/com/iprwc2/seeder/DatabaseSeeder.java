package com.iprwc2.seeder;

import com.iprwc2.model.Rights;
import com.iprwc2.model.User;
import com.iprwc2.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DatabaseSeeder {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            String adminUsername = "admin";
            String adminPassword = "admin123";
            String postalCode =  "1234AB";
            String street = "straat 48";
            String city = "City";

            if (userRepository.findByEmail(adminUsername).isEmpty()) {
                User adminUser = User.builder()
                        .email(adminUsername)
                        .password(passwordEncoder.encode(adminPassword))
                        .name("Admin")
                        .rights(Rights.ADMIN)
                        .postalCode(postalCode)
                        .street(street)
                        .city(city)
                        .build();
                userRepository.save(adminUser);
                System.out.println("Admin account created");
            } else {
                System.out.println("Admin account already exists");
            }

            String regularUsername = "user"; // Regular user username
            String regularPassword = "user123"; // Regular user password

            if (userRepository.findByEmail(regularUsername).isEmpty()) {
                User regularUser = User.builder()
                        .email(regularUsername)
                        .password(passwordEncoder.encode(regularPassword))
                        .name("Regular User")
                        .rights(Rights.USER)
                        .postalCode(postalCode)
                        .street(street)
                        .city(city)
                        .build();
                userRepository.save(regularUser);
                System.out.println("Regular user account created");
            } else {
                System.out.println("Regular user account already exists");
            }
        };
    }
}

