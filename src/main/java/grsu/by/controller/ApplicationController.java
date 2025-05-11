package grsu.by.controller;

import grsu.by.dto.ApplicationDto;
import grsu.by.service.ApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/{id}")
    public ApplicationDto getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ApplicationDto create(@RequestBody ApplicationDto applicationDto) {
        return service.create(applicationDto);
    }

    @PutMapping("/{id}")
    public ApplicationDto update(@PathVariable Long id, @RequestBody ApplicationDto applicationDto) {
        return service.update(id, applicationDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
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

    @GetMapping("/team/{teamId}")
    public Set<ApplicationDto> getByTeam(@PathVariable Long teamId) {
        return service.findByTeamId(teamId);
    }

    @GetMapping("/hackathon/{hackathonId}")
    public Set<ApplicationDto> getByHackathon(@PathVariable Long hackathonId) {
        return service.findByHackathonId(hackathonId);
    }
} 