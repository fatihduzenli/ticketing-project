package com.cydeo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration // when we have a bunch of config methods (bigger concept), we use separate config class
// whenever we need just a single bean, we can write it in the runner class
public class SecurityConfig {


//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder encoder){
//        List<UserDetails>userList= new ArrayList<>();

//        userList.add(new User("mike", encoder.encode("password"), Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"))));
//        userList.add(new User("fatih", encoder.encode("password"), Arrays.asList(new SimpleGrantedAuthority("ROLE_MANAGER"))));

//        return new InMemoryUserDetailsManager(userList);
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{


        return http
                .authorizeRequests()
//                .antMatchers("/user/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasAuthority("Admin")
//                .antMatchers("/project/**").hasAuthority("Manager")
//                .antMatchers("/task/employee/**").hasAuthority("Employee")
//                .antMatchers("/task/**").hasAuthority("Manager")
//                .antMatchers("/task/**").hasAnyRole("EMPLOYEE","ADMIN")
//                .antMatchers("/task/**").hasAuthority("ROLE_EMPLOYEE")
                .antMatchers(
                        "/",
                        "/login",
                        "/fragments/**",
                        "/assets/**",
                        "/images/**"
                ).permitAll()
                .anyRequest().authenticated()
                .and()
//                .httpBasic()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/welcome")
                .failureUrl("/login?error=true")
                .permitAll()
                .and().build();

    }
    
}
