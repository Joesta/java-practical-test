package com.globalkinetic.practical_test.controllers;

import com.globalkinetic.practical_test.dto.*;
import com.globalkinetic.practical_test.services.AuthService;
import com.globalkinetic.practical_test.services.UserService;
import com.globalkinetic.practical_test.services.jwt.JwtBlacklistService;
import com.globalkinetic.practical_test.services.jwt.JwtService;
import jakarta.validation.Valid;
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
public class UserController {
    private UserService userService;
    private AuthService authService;
    private JwtService jwtService;
    private JwtBlacklistService jwtBlacklistService;

    @Autowired
    public void setJwtService(JwtBlacklistService jwtBlacklistService) {
        this.jwtBlacklistService = jwtBlacklistService;
    }

    @Autowired
    public void setJwtService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

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
    public ResponseEntity<Long> logout(@PathVariable Long id, @RequestHeader("Authorization") String authHeader, Authentication authentication) {
        jwtBlacklistService.blacklist(id, authHeader, authentication);
        return ResponseEntity.noContent().build();
    }
}
