package grsu.by.controller;

import grsu.by.dto.userDto.UserBaseDto;
import grsu.by.dto.userDto.UserCreationDto;
import grsu.by.service.UserService;
import jakarta.validation.Valid;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserBaseDto create(@RequestBody @Valid UserCreationDto dto) {
        return service.create(dto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public UserBaseDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserBaseDto update(@PathVariable Long id, @RequestBody @Valid UserBaseDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> delete(@PathVariable Long id) {
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

//    @PostMapping("/{userId}/roles")
//    @ResponseStatus(HttpStatus.OK)
//    @PreAuthorize("hasAuthority('ADMIN')")
//    public ResponseEntity<?> setUserRole(@PathVariable Long userId, @RequestParam String roleName){
//        if (service.setUserRole(userId, roleName)) {
//            return ResponseEntity
//                    .status(HttpStatus.OK)
//                    .body("Role set successfully");
//        }
//        else {
//            return ResponseEntity
//                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("An unexpected error has occurred");
//        }
//
//    }
}

