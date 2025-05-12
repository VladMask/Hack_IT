package grsu.by.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import grsu.by.dto.authenticationDto.AuthResponse;
import grsu.by.dto.authenticationDto.SignInRequest;
import grsu.by.dto.authenticationDto.SignUpRequest;
import grsu.by.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "AuthenticationController", description = "The Authentication API")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Operation(summary = "Authenticate user", description = "Authenticates user credentials and returns JWT token")
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse authenticate(@RequestBody @Valid SignInRequest request) {
        return authenticationService.authenticate(request);
    }

    @Operation(summary = "Register user", description = "Registers a new user and returns JWT token")
    @PostMapping("/reg")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse registration(@RequestBody @Valid SignUpRequest request) {
        return authenticationService.register(request);
    }
}
