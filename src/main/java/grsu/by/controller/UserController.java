package grsu.by.controller;

import grsu.by.dto.userDto.UserBaseDto;
import grsu.by.dto.userDto.UserCreationDto;
import grsu.by.entity.UserTeamId;
import grsu.by.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "UserController", description = "The User API")
public class UserController {

    private final UserService service;

    @Operation(summary = "Create a new user", description = "Creates a new user and returns the created user entity")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserBaseDto create(@RequestBody @Valid UserCreationDto dto) {
        return service.create(dto);
    }

    @Operation(summary = "Find user by ID", description = "Returns user details for the given ID")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public UserBaseDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @Operation(summary = "Update user details", description = "Updates user details for the given ID and returns the updated user entity")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserBaseDto update(@PathVariable Long id, @RequestBody @Valid UserBaseDto dto) {
        return service.update(id, dto);
    }

    @Operation(summary = "Delete a user", description = "Deletes a user by ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (service.deleteById(id)) {
            return ResponseEntity.ok("Entity deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error has occurred");
        }
    }

    @Operation(summary = "Add user to a team", description = "Adds a user to a team with the specified user and team IDs")
    @PostMapping("/{userId}/teams/{teamId}")
    public ResponseEntity<String> addUserToTeam(@PathVariable Long userId, @PathVariable Long teamId) {
        if (service.addUserToTeam(new UserTeamId(userId, teamId))) {
            return ResponseEntity.ok("User added to team successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error has occurred");
        }
    }

    @Operation(summary = "Remove user from a team", description = "Removes the user from a team based on user and team IDs")
    @DeleteMapping("/{userId}/teams/{teamId}")
    public ResponseEntity<String> removeUserFromTeam(@PathVariable Long userId, @PathVariable Long teamId) {
        if (service.removeUserFromTeam(new UserTeamId(userId, teamId))) {
            return ResponseEntity.ok("User removed from team successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error has occurred");
        }
    }

    @Operation(summary = "Check if user is in a team", description = "Checks if the user is a member of a specific team")
    @GetMapping("/{userId}/teams")
    public ResponseEntity<String> isUserInTeam(@PathVariable Long userId, @RequestParam Long teamId) {
        if (service.isUserInTeam(new UserTeamId(userId, teamId))) {
            return ResponseEntity.ok("User is a team participant");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("User is not a team participant");
        }
    }

    @Operation(summary = "Add role to a user", description = "Adds a role to a user")
    @PostMapping("/{userId}/roles/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> addRoleToUser(@PathVariable Long userId, @RequestParam String roleName) {
        if (service.addRoleToUser(userId, roleName)) {
            return ResponseEntity.ok("Role added successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error has occurred");
        }
    }

    @Operation(summary = "Remove role from a user", description = "Removes a role from a user")
    @DeleteMapping("/{userId}/roles/remove")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> removeRoleFromUser(@PathVariable Long userId, @RequestParam String roleName) {
        if (service.removeRoleFromUser(userId, roleName)) {
            return ResponseEntity.ok("Role removed successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error has occurred");
        }
    }

    @Operation(summary = "Check if a user has a specific role", description = "Checks if the user has a specified role")
    @GetMapping("/{userId}/roles")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> hasRole(@PathVariable Long userId, @RequestParam String roleName) {
        if (service.hasRole(userId, roleName)) {
            return ResponseEntity.ok("User has specified role");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("User hasn't specified role");
        }
    }
}