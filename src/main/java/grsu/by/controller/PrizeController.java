package grsu.by.controller;

import grsu.by.dto.PrizeDto;
import grsu.by.service.PrizeService;
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
@RequestMapping("/api/v1/prizes")
@RequiredArgsConstructor
@Tag(name = "PrizeController", description = "The Prize API")
public class PrizeController {

    private final PrizeService service;

    @GetMapping
    public Set<PrizeDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public PrizeDto getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public PrizeDto create(@RequestBody PrizeDto prizeDto) {
        return service.create(prizeDto);
    }

    @PutMapping("/{id}")
    public PrizeDto update(@PathVariable Long id, @RequestBody PrizeDto prizeDto) {
        return service.update(id, prizeDto);
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

    @GetMapping("/hackathon/{hackathonId}")
    public Set<PrizeDto> getByHackathonId(@PathVariable Long hackathonId) {
        return service.findByHackathonId(hackathonId);
    }

    @GetMapping("/team/{teamId}")
    public Set<PrizeDto> getByTeamId(@PathVariable Long teamId) {
        return service.findByTeamId(teamId);
    }
} 