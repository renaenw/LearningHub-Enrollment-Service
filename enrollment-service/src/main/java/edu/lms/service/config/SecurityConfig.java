package edu.lms.service.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/actuator/health").permitAll()

                // Only administrators can create, update, or delete terms and courses
                .requestMatchers(HttpMethod.POST, "/api/enrollment/terms/**",
                    "/api/enrollment/courses/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/enrollment/terms/**",
                    "/api/enrollment/courses/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/enrollment/terms/**",
                    "/api/enrollment/courses/**").hasRole("ADMIN")

                // Enrollment changes require an authenticated user.
                // Student ownership checks will be added separately.
                .requestMatchers(HttpMethod.POST, "/api/enrollment")
                    .hasAnyRole("STUDENT", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/enrollment/*")
                    .hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/enrollment/**")
                    .hasRole("ADMIN")

                // All remaining API requests require authentication
                .anyRequest().authenticated()
            )
            .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt ->
                jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())
            ));

        return http.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();

        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            List<GrantedAuthority> authorities = new ArrayList<>();

            Map<String, Object> realmAccess = jwt.getClaimAsMap("realm_access");

            if (realmAccess != null && realmAccess.get("roles") instanceof List<?> roles) {
                for (Object role : roles) {
                    authorities.add(new SimpleGrantedAuthority(
                        "ROLE_" + role.toString().toUpperCase()
                    ));
                }
            }

            return authorities;
        });

        return converter;
    }
}
