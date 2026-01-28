package com.globalkinetic.practical_test.models;

import com.globalkinetic.practical_test.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

/**
 * @author Joesta
 */

@Entity
@Table(name = "users")
@Data
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String phone;
    @CreationTimestamp
    private Instant createdAt;
    @Enumerated(EnumType.STRING)
    private Role role;
}
