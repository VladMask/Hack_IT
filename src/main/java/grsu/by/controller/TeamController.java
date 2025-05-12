package grsu.by.controller;

import grsu.by.dto.teamDto.TeamFullDto;
import grsu.by.dto.teamDto.TeamCreationDto;
import grsu.by.service.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/teams")
@RequiredArgsConstructor
@Tag(name = "TeamController", description = "The Team API")
public class TeamController {

    private final TeamService service;

    @Operation(summary = "Get all teams", description = "Returns all teams in the system")
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Set<TeamCreationDto> getAll() {
        return service.findAll();
    }

    @Operation(summary = "Get team by ID", description = "Returns a team by its unique ID")
    @GetMapping("/{id}")
    public TeamFullDto getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @Operation(summary = "Create a new team", description = "Creates a team and returns the created entity")
    @PostMapping
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public TeamCreationDto create(@RequestBody TeamCreationDto teamDto) {
        return service.create(teamDto);
    }

    @Operation(summary = "Update an existing team", description = "Updates and returns the team with the given ID")
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public TeamFullDto update(@PathVariable Long id, @RequestBody TeamFullDto teamDto) {
        return service.update(id, teamDto);
    }

    @Operation(summary = "Delete a team", description = "Deletes the team by ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('HACKATHON_CREATOR') or hasAuthority('USER')")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        if (service.deleteById(id)) {
            return ResponseEntity.ok("Entity deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error has occurred");
        }
    }

    @Operation(summary = "Register a team for a hackathon", description = "Registers a specific team to a specific hackathon")
    @PostMapping("/{teamId}/hackathons/{hackathonId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('HACKATHON_CREATOR')")
    public ResponseEntity<String> registerTeamForHackathon(@PathVariable Long teamId, @PathVariable Long hackathonId) {
        if (service.registerTeamForHackathon(teamId, hackathonId)) {
            return ResponseEntity.ok("Team registered successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error has occurred");
        }
    }

    @Operation(summary = "Unregister a team from a hackathon", description = "Removes a team's registration from a hackathon")
    @DeleteMapping("/{teamId}/hackathons/{hackathonId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('HACKATHON_CREATOR')")
    public ResponseEntity<String> unregisterTeamFromHackathon(@PathVariable Long teamId, @PathVariable Long hackathonId) {
        if (service.unregisterTeamFromHackathon(teamId, hackathonId)) {
            return ResponseEntity.ok("Team unregistered successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error has occurred");
        }
    }

    @Operation(summary = "Check team registration", description = "Checks if a team is registered for a specific hackathon")
    @GetMapping("/{teamId}/hackathons/{hackathonId}")
    public ResponseEntity<String> isTeamRegisteredForHackathon(@PathVariable Long teamId, @PathVariable Long hackathonId) {
        if (service.isTeamRegisteredForHackathon(teamId, hackathonId)) {
            return ResponseEntity.ok("Team is registered for hackathon");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Team is not registered for hackathon");
        }
    }
}