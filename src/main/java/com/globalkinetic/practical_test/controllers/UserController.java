package com.globalkinetic.practical_test.controllers;

import com.globalkinetic.practical_test.dto.LoginRequestDTO;
import com.globalkinetic.practical_test.dto.LoginResponseDTO;
import com.globalkinetic.practical_test.dto.UserRequestDTO;
import com.globalkinetic.practical_test.dto.UserResponseDTO;
import com.globalkinetic.practical_test.services.AuthService;
import com.globalkinetic.practical_test.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Joesta
 */

@RestController
@RequestMapping("/api")
public class UserController {
    private UserService userService;
    private AuthService authService;

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
}
