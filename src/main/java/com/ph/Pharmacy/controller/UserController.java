package com.ph.Pharmacy.controller;

import com.ph.Pharmacy.dto.request.UserRequestDto;
import com.ph.Pharmacy.dto.response.UserResponseDto;
import com.ph.Pharmacy.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    private final UserService userService;

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody UserRequestDto userRequestDto) {
        log.info("Request received to create user with email: {}", userRequestDto.getEmail());

        // Check if email already exists
        if (userService.isEmailExists(userRequestDto.getEmail())) {
            log.warn("Email already exists: {}", userRequestDto.getEmail());
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Email already exists: " + userRequestDto.getEmail());
        }

        UserResponseDto response = userService.createUser(userRequestDto);
        log.info("User created successfully with ID: {}", response.getUserId());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-by-user-id/{userId}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long userId) {
        log.info("Request received to get user by ID: {}", userId);
        UserResponseDto response = userService.getUserById(userId);
        log.info("User retrieved successfully with ID: {}", userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-all-users")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        log.info("Request received to get all users");
        List<UserResponseDto> response = userService.getAllUsers();
        log.info("Retrieved {} users successfully", response.size());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update-user-by-id/{userId}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long userId,
                                                      @RequestBody UserRequestDto userRequestDto) {
        log.info("Request received to update user with ID: {}", userId);
        UserResponseDto response = userService.updateUser(userId, userRequestDto);
        log.info("User updated successfully with ID: {}", userId);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/patch-user-by-id/{userId}")
    public ResponseEntity<UserResponseDto> patchUser(@PathVariable Long userId,
                                                     @RequestBody UserRequestDto userRequestDto) {
        log.info("Request received to patch user with ID: {}", userId);
        UserResponseDto response = userService.patchUser(userId, userRequestDto);
        log.info("User patched successfully with ID: {}", userId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete-by-user-id/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        log.info("Request received to delete user with ID: {}", userId);
        userService.deleteUser(userId);
        log.info("User deleted successfully with ID: {}", userId);
        return ResponseEntity.status(HttpStatus.OK)
                .body("User deleted!! with user id: " + userId);
    }
}

