package com.company.users;

import com.company.component.ApiResponse;
import com.company.users.dto.UserResponse;
import com.company.users.dto.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/get/{userId}")
    public ResponseEntity<ApiResponse<UserResponse>> get(@PathVariable UUID userId) {
        return ResponseEntity.ok(userService.get(userId));
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<ApiResponse<UserResponse>> update(@PathVariable UUID userId, @RequestBody UserUpdateDto userUpdateDto) {
        return ResponseEntity.ok(userService.update(userId, userUpdateDto));
    }
}
