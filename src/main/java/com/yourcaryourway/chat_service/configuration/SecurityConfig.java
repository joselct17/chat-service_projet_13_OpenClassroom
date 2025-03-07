package com.yourcaryourway.chat_service.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Désactiver CSRF pour WebSocket
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/index.html", "/ws/**", "/chat/**", "/css/**", "/js/**", "/images/**").permitAll() // Autoriser fichiers statiques
                        .anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults()) // Activer login par défaut
                .logout(logout -> logout.logoutUrl("/logout"));

        return http.build();
    }
}


