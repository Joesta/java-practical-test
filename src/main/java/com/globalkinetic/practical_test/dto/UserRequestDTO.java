package com.globalkinetic.practical_test.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * @author Joesta
 */
public record UserRequestDTO(@NotBlank String username, @NotBlank String password, String phone) { }
