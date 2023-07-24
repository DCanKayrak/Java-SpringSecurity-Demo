package com.dcankayrak.springSecurityDemo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.dcankayrak.springSecurityDemo.entities.enums.Permission.*;
import static com.dcankayrak.springSecurityDemo.entities.enums.Role.*;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration{
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/welcome/**").hasAnyAuthority("ADMIN")

//                .requestMatchers("/admin/**").hasRole(ADMIN.name())
//
//                .requestMatchers(GET,"/admin/**").hasAuthority(ADMIN_READ.name())
//                .requestMatchers(POST,"/admin/**").hasAuthority(ADMIN_CREATE.name())
//                .requestMatchers(PUT,"/admin/**").hasAuthority(ADMIN_UPDATE.name())
//                .requestMatchers(DELETE,"/admin/**").hasAuthority(ADMIN_DELETE.name())

                .requestMatchers("/management/**").hasAnyRole(ADMIN.name(),MANAGER.name())

                .requestMatchers(GET,"/management/**").hasAnyAuthority(ADMIN_READ.name(),MANAGER_READ.name())
                .requestMatchers(POST,"/management/**").hasAnyAuthority(ADMIN_CREATE.name(),MANAGER_CREATE.name())
                .requestMatchers(PUT,"/management/**").hasAnyAuthority(ADMIN_UPDATE.name(),MANAGER_UPDATE.name())
                .requestMatchers(DELETE,"/management/**").hasAnyAuthority(ADMIN_DELETE.name(),MANAGER_DELETE.name())


                .requestMatchers("/welcome/**").hasAnyAuthority("ADMIN")
                .requestMatchers("/users/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(this.authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).build();
    }
}
