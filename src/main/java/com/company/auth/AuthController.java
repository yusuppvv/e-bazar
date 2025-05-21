package com.company.auth;

import com.company.auth.dto.*;
import com.company.component.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String >> register(@RequestBody AuthRegister authRegister) {
        return ResponseEntity.ok(authService.register(authRegister));
    }

    @PostMapping("/verification")
    public ResponseEntity<ApiResponse<AuthVerificationResponse>> verification(@RequestBody AuthVerification authVerification) {
        return ResponseEntity.ok(authService.verification(authVerification));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthVerificationResponse>> login(@RequestBody AuthLogin authLogin) {
        return ResponseEntity.ok(authService.login(authLogin));
    }
    @PostMapping("/confirm-login")
    public ResponseEntity<ApiResponse<AuthVerificationResponse>> confirmLogin(@RequestBody AuthConfirmLogin authConfirmLogin) {
        return ResponseEntity.ok(authService.confirmLogin(authConfirmLogin));
    }
}
