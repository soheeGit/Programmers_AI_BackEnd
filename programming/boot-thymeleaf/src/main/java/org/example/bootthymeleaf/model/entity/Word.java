package org.example.bootthymeleaf.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;
    @Column(nullable = false, length = 3)
    private String text;
    @CreationTimestamp
    private LocalDateTime createdAt;
}
