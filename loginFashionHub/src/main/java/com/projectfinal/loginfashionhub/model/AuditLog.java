package com.projectfinal.loginfashionhub.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Represents an audit log entry for tracking user actions in the system.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "audit_logs")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for each audit log entry

    @Column(nullable = false)
    private String action; // Action performed (e.g., "LOGIN", "UPDATE_USER", etc.)

    @Column(nullable = false)
    private String username; // Username of the person who performed the action

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime timestamp; // Timestamp when the action occurred

    @Column(columnDefinition = "TEXT")
    private String details; // Additional information about the action
}
