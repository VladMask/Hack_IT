package grsu.by.controller;

import grsu.by.dto.RoleDto;
import grsu.by.service.RoleService;
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
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
@Tag(name = "RoleController", description = "The Role API")
public class RoleController {

    private final RoleService service;

    @GetMapping
    public Set<RoleDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public RoleDto getById(@PathVariable Short id) {
        return service.findById(id);
    }

    @PostMapping
    public RoleDto create(@RequestBody RoleDto roleDto) {
        return service.create(roleDto);
    }

    @PutMapping("/{id}")
    public RoleDto update(@PathVariable Short id, @RequestBody RoleDto roleDto) {
        return service.update(id, roleDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteById(@PathVariable Short id) {
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
