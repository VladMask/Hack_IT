package grsu.by.controller;

import grsu.by.entity.UserTeamId;
import grsu.by.service.UserTeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user-teams")
@RequiredArgsConstructor
public class UserTeamController {

    private final UserTeamService service;

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
} 