package grsu.by.controller;

import grsu.by.dto.feedbackDto.FeedbackCreationDto;
import grsu.by.dto.feedbackDto.FeedbackFullDto;
import grsu.by.service.FeedbackService;
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
@RequestMapping("/api/v1/feedback")
@RequiredArgsConstructor
@Tag(name = "FeedbackController", description = "The Feedback API")
public class FeedbackController {

    private final FeedbackService service;

    @Operation(summary = "Get feedback by ID", description = "Returns a single feedback by its ID")
    @GetMapping("/{id}")
    public FeedbackFullDto getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @Operation(summary = "Create feedback", description = "Returns created feedback")
    @PostMapping
    @PreAuthorize("hasAuthority('JUDGE')")
    public FeedbackCreationDto create(@RequestBody FeedbackCreationDto feedbackCreationDto) {
        return service.create(feedbackCreationDto);
    }

    @Operation(summary = "Update feedback", description = "Returns updated feedback")
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('JUDGE')")
    public FeedbackFullDto update(@PathVariable Long id, @RequestBody FeedbackFullDto feedbackFullDto) {
        return service.update(id, feedbackFullDto);
    }

    @Operation(summary = "Delete feedback by ID", description = "Deletes feedback and returns status message")
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

    @Operation(summary = "Get feedback by judge ID", description = "Returns feedback left by a specific judge")
    @GetMapping("/judge/{judgeId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('JUDGE') or hasAuthority('HACKATHON_CREATOR')")
    public Set<FeedbackFullDto> getByJudge(@PathVariable Long judgeId) {
        return service.findByJudgeId(judgeId);
    }

    @Operation(summary = "Get feedback by solution ID", description = "Returns feedback related to a specific solution")
    @GetMapping("/solution/{solutionId}")
    public Set<FeedbackFullDto> getBySolution(@PathVariable Long solutionId) {
        return service.findBySolutionId(solutionId);
    }

    @Operation(summary = "Get feedback by hackathon ID", description = "Returns all feedback for a specific hackathon")
    @GetMapping("/hackathon/{hackathonId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('HACKATHON_CREATOR')")
    public Set<FeedbackFullDto> getByHackathon(@PathVariable Long hackathonId) {
        return service.findByHackathonId(hackathonId);
    }
}