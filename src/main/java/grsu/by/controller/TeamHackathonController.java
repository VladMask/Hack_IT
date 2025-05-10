package grsu.by.controller;

import grsu.by.service.TeamHackathonService;
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
@RequestMapping("/api/v1/team-hackathons")
@RequiredArgsConstructor
public class TeamHackathonController {

    private final TeamHackathonService service;

    @PostMapping("/{teamId}/hackathons/{hackathonId}")
    public ResponseEntity<String> registerTeamForHackathon(@PathVariable Long teamId, @PathVariable Long hackathonId) {
        if (service.registerTeamForHackathon(teamId, hackathonId)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Team registered successfully");
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error has occurred");
        }
    }

    @DeleteMapping("/{teamId}/hackathons/{hackathonId}")
    public ResponseEntity<String> unregisterTeamFromHackathon(@PathVariable Long teamId, @PathVariable Long hackathonId) {
        if (service.unregisterTeamFromHackathon(teamId, hackathonId)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Team unregistered successfully");
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error has occurred");
        }
    }

    @GetMapping("/{teamId}/hackathons")
    public ResponseEntity<String> isTeamRegisteredForHackathon(@PathVariable Long teamId, @RequestParam Long hackathonId) {
        if (service.isTeamRegisteredForHackathon(teamId, hackathonId)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Team is registered for hackathon");
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Team is not registered for hackathon");
        }
    }
} 