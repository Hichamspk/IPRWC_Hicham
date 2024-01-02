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
            String adminUsername = "admin"; // Voer hier de gewenste gebruikersnaam in
            String adminPassword = "admin123"; // Voer hier het gewenste wachtwoord in

            if (userRepository.findByUsername(adminUsername).isEmpty()) {
                User adminUser = User.builder()
                        .username(adminUsername)
                        .password(passwordEncoder.encode(adminPassword))
                        .name("Admin") // Of een andere naam naar keuze
                        .rights(Rights.ADMIN) // Zorg ervoor dat 'Rights' de juiste enum bevat
                        .build();
                userRepository.save(adminUser);
                System.out.println("Admin account created");
            } else {
                System.out.println("Admin account already exists");
            }
        };
    }
}
