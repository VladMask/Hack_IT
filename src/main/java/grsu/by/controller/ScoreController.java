package grsu.by.controller;

import grsu.by.dto.ScoreDto;
import grsu.by.service.ScoreService;
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
@RequestMapping("/api/v1/scores")
@RequiredArgsConstructor
@Tag(name = "ScoreController", description = "The Score API")
public class ScoreController {

    private final ScoreService service;

    @Operation(summary = "Get all scores", description = "Returns a list of all scores")
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Set<ScoreDto> getAll() {
        return service.findAll();
    }

    @Operation(summary = "Get score by ID", description = "Returns a single score by its ID")
    @GetMapping("/{id}")
    public ScoreDto getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @Operation(summary = "Create score", description = "Creates and returns a new score")
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('JUDGE')")
    public ScoreDto create(@RequestBody ScoreDto scoreDto) {
        return service.create(scoreDto);
    }

    @Operation(summary = "Update score", description = "Updates and returns the score")
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('JUDGE')")
    public ScoreDto update(@PathVariable Long id, @RequestBody ScoreDto scoreDto) {
        return service.update(id, scoreDto);
    }

    @Operation(summary = "Delete score by ID", description = "Deletes the score and returns a status message")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('JUDGE')")
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

    @Operation(summary = "Get scores by solution ID", description = "Returns scores linked to the specified solution")
    @GetMapping("/solution/{solutionId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('HACKATHON_CREATOR') or hasAuthority('USER')")
    public Set<ScoreDto> getBySolution(@PathVariable Long solutionId) {
        return service.findBySolutionId(solutionId);
    }

    @Operation(summary = "Get scores by judge ID", description = "Returns scores given by the specified judge")
    @GetMapping("/judge/{judgeId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('JUDGE')")
    public Set<ScoreDto> getByJudge(@PathVariable Long judgeId) {
        return service.findByJudgeId(judgeId);
    }
}