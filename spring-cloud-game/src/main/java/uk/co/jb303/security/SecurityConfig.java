package uk.co.jb303.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, UserUuidFilter userUuidFilter) throws Exception {
        http
            // Disable CSRF because authentication is checked per request via parameter
            .csrf(csrf -> csrf.disable()) 
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.GET, "/").permitAll()
                .requestMatchers(HttpMethod.POST, "/").authenticated()
                .anyRequest().authenticated()
            )
            // Inject the custom UUID verification filter before the standard authentication step
            .addFilterBefore(userUuidFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}

