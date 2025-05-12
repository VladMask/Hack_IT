package grsu.by.controller;

import grsu.by.dto.solutionDto.SolutionCreationDto;
import grsu.by.dto.solutionDto.SolutionFullDto;
import grsu.by.dto.solutionDto.SolutionShortDto;
import grsu.by.service.SolutionService;
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
@RequestMapping("/api/v1/solutions")
@RequiredArgsConstructor
@Tag(name = "SolutionController", description = "The Solution API")
public class SolutionController {

    private final SolutionService service;

    @Operation(summary = "Get all solutions", description = "Returns a list of all submitted solutions")
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Set<SolutionShortDto> getAll() {
        return service.findAll();
    }

    @Operation(summary = "Get solution by ID", description = "Returns a single solution by its ID")
    @GetMapping("/{id}")
    public SolutionFullDto getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @Operation(summary = "Create solution", description = "Creates and returns a new solution")
    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    public SolutionCreationDto create(@RequestBody SolutionCreationDto solutionDto) {
        return service.create(solutionDto);
    }

    @Operation(summary = "Update solution", description = "Updates and returns the specified solution")
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public SolutionFullDto update(@PathVariable Long id, @RequestBody SolutionFullDto solutionDto) {
        return service.update(id, solutionDto);
    }

    @Operation(summary = "Delete solution by ID", description = "Deletes the solution and returns a status message")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        if (service.deleteById(id)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Entity deleted successfully");
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error has occurred");
        }
    }

    @Operation(summary = "Get solutions by team ID", description = "Returns solutions submitted by a specific team")
    @GetMapping("/team/{teamId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Set<SolutionShortDto> getByTeam(@PathVariable Long teamId) {
        return service.findByTeamId(teamId);
    }

    @Operation(summary = "Get solutions by hackathon ID", description = "Returns solutions submitted to a specific hackathon")
    @GetMapping("/hackathon/{hackathonId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('HACKATHON_CREATOR')")
    public Set<SolutionShortDto> getByHackathon(@PathVariable Long hackathonId) {
        return service.findByHackathonId(hackathonId);
    }
}