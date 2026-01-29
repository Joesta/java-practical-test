package com.globalkinetic.practical_test.dto;

import java.time.Instant;

/**
 * @author Joesta
 */
public record ErrorResponseDTO(int status, String message, Instant timestamp) { }
