package com.globalkinetic.practical_test.controllers;

import com.globalkinetic.practical_test.dto.UserRequestDTO;
import com.globalkinetic.practical_test.dto.UserResponseDTO;
import com.globalkinetic.practical_test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Joesta
 */

@RestController
@RequestMapping("/api")
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("users")
    public ResponseEntity<Void> createUser(@RequestBody UserRequestDTO userReq) {
        userService.createUser(userReq);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("users")
    public ResponseEntity<UserResponseDTO> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
