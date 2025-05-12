package grsu.by.controller;

import grsu.by.dto.notificationDto.NotificationCreationDto;
import grsu.by.dto.notificationDto.NotificationFullDto;
import grsu.by.service.NotificationService;
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
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
@Tag(name = "NotificationController", description = "The Notification API")
public class NotificationController {

    private final NotificationService service;

    @Operation(summary = "Get all notifications", description = "Returns a list of all notifications")
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Set<NotificationFullDto> getAll() {
        return service.findAll();
    }

    @Operation(summary = "Get notification by ID", description = "Returns a single notification by its ID")
    @GetMapping("/{id}")
    public NotificationFullDto getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @Operation(summary = "Create notification", description = "Creates and returns a new notification")
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('HACKATHON_CREATOR')")
    public NotificationCreationDto create(@RequestBody NotificationCreationDto notificationCreationDto) {
        return service.create(notificationCreationDto);
    }

    @Operation(summary = "Update notification", description = "Updates and returns the notification")
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('HACKATHON_CREATOR')")
    public NotificationFullDto update(@PathVariable Long id, @RequestBody NotificationFullDto notificationFullDto) {
        return service.update(id, notificationFullDto);
    }

    @Operation(summary = "Delete notification by ID", description = "Deletes the notification and returns a status message")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('HACKATHON_CREATOR')")
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

    @Operation(summary = "Get notifications by user ID", description = "Returns all notifications for the specified user")
    @GetMapping("/user/{userId}")
    public Set<NotificationFullDto> getByUser(@PathVariable Long userId) {
        return service.findByUserId(userId);
    }

    @Operation(summary = "Get notifications by hackathon ID", description = "Returns all notifications for the specified hackathon")
    @GetMapping("/hackathon/{hackathonId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('HACKATHON_CREATOR')")
    public Set<NotificationFullDto> getByHackathon(@PathVariable Long hackathonId) {
        return service.findByHackathonId(hackathonId);
    }
}