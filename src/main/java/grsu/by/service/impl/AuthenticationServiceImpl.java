package grsu.by.service.impl;

import grsu.by.entity.UserRole;
import lombok.RequiredArgsConstructor;
import grsu.by.dto.authenticationDto.AuthResponse;
import grsu.by.dto.authenticationDto.SignInRequest;
import grsu.by.dto.authenticationDto.SignUpRequest;
import grsu.by.entity.Credentials;
import grsu.by.entity.Role;
import grsu.by.entity.User;
import grsu.by.exception.EntityNotFoundException;
import grsu.by.repository.CredentialsRepository;
import grsu.by.repository.RoleRepository;
import grsu.by.repository.UserRepository;
import grsu.by.security.UserDetailsImpl;
import grsu.by.security.jwt.JwtService;
import grsu.by.service.AuthenticationService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {

    private final CredentialsRepository credentialsRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper mapper;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final String DEFAULT_ROLE_NAME = "USER";

    @Override
    public AuthResponse authenticate(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new EntityNotFoundException(
                        Map.of("class", User.class.getName(),
                                "email", request.getEmail(),
                                "date", Instant.now().toString()
                        )
                )
        );
        String jwtToken = jwtService.generateToken(new UserDetailsImpl(user));

        return AuthResponse.builder().token(jwtToken).build();
    }

    @Override
    public AuthResponse register(SignUpRequest request) {
        User user = mapper.map(request.getUserCreationDto(), User.class);
        setDefaultRole(user);
        User createdUser = userRepository.save(user);

        Credentials credentials = mapper.map(request.getCredentialsDto(), Credentials.class);
        credentials.setPassword(passwordEncoder.encode(credentials.getPassword()));
        credentials.setUser(createdUser);

        Credentials createdCredentials = credentialsRepository.save(credentials);

        createdUser.setCredentials(createdCredentials);
        String jwtToken = jwtService.generateToken(new UserDetailsImpl(createdUser));

        return AuthResponse.builder().token(jwtToken).build();
    }

    private void setDefaultRole(User user){
        Role defaultRole = roleRepository.findByName(DEFAULT_ROLE_NAME).orElseThrow(
                () -> new EntityNotFoundException(
                        Map.of(
                                "class", Role.class.getName(),
                                "name", DEFAULT_ROLE_NAME,
                                "date", Instant.now().toString()
                        )
                )
        );
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(defaultRole);

        user.setUserRoles(Set.of(userRole));
    }
}
