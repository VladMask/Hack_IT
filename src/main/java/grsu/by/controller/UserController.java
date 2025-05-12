package grsu.by.controller;

import grsu.by.dto.userDto.UserBaseDto;
import grsu.by.dto.userDto.UserCreationDto;
import grsu.by.entity.UserTeamId;
import grsu.by.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@Tag(name = "UserController", description = "The User API")
public class UserController {

    private final UserService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserBaseDto create(@RequestBody @Valid UserCreationDto dto) {
        return service.create(dto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public UserBaseDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserBaseDto update(@PathVariable Long id, @RequestBody @Valid UserBaseDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (service.deleteById(id)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Entity deleted successfully");
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error has occurred");
        }
    }

    @PostMapping("/{userId}/roles")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> setUserRole(@PathVariable Long userId, @RequestParam String roleName){
        if (service.setUserRole(userId, roleName)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Role set successfully");
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error has occurred");
        }

    }

    @PostMapping("/{userId}/teams/{teamId}")
    public ResponseEntity<String> addUserToTeam(@PathVariable Long userId, @PathVariable Long teamId) {
        if (service.addUserToTeam(new UserTeamId(userId, teamId))) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("User added to team successfully");
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error has occurred");
        }
    }

    @DeleteMapping("/{userId}/teams/{teamId}")
    public ResponseEntity<String> removeUserFromTeam(@PathVariable Long userId, @PathVariable Long teamId) {
        if (service.removeUserFromTeam(new UserTeamId(userId, teamId))) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("User removed from team successfully");
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error has occurred");
        }
    }

    @GetMapping("/{userId}/teams")
    public ResponseEntity<String> isUserInTeam(@PathVariable Long userId, @RequestParam Long teamId) {
        if (service.isUserInTeam(new UserTeamId(userId, teamId))) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("User is a team participant");
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("User is not a team participant");
        }
    }

    @PostMapping("/{userId}/roles")
    public ResponseEntity<String> addRoleToUser(@PathVariable Long userId, @RequestParam String roleName) {
        if (service.addRoleToUser(userId, roleName)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Role added successfully");
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error has occurred");
        }
    }

    @DeleteMapping("/{userId}/roles")
    public ResponseEntity<String> removeRoleFromUser(@PathVariable Long userId, @RequestParam String roleName) {
        if (service.removeRoleFromUser(userId, roleName)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Role removed successfully");
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error has occurred");
        }
    }

    @GetMapping("/{userId}/roles")
    public ResponseEntity<String> hasRole(@PathVariable Long userId, @RequestParam String roleName) {
        if (service.hasRole(userId, roleName)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("User has specified role");
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("User hasn't specified role");
        }
    }
}

