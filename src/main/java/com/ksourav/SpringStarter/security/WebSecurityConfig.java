package com.ksourav.SpringStarter.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig {
   
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
        .formLogin(login -> login
        .loginPage("/login")
        .loginProcessingUrl("/login")
        .usernameParameter("email")
        .passwordParameter("password")
         .defaultSuccessUrl("/home",true)
        .failureUrl("/login?error")
         .permitAll()
       )
         .authorizeHttpRequests((authorize) -> authorize
        .requestMatchers("/assets/**","/resources/static/**", "/","/db-console/**","/db-console/*"
        ,"/register","/sample","/fragments/*").permitAll()
        .anyRequest().authenticated()
        )
        .logout(logout -> logout
        .logoutUrl("/logout")
        .logoutSuccessUrl("/login")
        .permitAll())
        .rememberMe((remember) -> remember
        .rememberMeParameter("remember-me")
        .alwaysRemember(true))
        .csrf(csrf -> csrf.disable())
        .headers(headers -> headers.frameOptions().disable());
        return http.build();     
    }     
    
    @Bean
    PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
