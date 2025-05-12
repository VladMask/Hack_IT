package grsu.by.controller;

import grsu.by.dto.ApplicationDto;
import grsu.by.service.ApplicationService;
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
@RequestMapping("/api/v1/applications")
@RequiredArgsConstructor
@Tag(name = "ApplicationController", description = "The Application API")
public class ApplicationController {

    private final ApplicationService service;

    @Operation(summary = "Get application by ID", description = "Returns a single application by its ID")
    @GetMapping("/{id}")
    public ApplicationDto getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @Operation(summary = "Creates application", description = "Returns created application")
    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    public ApplicationDto create(@RequestBody ApplicationDto applicationDto) {
        return service.create(applicationDto);
    }

    @Operation(summary = "Updates application", description = "Returns updated application")
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ApplicationDto update(@PathVariable Long id, @RequestBody ApplicationDto applicationDto) {
        return service.update(id, applicationDto);
    }

    @Operation(summary = "Deletes application by ID", description = "Returns message ")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
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

    @Operation(summary = "Get applications by teamID", description = "Returns teams applications")
    @GetMapping("/team/{teamId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Set<ApplicationDto> getByTeam(@PathVariable Long teamId) {
        return service.findByTeamId(teamId);
    }

    @Operation(summary = "Get hackathon by teamID", description = "Returns hackathons applications")
    @GetMapping("/hackathon/{hackathonId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('JUDGE') or hasAuthority('HACKATHON_CREATOR')")
    public Set<ApplicationDto> getByHackathon(@PathVariable Long hackathonId) {
        return service.findByHackathonId(hackathonId);
    }

    @Operation(summary = "Accepts application for a hackathon", description = "Marks application as accepted and registers a specific team to a specific hackathon")
    @PostMapping("/{applicationId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('JUDGE') or hasAuthority('HACKATHON_CREATOR')")
    public ResponseEntity<String> acceptApplication(@PathVariable Long applicationId) {
        if (service.acceptApplication(applicationId)) {
            return ResponseEntity.ok("Application is accepted");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Application is not accepted");
        }
    }
}