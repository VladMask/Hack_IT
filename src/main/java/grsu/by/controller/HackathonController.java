package grsu.by.controller;

import grsu.by.dto.hackathonDto.HackathonCreationDto;
import grsu.by.dto.hackathonDto.HackathonFullDto;
import grsu.by.dto.hackathonDto.HackathonShortDto;
import grsu.by.dto.userDto.UserBaseDto;
import grsu.by.service.HackathonService;
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
@RequestMapping("/api/v1/hackathons")
@RequiredArgsConstructor
public class HackathonController {

    private final HackathonService service;

    @GetMapping
    public List<HackathonShortDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public HackathonFullDto getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public HackathonCreationDto create(@RequestBody HackathonCreationDto hackathonDto) {
        return service.create(hackathonDto);
    }

    @PutMapping("/{id}")
    public HackathonFullDto update(@PathVariable Long id, @RequestBody HackathonFullDto hackathonDto) {
        return service.update(id, hackathonDto);
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

    @PostMapping("/{id}/judges/{judgeId}")
    public ResponseEntity<String> addJudge(@PathVariable Long id, @PathVariable Long judgeId) {
        if (service.addJudge(id, judgeId)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Judge added successfully");
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error has occurred");
        }
    }

    @DeleteMapping("/{id}/judges/{judgeId}")
    public ResponseEntity<String> removeJudge(@PathVariable Long id, @PathVariable Long judgeId) {
        if (service.removeJudge(id, judgeId)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Judge removed successfully");
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error has occurred");
        }
    }

    @GetMapping("/{id}/judges")
    public List<UserBaseDto> getJudges(@PathVariable Long id) {
        return service.getJudges(id);
    }

    @PostMapping("/{id}/finish")
    public ResponseEntity<String> finish(@PathVariable Long id) {
        if (service.finishHackathon(id)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Hackathon is finished");
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error has occurred");
        }
    }
} 