package com.example.bookingRoom.configuration;

import com.example.bookingRoom.entity.User;
import com.example.bookingRoom.repository.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppInitConfig {
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(7);

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository){
        return args -> {
            if(userRepository.findByNameLogin("admin").isEmpty()){
                String role = "ADMIN";

                User user = User.builder()
                        .nameLogin("admin")
                        .password(passwordEncoder.encode("admin"))
//                        .role(role)
                        .build();

                userRepository.save(user);
            }
        };
    }
}
