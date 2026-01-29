package com.globalkinetic.practical_test.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

/**
 * @author Joesta
 */

@Table(name = "jwt_blacklist")
@Entity
@Data
public class BlacklistedToken {
    @Id
    private String jti;
    private Instant expiresAt;
}
