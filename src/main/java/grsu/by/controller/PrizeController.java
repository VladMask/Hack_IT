package grsu.by.controller;

import grsu.by.dto.prizeDto.PrizeCreationDto;
import grsu.by.dto.prizeDto.PrizeFullDto;
import grsu.by.service.PrizeService;
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
@RequestMapping("/api/v1/prizes")
@RequiredArgsConstructor
@Tag(name = "PrizeController", description = "The Prize API")
public class PrizeController {

    private final PrizeService service;

    @Operation(summary = "Get all prizes", description = "Returns a list of all prizes")
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Set<PrizeFullDto> getAll() {
        return service.findAll();
    }

    @Operation(summary = "Get prize by ID", description = "Returns a single prize by its ID")
    @GetMapping("/{id}")
    public PrizeFullDto getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @Operation(summary = "Create prize", description = "Creates and returns a new prize")
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('HACKATHON_CREATOR')")
    public PrizeCreationDto create(@RequestBody PrizeCreationDto prizeCreationDto) {
        return service.create(prizeCreationDto);
    }

    @Operation(summary = "Update prize", description = "Updates and returns the prize")
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('HACKATHON_CREATOR')")
    public PrizeFullDto update(@PathVariable Long id, @RequestBody PrizeFullDto prizeFullDto) {
        return service.update(id, prizeFullDto);
    }

    @Operation(summary = "Delete prize by ID", description = "Deletes the prize and returns a status message")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('HACKATHON_CREATOR')")
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

    @Operation(summary = "Get prizes by hackathon ID", description = "Returns all prizes for the specified hackathon")
    @GetMapping("/hackathon/{hackathonId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('HACKATHON_CREATOR')")
    public Set<PrizeFullDto> getByHackathonId(@PathVariable Long hackathonId) {
        return service.findByHackathonId(hackathonId);
    }

    @Operation(summary = "Get prizes by team ID", description = "Returns all prizes for the specified team")
    @GetMapping("/team/{teamId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public Set<PrizeFullDto> getByTeamId(@PathVariable Long teamId) {
        return service.findByTeamId(teamId);
    }
}