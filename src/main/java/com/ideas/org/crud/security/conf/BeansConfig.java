package com.ideas.org.crud.security.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeansConfig {


    @Bean
    public PasswordEncoder passwordEncoder() {

        // Para trbajar el mdo desarrollo, lo manejoa como texto plano, no se recomienda
        //NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }

}
