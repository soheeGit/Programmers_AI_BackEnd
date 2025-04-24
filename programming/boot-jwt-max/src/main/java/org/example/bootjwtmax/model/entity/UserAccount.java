package org.example.bootjwtmax.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Entity // JPA
@Data // Lombok
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false, unique = true)
    private String username; // 이건 겹치면 안 된다
    @Column(nullable = false)
    private String password; // BCrypt
    @CreationTimestamp
    private ZonedDateTime createdAt = ZonedDateTime.now(ZoneOffset.UTC);
    @Column(nullable = false)
    private String role;
}