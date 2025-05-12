package grsu.by.service;

import grsu.by.dto.authenticationDto.AuthResponse;
import grsu.by.dto.authenticationDto.SignInRequest;
import grsu.by.dto.authenticationDto.SignUpRequest;

public interface AuthenticationService {

    AuthResponse authenticate(SignInRequest request);
    AuthResponse register(SignUpRequest request);

}
