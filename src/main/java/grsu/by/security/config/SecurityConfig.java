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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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

        http.authorizeHttpRequests(request -> request.requestMatchers(new AntPathRequestMatcher("/api/v1/auth/**")).permitAll());
        http.authorizeHttpRequests(request -> request.requestMatchers(new AntPathRequestMatcher("/api/v1/cities/**")).hasAuthority("ADMIN"));
        http.authorizeHttpRequests(request -> request.requestMatchers(new AntPathRequestMatcher("/api/v1/deliveries/types/**")).hasAuthority("ADMIN"));
        http.authorizeHttpRequests(request -> request.requestMatchers(new AntPathRequestMatcher("/api/v1/orders/statuses/**")).hasAuthority("ADMIN"));
        http.authorizeHttpRequests(request -> request.requestMatchers(new AntPathRequestMatcher("/api/v1/payments/types/**")).hasAuthority("ADMIN"));
        http.authorizeHttpRequests(request -> request.requestMatchers(new AntPathRequestMatcher("/api/v1/food/categories/**")).hasAuthority("ADMIN"));
        http.authorizeHttpRequests(request -> request.requestMatchers(new AntPathRequestMatcher("/api/v1/roles/**")).hasAuthority("ADMIN"));
        http.authorizeHttpRequests(request -> request.anyRequest().authenticated());

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
