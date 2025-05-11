package grsu.by.controller;

import grsu.by.dto.teamDto.TeamFullDto;
import grsu.by.dto.teamDto.TeamCreationDto;
import grsu.by.service.TeamService;
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
@RequestMapping("/api/v1/teams")
@RequiredArgsConstructor
@Tag(name = "TeamController", description = "The Team API")
public class TeamController {

    private final TeamService service;

    @GetMapping
    public Set<TeamFullDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public TeamFullDto getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public TeamCreationDto create(@RequestBody TeamCreationDto teamDto) {
        return service.create(teamDto);
    }

    @PutMapping("/{id}")
    public TeamFullDto update(@PathVariable Long id, @RequestBody TeamFullDto teamDto) {
        return service.update(id, teamDto);
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
} 