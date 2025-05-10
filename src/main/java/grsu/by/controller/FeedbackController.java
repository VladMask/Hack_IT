package grsu.by.controller;

import grsu.by.dto.FeedbackDto;
import grsu.by.service.FeedbackService;
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

import java.util.List;

@RestController
@RequestMapping("/api/v1/feedback")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService service;

    @GetMapping("/{id}")
    public FeedbackDto getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public FeedbackDto create(@RequestBody FeedbackDto feedbackDto) {
        return service.create(feedbackDto);
    }

    @PutMapping("/{id}")
    public FeedbackDto update(@PathVariable Long id, @RequestBody FeedbackDto feedbackDto) {
        return service.update(id, feedbackDto);
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

    @GetMapping("/judge/{judgeId}")
    public List<FeedbackDto> getByJudge(@PathVariable Long judgeId) {
        return service.findByJudgeId(judgeId);
    }

    @GetMapping("/solution/{solutionId}")
    public List<FeedbackDto> getBySolution(@PathVariable Long solutionId) {
        return service.findBySolutionId(solutionId);
    }

    @GetMapping("/hackathon/{hackathonId}")
    public List<FeedbackDto> getByHackathon(@PathVariable Long hackathonId) {
        return service.findByHackathonId(hackathonId);
    }
} 