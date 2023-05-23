package com.example.demo.Security;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


import javax.sql.DataSource;

@Configuration
public class Databaseconnector {


    @Bean
    public UserDetailsManager userDetailsManager(@Qualifier("empdirDataSource") DataSource dataSource) {

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        // define query to retrieve a user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select user_id, pw, active from members where user_id=?");

        // define query to retrieve the authorities/roles by username
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select user_id, role from roles where user_id=?");

        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

         http.csrf(csrf -> csrf.disable());


                http.authorizeHttpRequests((authz) -> authz
                        .requestMatchers(HttpMethod.GET, "/api").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/*").hasRole("ADMIN")
                                .anyRequest().authenticated()
                        );

                http.httpBasic();
                return http.build();

//        http.authorizeHttpRequests(configurer ->
//                configurer
//                        .requestMatchers(HttpMethod.GET, "/api").hasRole("EMPLOYEE")
//                        .requestMatchers(HttpMethod.GET, "/api/**").hasRole("EMPLOYEE")
//                        .requestMatchers(HttpMethod.POST, "/api").hasRole("MANAGER")
//                        .requestMatchers(HttpMethod.PUT, "/api").hasRole("MANAGER")
//                        .requestMatchers(HttpMethod.DELETE, "/api/*").hasRole("ADMIN")
//        );
//
//        http.httpBasic();
//
//        // disable Cross Site Request Forgery (CSRF)
//        // in general, not required for stateless REST APIs that use POST, PUT, DELETE and/or PATCH
//        http.csrf().disable();
//
//        return http.build();

       // return http.build();

    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/list");
    }

}
