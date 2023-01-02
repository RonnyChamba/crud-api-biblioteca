package com.ideas.org.crud.security;

import com.ideas.org.crud.security.jwt.JwtAuthFilter;
import com.ideas.org.crud.security.jwt.JwtEntryPoint;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    private final UserDetailsService userDetailsService;

    private final JwtEntryPoint jwtEntryPoint;

    private final PasswordEncoder passwordEncoder;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http.csrf().disable()
                .authorizeRequests()
                .antMatchers(("/api/v1/auth/**"))
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtEntryPoint)
                .and()
                .sessionManagement()
                // Configurar para que no se guarde la sesion del usuario autenticado, sin estado.
                /**
                 * Si no se configura eso, despues de la primera vez que se autentique al usuario
                 * se quedara guarda la autencion, x ello hay que evitar guardar la autenrticacion
                 */
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider())
                // Inicamos a spring que llame al filtro de jwt antes de que se llame filtro de
                // autenticacion se usuario y password
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();


    }

    @Bean
    public AuthenticationProvider authenticationProvider() {

        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();

    }



}
