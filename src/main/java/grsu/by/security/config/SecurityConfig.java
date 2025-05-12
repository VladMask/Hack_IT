package grsu.by.security.config;

import lombok.RequiredArgsConstructor;
import grsu.by.security.jwt.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authorizeHttpRequests(request -> request
                .requestMatchers("/api/v1/auth/**").permitAll()
                .requestMatchers("/swagger-ui/**").permitAll()
                .requestMatchers("/v3/api-docs/**").permitAll()

                .requestMatchers("/api/v1/roles/**").hasAuthority("ADMIN")

                .requestMatchers("/api/v1/scores/**").hasAnyAuthority("ADMIN", "JUDGE")
                .requestMatchers("/api/v1/feedback/**").hasAnyAuthority("ADMIN", "JUDGE", "HACKATHON_CREATOR")
                .requestMatchers("/api/v1/hackathons/**").hasAnyAuthority("ADMIN", "HACKATHON_CREATOR")
                .requestMatchers("/api/v1/notifications/**").hasAnyAuthority("ADMIN", "HACKATHON_CREATOR")
                .requestMatchers("/api/v1/prizes/**").hasAnyAuthority("ADMIN", "HACKATHON_CREATOR", "USER")
                .requestMatchers("/api/v1/solutions/**").hasAnyAuthority("ADMIN", "HACKATHON_CREATOR", "USER")
                .requestMatchers("/api/v1/teams/**").hasAnyAuthority("ADMIN", "HACKATHON_CREATOR", "USER")
                .requestMatchers("/api/v1/users/**").authenticated()

                .anyRequest().authenticated()
        );


        http.httpBasic(AbstractHttpConfigurer::disable);

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
