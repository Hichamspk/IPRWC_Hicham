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
            // Admin User Setup
            String adminUsername = "admin"; // Admin username
            String adminPassword = "admin123"; // Admin password

            if (userRepository.findByEmail(adminUsername).isEmpty()) {
                User adminUser = User.builder()
                        .email(adminUsername)
                        .password(passwordEncoder.encode(adminPassword))
                        .name("Admin")
                        .rights(Rights.ADMIN)
                        .build();
                userRepository.save(adminUser);
                System.out.println("Admin account created");
            } else {
                System.out.println("Admin account already exists");
            }

            // Regular User Setup
            String regularUsername = "user"; // Regular user username
            String regularPassword = "user123"; // Regular user password

            if (userRepository.findByEmail(regularUsername).isEmpty()) {
                User regularUser = User.builder()
                        .email(regularUsername)
                        .password(passwordEncoder.encode(regularPassword))
                        .name("Regular User")
                        .rights(Rights.USER) // Assuming 'USER' is a valid enum for regular users
                        .build();
                userRepository.save(regularUser);
                System.out.println("Regular user account created");
            } else {
                System.out.println("Regular user account already exists");
            }
        };
    }
}

