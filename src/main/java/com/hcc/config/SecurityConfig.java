package com.hcc.config;

import com.hcc.services.UserDetailServiceImpl;
import com.hcc.utils.CustomPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailServiceImpl userDetailServiceImpl;

    @Autowired
    private CustomPasswordEncoder customPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Configuring authentication manager with custom user details service and password encoder
        auth.userDetailsService(userDetailServiceImpl).passwordEncoder(customPasswordEncoder.getPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()  // Disabling CSRF for simplicity; consider enabling for security in production
                .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()  // Public access to authentication endpoints
                .antMatchers("/api/assignments/**").hasRole("LEARNER")  // Only learners can access assignments
                .antMatchers("/api/review/**").hasRole("REVIEWER")  // Only reviewers can access review endpoints
                .anyRequest().authenticated()  // All other requests require authentication
                .and()
                .formLogin().permitAll()  // Enabling form-based login
                .and()
                .logout().permitAll();  // Allowing everyone to access logout
    }
}
