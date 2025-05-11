package grsu.by.controller;

import grsu.by.dto.solutionDto.SolutionCreationDto;
import grsu.by.dto.solutionDto.SolutionFullDto;
import grsu.by.dto.solutionDto.SolutionShortDto;
import grsu.by.service.SolutionService;
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
@RequestMapping("/api/v1/solutions")
@RequiredArgsConstructor
@Tag(name = "SolutionController", description = "The Solution API")
public class SolutionController {

    private final SolutionService service;

    @GetMapping
    public Set<SolutionShortDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public SolutionFullDto getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public SolutionCreationDto create(@RequestBody SolutionCreationDto solutionDto) {
        return service.create(solutionDto);
    }

    @PutMapping("/{id}")
    public SolutionFullDto update(@PathVariable Long id, @RequestBody SolutionFullDto solutionDto) {
        return service.update(id, solutionDto);
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
    public Set<SolutionShortDto> getByTeam(@PathVariable Long teamId) {
        return service.findByTeamId(teamId);
    }

    @GetMapping("/hackathon/{hackathonId}")
    public Set<SolutionShortDto> getByHackathon(@PathVariable Long hackathonId) {
        return service.findByHackathonId(hackathonId);
    }
} 