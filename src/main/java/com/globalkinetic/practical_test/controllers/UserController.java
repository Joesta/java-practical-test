package com.globalkinetic.practical_test.controllers;

import com.globalkinetic.practical_test.dto.*;
import com.globalkinetic.practical_test.services.AuthService;
import com.globalkinetic.practical_test.services.UserService;
import com.globalkinetic.practical_test.services.jwt.JwtBlacklistService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * @author Joesta
 */

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthService authService;
    private final JwtBlacklistService jwtBlacklistService;


    @PutMapping("users")
    public ResponseEntity<Void> createUser(@RequestBody @Valid UserRequestDTO userReq) {
        userService.createUser(userReq);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("users")
    public ResponseEntity<UserResponseDTO> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginReq) {
        return ResponseEntity.ok(authService.login(loginReq));
    }

    @PostMapping("logout/{id}")
    public ResponseEntity<LogoutResponseDTO> logout(@PathVariable Long id, @RequestHeader("Authorization") String authHeader, Authentication authentication) {
        return ResponseEntity.ok(jwtBlacklistService.blacklist(id, authHeader, authentication));
    }
}
