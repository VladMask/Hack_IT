package grsu.by.controller;

import grsu.by.dto.hackathonDto.HackathonCreationDto;
import grsu.by.dto.hackathonDto.HackathonFullDto;
import grsu.by.dto.hackathonDto.HackathonShortDto;
import grsu.by.dto.userDto.UserBaseDto;
import grsu.by.service.HackathonService;
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
@RequestMapping("/api/v1/hackathons")
@RequiredArgsConstructor
@Tag(name = "HackathonController", description = "The Hackathon API")
public class HackathonController {

    private final HackathonService service;

    @Operation(summary = "Get all hackathons", description = "Returns a list of all hackathons with basic info")
    @GetMapping
    public Set<HackathonShortDto> getAll() {
        return service.findAll();
    }

    @Operation(summary = "Get hackathon by ID", description = "Returns full information about a specific hackathon")
    @GetMapping("/{id}")
    public HackathonFullDto getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @Operation(summary = "Create a new hackathon", description = "Creates and returns a new hackathon")
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('HACKATHON_CREATOR')")
    public HackathonCreationDto create(@RequestBody HackathonCreationDto hackathonDto) {
        return service.create(hackathonDto);
    }

    @Operation(summary = "Update hackathon", description = "Updates and returns the hackathon")
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('HACKATHON_CREATOR')")
    public HackathonFullDto update(@PathVariable Long id, @RequestBody HackathonFullDto hackathonDto) {
        return service.update(id, hackathonDto);
    }

    @Operation(summary = "Delete hackathon by ID", description = "Deletes the hackathon and returns a status message")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('HACKATHON_CREATOR')")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        if (service.deleteById(id)) {
            return ResponseEntity.status(HttpStatus.OK).body("Entity deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error has occurred");
        }
    }

    @Operation(summary = "Add judge to hackathon", description = "Adds a judge to the hackathon by ID")
    @PostMapping("/{id}/judges/{judgeId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('HACKATHON_CREATOR')")
    public ResponseEntity<String> addJudge(@PathVariable Long id, @PathVariable Long judgeId) {
        if (service.addJudge(id, judgeId)) {
            return ResponseEntity.status(HttpStatus.OK).body("Judge added successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error has occurred");
        }
    }

    @Operation(summary = "Remove judge from hackathon", description = "Removes a judge from the hackathon by ID")
    @DeleteMapping("/{id}/judges/{judgeId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('HACKATHON_CREATOR')")
    public ResponseEntity<String> removeJudge(@PathVariable Long id, @PathVariable Long judgeId) {
        if (service.removeJudge(id, judgeId)) {
            return ResponseEntity.status(HttpStatus.OK).body("Judge removed successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error has occurred");
        }
    }

    @Operation(summary = "Get all judges of a hackathon", description = "Returns a list of judges for the specified hackathon")
    @GetMapping("/{id}/judges")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('HACKATHON_CREATOR')")
    public Set<UserBaseDto> getJudges(@PathVariable Long id) {
        return service.getJudges(id);
    }

    @Operation(summary = "Finish hackathon", description = "Marks the hackathon as finished")
    @PostMapping("/{id}/finish")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('HACKATHON_CREATOR')")
    public ResponseEntity<String> finish(@PathVariable Long id) {
        if (service.finishHackathon(id)) {
            return ResponseEntity.status(HttpStatus.OK).body("Hackathon is finished");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error has occurred");
        }
    }
}