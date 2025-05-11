package grsu.by.controller;

import grsu.by.dto.ScoreDto;
import grsu.by.service.ScoreService;
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
@RequestMapping("/api/v1/scores")
@RequiredArgsConstructor
@Tag(name = "ScoreController", description = "The Score API")
public class ScoreController {

    private final ScoreService service;

    @GetMapping
    public Set<ScoreDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ScoreDto getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ScoreDto create(@RequestBody ScoreDto scoreDto) {
        return service.create(scoreDto);
    }

    @PutMapping("/{id}")
    public ScoreDto update(@PathVariable Long id, @RequestBody ScoreDto scoreDto) {
        return service.update(id, scoreDto);
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

    @GetMapping("/solution/{solutionId}")
    public Set<ScoreDto> getBySolution(@PathVariable Long solutionId) {
        return service.findBySolutionId(solutionId);
    }

    @GetMapping("/judge/{judgeId}")
    public Set<ScoreDto>getByJudge(@PathVariable Long judgeId) {
        return service.findByJudgeId(judgeId);
    }
} 