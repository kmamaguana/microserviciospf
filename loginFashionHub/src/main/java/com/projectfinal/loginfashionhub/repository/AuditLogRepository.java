package com.projectfinal.loginfashionhub.repository;

import com.projectfinal.loginfashionhub.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for managing audit log entries.
 */
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    // Add custom queries for audit logs if needed.
}
