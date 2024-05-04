package com.ali.java.todo.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final MyUserDetailService myUserDetailService;

    @Bean
    public UserDetailsService userDetailsService() {

        return myUserDetailService ;
//        UserDetails user = User.builder()
//                .username("user")
//                .password(passwordEncoder.encode("user"))
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.withUsername("admin")
//                .password(passwordEncoder.encode("admin"))
//                .roles("USER", "ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, admin);
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(myUserDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return  http.authorizeHttpRequests(request -> {
                        request.requestMatchers("/api/v1/home","api/v1/user/register").permitAll();
//                        request.requestMatchers("api/v1/user/**").hasRole("USER");
                    request.requestMatchers("api/v1/admin/**").hasRole("ADMIN");
                    request.anyRequest().authenticated();
                   })
                .formLogin(form -> form
                        .successHandler(new AuthenticationSuccessHandler())
                        .permitAll())
                .csrf(c -> c.disable())   //without disabling csrf, post requests won't work
               .build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}


